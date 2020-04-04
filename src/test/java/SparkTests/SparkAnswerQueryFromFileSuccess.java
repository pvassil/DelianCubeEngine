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

public class SparkAnswerQueryFromFileSuccess {

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
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueriesFromFile(java.io.File)}.
	 * @throws IOException 
	 */
	@Test
	public final void testAnswerCubeQueriesFromFile() throws IOException {
		
		File file = new File("src/test/resources/InputFiles/10K-products/spark_sales_test_queries.txt");
		testEngine.answerCubeQueriesFromFile(file);
		
		File output01 = new File("OutputFiles/Spark_FromFile_Test_1.tab");
		File reference01 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_1.tab");
		boolean check01 = FileUtils.contentEquals(output01, reference01);
		assertEquals(check01, true);
		
		File output02 = new File("OutputFiles/Spark_FromFile_Test_2.tab");
		File reference02 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_2.tab");
		boolean check02 = FileUtils.contentEquals(output02, reference02);
		assertEquals(check02, true);
		
		File output03 = new File("OutputFiles/Spark_FromFile_Test_3.tab");
		File reference03 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_3.tab");
		boolean check03 = FileUtils.contentEquals(output03, reference03);
		assertEquals(check03, true);
		
		File output04 = new File("OutputFiles/Spark_FromFile_Test_4.tab");
		File reference04 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_4.tab");
		boolean check04 = FileUtils.contentEquals(output04, reference04);
		assertEquals(check04, true);
		
		File output05 = new File("OutputFiles/Spark_FromFile_Test_5.tab");
		File reference05 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_5.tab");
		boolean check05 = FileUtils.contentEquals(output05, reference05);
		assertEquals(check05, true);
		
		File output06 = new File("OutputFiles/Spark_FromFile_Test_6.tab");
		File reference06 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_6.tab");
		boolean check06 = FileUtils.contentEquals(output06, reference06);
		assertEquals(check06, true);
		
		File output07 = new File("OutputFiles/Spark_FromFile_Test_7.tab");
		File reference07 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_7.tab");
		boolean check07 = FileUtils.contentEquals(output07, reference07);
		assertEquals(check07, true);
	}

}
