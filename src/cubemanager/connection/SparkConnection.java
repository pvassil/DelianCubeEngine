package cubemanager.connection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

import cubemanager.starschema.Table;
import mainengine.spark.SparkManager;
import result.Result;

public class SparkConnection extends Connection {
	
	private String schemaName;
	private String inputFolder;
	private String cubeName;
	private SparkManager sparkManager;
	
	public SparkConnection() {
		Tbl = new ArrayList<Table>();
	}

	@Override
	protected void generateTableList() {
		String path = "InputFiles/" + inputFolder + "/" + cubeName;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		System.out.println(path);
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("\nReading file : " + FilenameUtils.removeExtension(listOfFiles[i].getName()));
			String attrs = FilenameUtils.removeExtension(listOfFiles[i].getName());
			String name =  attrs.substring(0, attrs.length() - 6);
			System.out.println("Table : " + name);
			Table tmp = new Table(name);
			tmp.setAttribute(attrs, path);
			System.out.println();
			this.Tbl.add(tmp);
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		System.out.println();
	}

	@Override
	public void registerCubeBase(HashMap<String, String> userInputList) {
		schemaName = userInputList.get("schemaName");
		inputFolder = userInputList.get("inputFolder");
		cubeName = userInputList.get("cubeName");
		registerSpark();
		generateTableList();
	}

	@Override
	public Result executeQueryToProduceResult(String queryString, Result result) {
		return sparkManager.executeQueryToSpark(queryString, result);
	}
	
	public void registerSpark() {
		sparkManager = new SparkManager(schemaName, inputFolder, cubeName);
	}

}
