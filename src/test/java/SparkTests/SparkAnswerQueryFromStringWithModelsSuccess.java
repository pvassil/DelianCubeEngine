package SparkTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import mainengine.IMainEngine;
import mainengine.SimpleQueryProcessorEngine;

public class SparkAnswerQueryFromStringWithModelsSuccess {

	private static IMainEngine testEngine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testEngine = new SimpleQueryProcessorEngine();
		String typeOfConnection = "Spark";
		
		HashMap<String, String> userInputList = new HashMap<>();
		userInputList.put("schemaName", "10K-products");
		userInputList.put("cubeName", "sales");
		userInputList.put("inputFolder", "10K-products");
		
		testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	/**
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueryFromStringWithModels(String, String [])}.
	 * @throws IOException 
	 */
	@Test
	public final void testanswerCubeQueryFromStringWithModels() throws IOException {
		String testQueryString1 =
				"CubeName:sales\n" + 
				"Name:Spark_Models_Test_1\n" + 
				"AggrFunc:Avg\n" + 
				"Measure:sales\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl0\n" + 
				"Sigma:dates_dim.lvl3='2'";

		String [] modelsToGenerate01 = {"Rank","Outlier", "KMeansApache", "KPIMedianBased"};		
		testEngine.answerCubeQueryFromStringWithModels(testQueryString1, modelsToGenerate01);
		
		// Normal Output File
		File output01 = new File("OutputFiles/Spark_Models_Test_1.tab");
		File reference01 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1.tab");
        boolean check01 = FileUtils.contentEquals(output01, reference01);
        assertEquals(check01 , true);

        // Normal Info File
		File output02 = new File("OutputFiles/Spark_Models_Test_1_info.txt");
		File reference02 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_info.txt");
        boolean check02 = FileUtils.contentEquals(output02, reference02);
        assertEquals(check02 , true);
        
        // KMeans Apache - Probably will fail because the Cluster might change the Cluster order.
//		File output03 = new File("OutputFiles/Spark_Models_Test_1_KMeansApache.tab");
//		File reference03 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KMeansApache.tab");
//		boolean check03 = FileUtils.contentEquals(output03, reference03);
//		assertEquals(check03 , true);
//        
        
        // KMeans Apache Info File
		File output04 = new File("OutputFiles/Spark_Models_Test_1_KMeansApache_info.txt");
		File reference04 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KMeansApache_info.txt");
        boolean check04 = FileUtils.contentEquals(output04, reference04);
        assertEquals(check04 , true);
        
        // KPI Median Based Model
		File output05 = new File("OutputFiles/Spark_Models_Test_1_KPIMedianBasedModel.tab");
		File reference05 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KPIMedianBasedModel.tab");
        boolean check05 = FileUtils.contentEquals(output05, reference05);
        assertEquals(check05 , true);
        
        // KPI Median Based Model Info file
		File output06 = new File("OutputFiles/Spark_Models_Test_1_KPIMedianBasedModel_info.txt");
		File reference06 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KPIMedianBasedModel_info.txt");
        boolean check06 = FileUtils.contentEquals(output06, reference06);
        assertEquals(check06 , true);
        
        // Ranks
		File output07 = new File("OutputFiles/Spark_Models_Test_1_Ranks.tab");
		File reference07 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Ranks.tab");
        boolean check07 = FileUtils.contentEquals(output07, reference07);
        assertEquals(check07 , true);
        
        // Ranks Info File
		File output08 = new File("OutputFiles/Spark_Models_Test_1_Ranks_info.txt");
		File reference08 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Ranks_info.txt");
        boolean check08 = FileUtils.contentEquals(output08, reference08);
        assertEquals(check08 , true);
        
        // Z-Score Outlier
		File output09 = new File("OutputFiles/Spark_Models_Test_1_Z-Score_Outliers.tab");
		File reference09 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Z-Score_Outliers.tab");
        boolean check09 = FileUtils.contentEquals(output09, reference09);
        assertEquals(check09 , true);
        
        // Z-Score Outlier Info File
		File output10 = new File("OutputFiles/Spark_Models_Test_1_Z-Score_Outliers_info.txt");
		File reference10 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Z-Score_Outliers_info.txt");
        boolean check10 = FileUtils.contentEquals(output10, reference10);
        assertEquals(check10 , true);
        
	}
}
