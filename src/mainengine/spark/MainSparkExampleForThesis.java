package mainengine.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MainSparkExampleForThesis {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		System.setProperty("hadoop.home.dir", "c:/hadoop");
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark Session")
				.master("local[*]")
				.config("spark.sql.warehouse.dir", "file:///c:/tmp/")
				.getOrCreate();
		
		Dataset<Row> df = spark.read()
				.format("csv")
				.option("sep", ",")
				.option("inferSchema", "true")
				.option("header", "true")
				.load("C:\\Users\\kosta\\Desktop\\mileStone1\\DelianCubeEngine\\InputFiles\\products\\products.csv");
		
		df.show();
		df.printSchema();
		
		df.createOrReplaceTempView("products");

		Dataset<Row> sqlDF = spark.sql("SELECT * FROM products WHERE category = 'category1'");
		sqlDF.show();
		
		
//+----------+---------+------+------------+---------+
//|product_id|     name| price| subcategory| category|
//+----------+---------+------+------------+---------+
//|         0|product53|205.45|subcategory4|category1|
//|         9|product36|135.11|subcategory3|category1|
//+----------+---------+------+------------+---------+
		spark.close();
	}
}
