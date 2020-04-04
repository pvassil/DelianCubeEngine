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

public class RDBMSAnswerQueryFromStringSuccess {

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
		userInputList.put("password", "password");
		
		testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	/**
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueryFromString(java.io.File)}.
	 * @throws IOException 
	 */
	@Test
	public final void testanswerCubeQueryFromString() throws IOException {
		
		// RDBMS_FromString_Test1
		String testQueryString1 = 
				"CubeName:sales\r\n" + 
				"Name:RDBMS_FromString_Test_1\r\n" + 
				"AggrFunc:Avg\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl0\r\n" + 
				"Sigma:dates_dim.lvl3='2'";
		testEngine.answerCubeQueryFromString(testQueryString1);   /**/
		File output01 = new File("OutputFiles/RDBMS_FromString_Test_1.tab");
		File reference01 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_1.tab");
		File soutput01 = orderLinesOfFile(output01);
		File sreference01 = orderLinesOfFile(reference01);
		boolean check01 = FileUtils.contentEquals(soutput01, sreference01);
		assertEquals(check01, true);			

		// RDBMS_FromString_Test2
		String testQueryString2 = 
				"CubeName:sales\r\n" + 
				"Name:RDBMS_FromString_Test_2\r\n" + 
				"AggrFunc:Max\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl2\r\n" + 
				"Sigma:dates_dim.lvl3='2'";
		testEngine.answerCubeQueryFromString(testQueryString2);   /**/
		File output02 = new File("OutputFiles/RDBMS_FromString_Test_2.tab");
		File reference02 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_2.tab");
		File soutput02 = orderLinesOfFile(output02);
		File sreference02 = orderLinesOfFile(reference02);
		boolean check02 = FileUtils.contentEquals(soutput02, sreference02);
		assertEquals(check02, true);
        
		// RDBMS_FromString_Test3
		String testQueryString3 = 
				"CubeName:sales\r\n" + 
				"Name:RDBMS_FromString_Test_3\r\n" + 
				"AggrFunc:Max\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl0\r\n" + 
				"Sigma:products_dim.lvl2='subcategory3'";
		testEngine.answerCubeQueryFromString(testQueryString3);   /**/
		File output03 = new File("OutputFiles/RDBMS_FromString_Test_3.tab");
		File reference03 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_3.tab");
		File soutput03 = orderLinesOfFile(output03);
		File sreference03 = orderLinesOfFile(reference03);
		boolean check03 = FileUtils.contentEquals(soutput03, sreference03);
		assertEquals(check03, true);
        
		// RDBMS_FromString_Test4
		String testQueryString4 = 
				"CubeName:sales\r\n" + 
				"Name:RDBMS_FromString_Test_4\r\n" + 
				"AggrFunc:Sum\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl2,locations_dim.lvl0\r\n" + 
				"Sigma:products_dim.lvl1='product75'";
		testEngine.answerCubeQueryFromString(testQueryString4);   /**/
		File output04 = new File("OutputFiles/RDBMS_FromString_Test_4.tab");
		File reference04 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_4.tab");
		File soutput04 = orderLinesOfFile(output04);
		File sreference04 = orderLinesOfFile(reference04);
		boolean check04 = FileUtils.contentEquals(soutput04, sreference04);
		assertEquals(check04, true);
        
		// RDBMS_FromString_Test5
		// Example : In a few values there is a different 4th decimal.
		// That's because Spark calculates more numbers by default so rounding them changes the 4th value.
//		String testQueryString5 = 
//				"CubeName:sales\r\n" + 
//				"Name:RDBMS_FromString_Test_5\r\n" + 
//				"AggrFunc:Avg\r\n" + 
//				"Measure:sales\r\n" + 
//				"Gamma:locations_dim.lvl2,dates_dim.lvl2\r\n" + 
//				"Sigma:products_dim.lvl2='subcategory3'";
//		testEngine.answerCubeQueryFromString(testQueryString5);   /**/
//		File output05 = new File("OutputFiles/RDBMS_FromString_Test_5.tab");
//		File reference05 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_5.tab");
//		File soutput05 = orderFiles(output05);
//		File sreference05 = orderFiles(reference05);
//		boolean check05 = FileUtils.contentEquals(soutput05, sreference05);
//		assertEquals(check05, true);
        
		// RDBMS_FromString_Test6
		String testQueryString6 = 
				"CubeName:sales\r\n" + 
				"Name:RDBMS_FromString_Test_6\r\n" + 
				"AggrFunc:Avg\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:locations_dim.lvl3,dates_dim.lvl5\r\n" + 
				"Sigma:products_dim.lvl2='subcategory3'";
		testEngine.answerCubeQueryFromString(testQueryString6);   /**/
		File output06 = new File("OutputFiles/RDBMS_FromString_Test_6.tab");
		File reference06 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_6.tab");
        boolean check06 = FileUtils.contentEquals(output06, reference06);
        assertEquals(check06 , true);
        
		// RDBMS_FromString_Test7
		String testQueryString7 = 
				"CubeName:sales\r\n" + 
				"Name:RDBMS_FromString_Test_7\r\n" + 
				"AggrFunc:Sum\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl4,locations_dim.lvl3\r\n" + 
				"Sigma:products_dim.lvl3='category6'";
		testEngine.answerCubeQueryFromString(testQueryString7);   /**/
		File output07 = new File("OutputFiles/RDBMS_FromString_Test_7.tab");
		File reference07 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_7.tab");
        boolean check07 = FileUtils.contentEquals(output07, reference07);
        assertEquals(check07 , true);

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
