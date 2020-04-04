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

public class SparkAnswerQueryFromStringSuccess {

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
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueryFromString(java.io.File)}.
	 * @throws IOException 
	 */
	@Test
	public final void testanswerCubeQueryFromString() throws IOException {
		
		// Spark_FromString_Test1
		String testQueryString1 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromString_Test_1\r\n" + 
				"AggrFunc:Avg\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl0\r\n" + 
				"Sigma:dates_dim.lvl3='2'";
		testEngine.answerCubeQueryFromString(testQueryString1);   /**/
		File output01 = new File("OutputFiles/Spark_FromString_Test_1.tab");
		File reference01 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_1.tab");
        boolean check01 = FileUtils.contentEquals(output01, reference01);
        assertEquals(check01 , true);				

		// Spark_FromString_Test2
		String testQueryString2 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromString_Test_2\r\n" + 
				"AggrFunc:Max\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl2\r\n" + 
				"Sigma:dates_dim.lvl3='2'";
		testEngine.answerCubeQueryFromString(testQueryString2);   /**/
		File output02 = new File("OutputFiles/Spark_FromString_Test_2.tab");
		File reference02 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_2.tab");
        boolean check02 = FileUtils.contentEquals(output02, reference02);
        assertEquals(check02 , true);
        
		// Spark_FromString_Test3
		String testQueryString3 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromString_Test_3\r\n" + 
				"AggrFunc:Max\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl0\r\n" + 
				"Sigma:products_dim.lvl2='subcategory3'";
		testEngine.answerCubeQueryFromString(testQueryString3);   /**/
		File output03 = new File("OutputFiles/Spark_FromString_Test_3.tab");
		File reference03 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_3.tab");
        boolean check03 = FileUtils.contentEquals(output03, reference03);
        assertEquals(check03 , true);
        
		// Spark_FromString_Test4
		String testQueryString4 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromString_Test_4\r\n" + 
				"AggrFunc:Sum\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl2,locations_dim.lvl0\r\n" + 
				"Sigma:products_dim.lvl1='product75'";
		testEngine.answerCubeQueryFromString(testQueryString4);   /**/
		File output04 = new File("OutputFiles/Spark_FromString_Test_1.tab");
		File reference04 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_4.tab");
        boolean check04 = FileUtils.contentEquals(output04, reference04);
        assertEquals(check01 , true);
        
		// Spark_FromString_Test5
		String testQueryString5 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromString_Test_5\r\n" + 
				"AggrFunc:Avg\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:locations_dim.lvl2,dates_dim.lvl2\r\n" + 
				"Sigma:products_dim.lvl2='subcategory3'";
		testEngine.answerCubeQueryFromString(testQueryString5);   /**/
		File output05 = new File("OutputFiles/Spark_FromString_Test_5.tab");
		File reference05 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_5.tab");
        boolean check05 = FileUtils.contentEquals(output05, reference05);
        assertEquals(check05 , true);
        
		// Spark_FromString_Test6
		String testQueryString6 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromString_Test_6\r\n" + 
				"AggrFunc:Avg\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:locations_dim.lvl3,dates_dim.lvl5\r\n" + 
				"Sigma:products_dim.lvl2='subcategory3'";
		testEngine.answerCubeQueryFromString(testQueryString6);   /**/
		File output06 = new File("OutputFiles/Spark_FromString_Test_6.tab");
		File reference06 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_6.tab");
        boolean check06 = FileUtils.contentEquals(output06, reference06);
        assertEquals(check06 , true);
        
		// Spark_FromString_Test7
		String testQueryString7 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromString_Test_7\r\n" + 
				"AggrFunc:Sum\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl4,locations_dim.lvl3\r\n" + 
				"Sigma:products_dim.lvl3='category6'";
		testEngine.answerCubeQueryFromString(testQueryString7);   /**/
		File output07 = new File("OutputFiles/Spark_FromString_Test_7.tab");
		File reference07 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFile_Test_7.tab");
        boolean check07 = FileUtils.contentEquals(output07, reference07);
        assertEquals(check07 , true);

	}

}
