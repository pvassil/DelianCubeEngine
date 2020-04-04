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

public class SparkAnswerQueryFromStringWithMetadataSuccess {
	
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
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueriesFromStringWithMetadata(String)}.
	 * @throws IOException 
	 */
	@Test
	public final void testanswerCubeQueryFromStringWithMetadata() throws IOException{

		String testQueryString1 = 
				"CubeName:sales\r\n" + 
				"Name:Spark_FromStringWithMetadata_Test_1\r\n" + 
				"AggrFunc:Avg\r\n" + 
				"Measure:sales\r\n" + 
				"Gamma:products_dim.lvl1,locations_dim.lvl0\r\n" + 
				"Sigma:dates_dim.lvl3='2'";

		testEngine.answerCubeQueryFromStringWithMetadata(testQueryString1);   /**/
		
		
		File output01 = new File("OutputFiles/Spark_FromFileWithMetadata_Test_1_info.txt");
		File reference01 = new File("src/test/resources/OutputFiles/10K-products/SparkFiles/Spark_FromFileWithMetadata_Test_1_info.tab");
        boolean check01 = FileUtils.contentEquals(output01, reference01);
        assertEquals(check01 , true);/**/
	}
}
