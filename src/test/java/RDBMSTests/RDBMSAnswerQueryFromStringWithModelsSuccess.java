package RDBMSTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import mainengine.IMainEngine;
import mainengine.SimpleQueryProcessorEngine;

public class RDBMSAnswerQueryFromStringWithModelsSuccess {

	private static IMainEngine testEngine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testEngine = new SimpleQueryProcessorEngine();
		String typeOfConnection = "RDBMS";
		
		HashMap<String, String> userInputList = new HashMap<>();
		userInputList.put("schemaName", "10K-products");
		userInputList.put("cubeName", "sales");
		userInputList.put("inputFolder", "10K-products");
		userInputList.put("username", "root");
		userInputList.put("password", "root");
		
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
				"Name:RDBMS_Models_Test_1\n" + 
				"AggrFunc:Avg\n" + 
				"Measure:sales\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl0\n" + 
				"Sigma:dates_dim.lvl3='2'";

		String [] modelsToGenerate01 = {"Rank","Outlier", "KMeansApache", "KPIMedianBased"};		
		testEngine.answerCubeQueryFromStringWithModels(testQueryString1, modelsToGenerate01);
		
		// Normal Output File
		File output01 = new File("OutputFiles/RDBMS_Models_Test_1.tab");
		File reference01 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1.tab");
		File soutput01 = orderLinesOfFile(output01);
		File sreference01 = orderLinesOfFile(reference01);
		boolean check01 = FileUtils.contentEquals(soutput01, sreference01);
		assertEquals(check01, true);

        // Normal Info File
		// Info File is RDBMS. Cant be the same with Spark Info File
//		File output02 = new File("OutputFiles/RDBMS_Models_Test_1_info.txt");
//		File reference02 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_info.txt");
//		boolean check02 = FileUtils.contentEquals(output02, reference02);
//		assertEquals(check02, true);
        
        // KMeans Apache - Probably will fail because the Cluster might change the Cluster order.
//		File output03 = new File("OutputFiles/RDBMS_Models_Test_1_KMeansApache.tab");
//		File reference03 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KMeansApache.tab");
//		File soutput03 = orderLinesOfFile(output03);
//		File sreference03 = orderLinesOfFile(reference03);
//		boolean check03 = FileUtils.contentEquals(soutput03, sreference03);
//		assertEquals(check03, true);
//        
        
        // KMeans Apache Info File
		File output04 = new File("OutputFiles/RDBMS_Models_Test_1_KMeansApache_info.txt");
		File reference04 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KMeansApache_info.txt");
		File soutput04 = orderLinesOfFile(output04);
		File sreference04 = orderLinesOfFile(reference04);
		boolean check04 = FileUtils.contentEquals(soutput04, sreference04);
		assertEquals(check04, true);
        
        // KPI Median Based Model
		File output05 = new File("OutputFiles/RDBMS_Models_Test_1_KPIMedianBasedModel.tab");
		File reference05 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KPIMedianBasedModel.tab");
		File soutput05 = orderLinesOfFile(output05);
		File sreference05 = orderLinesOfFile(reference05);
		boolean check05 = FileUtils.contentEquals(soutput05, sreference05);
		assertEquals(check05, true);
        
        // KPI Median Based Model Info file
		File output06 = new File("OutputFiles/RDBMS_Models_Test_1_KPIMedianBasedModel_info.txt");
		File reference06 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_KPIMedianBasedModel_info.txt");
		File soutput06 = orderLinesOfFile(output06);
		File sreference06 = orderLinesOfFile(reference06);
		boolean check06 = FileUtils.contentEquals(soutput06, sreference06);
		assertEquals(check06, true);
        
        // Ranks
		File output07 = new File("OutputFiles/RDBMS_Models_Test_1_Ranks.tab");
		File reference07 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Ranks.tab");
		File soutput07 = orderLinesOfFile(output07);
		File sreference07 = orderLinesOfFile(reference07);
		boolean check07 = FileUtils.contentEquals(soutput07, sreference07);
		assertEquals(check07, true);
        
        // Ranks Info File
		File output08 = new File("OutputFiles/RDBMS_Models_Test_1_Ranks_info.txt");
		File reference08 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Ranks_info.txt");
		File soutput08 = orderLinesOfFile(output08);
		File sreference08 = orderLinesOfFile(reference08);
		boolean check08 = FileUtils.contentEquals(soutput08, sreference08);
		assertEquals(check08, true);
        
        // Z-Score Outlier
		File output09 = new File("OutputFiles/RDBMS_Models_Test_1_Z-Score_Outliers.tab");
		File reference09 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Z-Score_Outliers.tab");
		File soutput09 = orderLinesOfFile(output09);
		File sreference09 = orderLinesOfFile(reference09);
		boolean check09 = FileUtils.contentEquals(soutput09, sreference09);
		assertEquals(check09, true);
        
        // Z-Score Outlier Info File
		File output10 = new File("OutputFiles/RDBMS_Models_Test_1_Z-Score_Outliers_info.txt");
		File reference10 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_Models_Test_1_Z-Score_Outliers_info.txt");
		File soutput10 = orderLinesOfFile(output10);
		File sreference10 = orderLinesOfFile(reference10);
		boolean check10 = FileUtils.contentEquals(soutput10, sreference10);
		assertEquals(check10, true);
        
	}
	
	public File orderLinesOfFile(File input) throws IOException{

		String inputFile = input.getName();
		String outputFile = "O" + input.getName();
		
		FileReader fileReader = new FileReader(input);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String inputLine;
		List<String> lineList = new ArrayList<String>();
		while ((inputLine = bufferedReader.readLine()) != null) {
			lineList.add(inputLine);
		}
		fileReader.close();

		Collections.sort(lineList);

		FileWriter fileWriter = new FileWriter(outputFile);
		PrintWriter out = new PrintWriter(fileWriter);
		for (String outputLine : lineList) {
			out.println(outputLine);
		}
		out.flush();
		out.close();
		fileWriter.close();
		return new File(outputFile);
	}
}
