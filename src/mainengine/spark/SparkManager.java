package mainengine.spark;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import result.Cell;
import result.Result;

public class SparkManager {
	
	private String schemaName;
	private String inputFolder;
	private String cubeName;
	private SparkSession spark;
	
	private HashMap<String, Dataset<Row>> datasets = new HashMap<>();
	
	@SuppressWarnings("resource")
	public SparkManager(String schemaName, String inputFolder, String cubeName) {
		this.schemaName = schemaName;
		this.inputFolder = inputFolder;
		this.cubeName = cubeName;
		
		System.setProperty("hadoop.home.dir", "c:/hadoop");
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		spark = SparkSession
				.builder()
				.appName("Spark Session")
				.master("local[*]")
				.config("spark.sql.warehouse.dir", "file:///c:/tmp/")
				.getOrCreate();
		
		createDataSets();
	}
	
	public void createDataSets() {
		String path = "InputFiles/" + schemaName + "/Data";
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if(file.isFile()) {
				datasets.put(FilenameUtils.removeExtension(file.getName()),spark.read()
								.format("csv")
								.option("sep", ",")
								.option("inferSchema", "true")
								.option("header", "true")
								.load(file.getPath()));
			}
		}
		datasets.get("sales").printSchema();
	}
	
	public Result executeQueryToSpark(String query, Result result) {
		for (Map.Entry<String, Dataset<Row>> entry : datasets.entrySet()) {
			String key = entry.getKey();
			Dataset<Row> value = entry.getValue();
			value.createOrReplaceTempView(key);
		}
		long startTime = System.currentTimeMillis();
		Dataset<Row> queryDS = spark.sql(query);
		queryDS.show();
		queryDS.printSchema();
		Dataset<Row> temp = queryDS.filter(row -> row.getAs("subcategory").equals("subcategory1"));
		temp.show();

		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(estimatedTime);
		result = generateResult(temp, result);
		return result;
	}
	
	public Result generateResult(Dataset<Row> queryDS, Result result) {
		List<Row> queryList = queryDS.collectAsList();
		int columnCount = queryDS.columns().length;
		int rowCount = queryDS.collectAsList().size();
		System.out.println(columnCount + " 9999999999999999 " + rowCount);
		boolean ret_value=false;
		
		String resultArray [][] = new String[rowCount+2][columnCount];

		for(int i=0;i<columnCount;i++) {
			resultArray[0][i]= queryDS.columns()[i];
			result.getColumnNames().add(queryDS.columns()[i]);
			resultArray[1][i]= queryDS.columns()[i];
			result.getColumnLabels().add(queryDS.columns()[i]);
		}
		
		for (int i = 0; i < queryList.size(); i++) {
			String[] values = new String[queryList.get(i).size()];
			for (int j = 0; j < columnCount; j++) {
				String value = queryList.get(i).get(j).toString();
				resultArray[i][j]=value;
				values[j] = value;
			}
			/*
			 * VERY IMPORTANT: here is where cells are created!
			 * We need them to be in a List, so that they are ordered!
			 */
			result.getCells().add(new Cell(values));

			result.setResultArray(resultArray);
			System.out.println(result);
		}
		String[] columns = queryDS.columns();
		for (String column : columns) {
			System.out.println(column);
		}
		System.out.println(queryList);
		return result;
	}
}
