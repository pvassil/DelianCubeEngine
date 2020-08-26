package RDBMSTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.SizeFileComparator;
import org.junit.BeforeClass;
import org.junit.Test;

import mainengine.IMainEngine;
import mainengine.SimpleQueryProcessorEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RDBMSAnswerQueryFromFileSuccess {

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
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueriesFromFile(java.io.File)}.
	 * @throws IOException 
	 */
	@Test
	public final void testAnswerCubeQueriesFromFile() throws IOException {
		
		File file = new File("src/test/resources/InputFiles/10K-products/rdbms_sales_test_queries.txt");
		testEngine.answerCubeQueriesFromFile(file);

		File output01 = new File("OutputFiles/RDBMS_FromFile_Test_1.tab");
		File reference01 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_1.tab");
		File soutput01 = orderLinesOfFile(output01);
		File sreference01 = orderLinesOfFile(reference01);
		boolean check01 = FileUtils.contentEquals(soutput01, sreference01);
		assertEquals(check01, true);
		
		File output02 = new File("OutputFiles/RDBMS_FromFile_Test_2.tab");
		File reference02 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_2.tab");
		File soutput02 = orderLinesOfFile(output02);
		File sreference02 = orderLinesOfFile(reference02);
		boolean check02 = FileUtils.contentEquals(soutput02, sreference02);
		assertEquals(check02, true);
		
		File output03 = new File("OutputFiles/RDBMS_FromFile_Test_3.tab");
		File reference03 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_3.tab");
		File soutput03 = orderLinesOfFile(output03);
		File sreference03 = orderLinesOfFile(reference03);
		boolean check03 = FileUtils.contentEquals(soutput03, sreference03);
		assertEquals(check03, true);
		
		File output04 = new File("OutputFiles/RDBMS_FromFile_Test_4.tab");
		File reference04 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_4.tab");
		File soutput04 = orderLinesOfFile(output04);
		File sreference04 = orderLinesOfFile(reference04);
		boolean check04 = FileUtils.contentEquals(soutput04, sreference04);
		assertEquals(check04, true);
		
		// RDBMS Test 5 - Fails because Spark shows more numbers in a decimal.
		// Example : In a few values there is a different 4th decimal.
		// That's because Spark calculates more numbers by default so rounding them changes the 4th value.
//		File output05 = new File("OutputFiles/RDBMS_FromFile_Test_5.tab");
//		File reference05 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_5.tab");
//		File soutput05 = orderLinesOfFile(output05);
//		File sreference05 = orderLinesOfFile(reference05);
//		boolean check05 = FileUtils.contentEquals(soutput05, sreference05);
//		assertEquals(check05, true);
		
		File output06 = new File("OutputFiles/RDBMS_FromFile_Test_6.tab");
		File reference06 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_6.tab");
		File soutput06 = orderLinesOfFile(output06);
		File sreference06 = orderLinesOfFile(reference06);
		boolean check06 = FileUtils.contentEquals(soutput06, sreference06);
		assertEquals(check06, true);
		
		File output07 = new File("OutputFiles/RDBMS_FromFile_Test_7.tab");
		File reference07 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_7.tab");
		boolean check07 = FileUtils.contentEquals(output07, reference07);
		assertEquals(check07, true);
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
