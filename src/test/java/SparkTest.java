/*
 * JUnit4 tests for the Spark implementation.
 * By Konstantinos Kadoglou
 */

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import mainengine.IMainEngine;
import mainengine.SimpleQueryProcessorEngine;

public class SparkTest {
	
	private static IMainEngine testEngine;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testEngine = new SimpleQueryProcessorEngine();
		String typeOfConnection = "Spark";
		
		HashMap<String, String> userInputList = new HashMap<>();
		userInputList.put("schemaName", "pkdd99");
		userInputList.put("cubeName", "loan");
		userInputList.put("inputFolder", "pkdd99");
		
		testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	/**
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueriesFromFile(java.io.File)}.
	 * @throws IOException 
	 */
	@Test
	public final void testAnswerCubeQueriesFromFile() throws IOException {
		
		File file = new File("src/test/InputFiles/pkdd99/Six-Queries-Sample.txt");
		testEngine.answerCubeQueriesFromFile(file);
		
		File output01 = new File("OutputFiles/Query-Test1.tab");
		File reference01 = new File("src/test/OutputFiles/pkdd99/Reference_Query-Test1.tsv");
		boolean check01 = FileUtils.contentEquals(output01, reference01);
		assertEquals(check01, true);
		
		File output02 = new File("OutputFiles/Query-Test2.tab");
		File reference02 = new File("src/test/OutputFiles/pkdd99/Reference_Query-Test2.tsv");
		System.out.println(reference02.canRead());
		boolean check02 = FileUtils.contentEquals(output02, reference02);
		assertEquals(check02, true);
		
		File output03 = new File("OutputFiles/Query-Test3.tab");
		File reference03 = new File("src/test/OutputFiles/pkdd99/Reference_Query-Test3.tsv");
		boolean check03 = FileUtils.contentEquals(output03, reference03);
		assertEquals(check03, true);
		
		File output04 = new File("OutputFiles/Query-Test4.tab");
		File reference04 = new File("src/test/OutputFiles/pkdd99/Reference_Query-Test4.tsv");
		boolean check04 = FileUtils.contentEquals(output04, reference04);
		assertEquals(check04, true);
		
		File output05 = new File("OutputFiles/Query-Test5.tab");
		File reference05 = new File("src/test/OutputFiles/pkdd99/Reference_Query-Test5.tsv");
		boolean check05 = FileUtils.contentEquals(output05, reference05);
		assertEquals(check05, true);
	    
		File output06 = new File("OutputFiles/Query-Test6.tab");
		File reference06 = new File("src/test/OutputFiles/pkdd99/Reference_Query-Test6.tsv");
		boolean check06 = FileUtils.contentEquals(output06, reference06);
		assertEquals(check06, true);
	}
	
	/**
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueryFromString(java.io.File)}.
	 * @throws IOException 
	 */
	@Test
	public final void testanswerCubeQueryFromString() throws IOException {
		//fail("Not yet implemented");
		// can try failures by modifying filenames and/or paths. See answerCQFromFILES for comments 

		//GIVE STH DIFFERENT
		String testQueryString1 = 
				"CubeName:loan" + " \n" +
						"Name:CubeQueryLoan1_FailTheTest" + " \n" +
						"AggrFunc:Avg" + " \n" +
						"Measure:amount" + " \n" +
						"Gamma:account_dim.lvl2,date_dim.lvl2" + " \n" +
						"Sigma:account_dim.lvl1='Liberec'";
		testEngine.answerCubeQueryFromString(testQueryString1);   /**/
		File fileProduced1 = new File("OutputFiles/CubeQueryLoan1_FailTheTest.tab");
		File fileReference1 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan1.tab");
        boolean comparison1 = FileUtils.contentEquals(fileProduced1, fileReference1);
		
        assertEquals(comparison1 , false);				

		//GIVE THE EXACT SAME QUERY
		String testQueryString2 = 
				"CubeName:loan" + " \n" +
				"Name:CubeQueryLoan2_Copy" + " \n" +
				"AggrFunc:Avg" + " \n" +
				"Measure:amount" + " \n" +
				"Gamma:account_dim.lvl2,date_dim.lvl2" + " \n" +
				"Sigma:account_dim.lvl1='Liberec',status_dim.lvl0='Running Contract/OK'";

		testEngine.answerCubeQueryFromString(testQueryString2);   /**/
		

		File fileProduced2 = new File("OutputFiles/CubeQueryLoan2_Copy.tab");
		File fileReference2 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan2-Spark.tab");
        boolean comparison2 = FileUtils.contentEquals(fileProduced2, fileReference2);
		
        assertEquals(comparison2 , true);/**/
        
	}//end testanswerCubeQueryFromString

	/**
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueriesFromStringWithMetadata(String)}.
	 * @throws IOException 
	 */
	@Test
	public final void testanswerCubeQueryFromStringWithMetadata() throws IOException{

		String testQueryString2 = 
				"CubeName:loan" + " \n" +
				"Name: CubeQueryLoan22_Copy" + " \n" +
				"AggrFunc:Sum" + " \n" +
				"Measure:amount" + " \n" +
				"Gamma:account_dim.lvl1,date_dim.lvl3" + " \n" +
				"Sigma:account_dim.lvl2='south Moravia',status_dim.lvl0='Running Contract/OK'";

		testEngine.answerCubeQueryFromStringWithMetadata(testQueryString2);   /**/
		
		
		File fileInfoProduced2 = new File("OutputFiles/CubeQueryLoan22_Copy_Info.txt");
		File fileInfoReference2 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan22_Info.txt");
        boolean comparison2 = FileUtils.contentEquals(fileInfoProduced2, fileInfoReference2);
        assertEquals(comparison2 , true);/**/
	}//end method testanswerCubeQueryFromStringWithMetadata


	/**
	 * Test method for {@link mainengine.SimpleQueryProcessorEngine#answerCubeQueryFromStringWithModels(String, String [])}.
	 * @throws IOException 
	 */
	@Test
	public final void testanswerCubeQueryFromStringWithModels() throws IOException {
		String queryForModels11 =		"CubeName:loan" + " \n" +
				"Name: CubeQueryLoan11_Prague" + " \n" +
				"AggrFunc:Sum" + " \n" +
				"Measure:amount" + " \n" +
				"Gamma:account_dim.lvl1,date_dim.lvl2" + " \n" +
				"Sigma:account_dim.lvl2='Prague'";
		String [] modelsToGenerate11 = {"Outlier"};	
		
		testEngine.answerCubeQueryFromStringWithModels(queryForModels11, modelsToGenerate11);
		
		File fileProduced_11_1 = new File("OutputFiles/CubeQueryLoan11_Prague.tab");
		File fileReference_11_1 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan11_Prague.tab");
        boolean comparison_11_1 = FileUtils.contentEquals(fileProduced_11_1, fileReference_11_1);
        assertEquals(comparison_11_1 , true);

        File fileProduced_11_2 = new File("OutputFiles/CubeQueryLoan11_Prague_Info.txt");
		File fileReference_11_2 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan11_Prague_Info.txt");
        boolean comparison_11_2 = FileUtils.contentEquals(fileProduced_11_2, fileReference_11_2);
        assertEquals(comparison_11_2 , true);

//        File fileProduced_11_31 = new File("OutputFiles/CubeQueryLoan11_Prague_Ranks.tab");
//		File fileReference_11_31 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan11_Prague_Ranks.tab");
//        boolean comparison_11_31 = FileUtils.contentEquals(fileProduced_11_31, fileReference_11_31);
//        assertEquals(comparison_11_31 , true);
        
        File fileProduced_11_32 = new File("OutputFiles/CubeQueryLoan11_Prague_Z-Score_Outliers.tab");
		File fileReference_11_32 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan11_Prague_Z-Score_Outliers.tab");
        boolean comparison_11_32 = FileUtils.contentEquals(fileProduced_11_32, fileReference_11_32);
        assertEquals(comparison_11_32 , true);
        
        /* *********** Now, a Loaded model generation ********** */
		String queryForModels12 =		"CubeName:loan" + " \n" +
				"Name: CubeQueryLoan12_Sum1998" + " \n" +
				"AggrFunc:Sum" + " \n" +
				"Measure:amount" + " \n" +
				"Gamma:account_dim.lvl1,status_dim.lvl1" + " \n" +
				"Sigma:date_dim.lvl2 = '1998-01'";
		String [] modelsToGenerate12 = {"Rank","Outlier", "KMeansApache", "KPIMedianBased"};	

		testEngine.answerCubeQueryFromStringWithModels(queryForModels12, modelsToGenerate12);

		File fileProduced_12_1 = new File("OutputFiles/CubeQueryLoan12_Sum1998.tab");
		File fileReference_12_1 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998.tab");
        boolean comparison_12_1 = FileUtils.contentEquals(fileProduced_12_1, fileReference_12_1);
        assertEquals(comparison_12_1 , true);

        File fileProduced_12_2 = new File("OutputFiles/CubeQueryLoan12_Sum1998_Info.txt");
		File fileReference_12_2 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_Info.txt");
        boolean comparison_12_2 = FileUtils.contentEquals(fileProduced_12_2, fileReference_12_2);
        assertEquals(comparison_12_2 , true);

        File fileProduced_12_31 = new File("OutputFiles/CubeQueryLoan12_Sum1998_Ranks.tab");
		File fileReference_12_31 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_Ranks.tab");
        boolean comparison_12_31 = FileUtils.contentEquals(fileProduced_12_31, fileReference_12_31);
        assertEquals(comparison_12_31, true);
        
        File fileProduced_12_32 = new File("OutputFiles/CubeQueryLoan12_Sum1998_Z-Score_Outliers.tab");
		File fileReference_12_32 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_Z-Score_Outliers.tab");
        boolean comparison_12_32 = FileUtils.contentEquals(fileProduced_12_32, fileReference_12_32);
        assertEquals(comparison_12_32 , true);

//        File fileProduced_12_33 = new File("OutputFiles/CubeQueryLoan12_Sum1998_KMeansApache.tab");
//		File fileReference_12_33 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_KMeansApache.tab");
//        boolean comparison_12_33 = FileUtils.contentEquals(fileProduced_12_33, fileReference_12_33);
//        assertEquals(comparison_12_33 , true);
//        //First of all this is K-means => it can possibly deviate between executions, although after so many iterations this originally seemed improbable...
//        //Second, even if the clusters are all right, the test can still fail because of the following issue: 
//        //the reference clustering assigns Cluster 2 to the (single) last value and Cluster 3 to the values in the middle of the list
//        //The produced clustering might change the order and assign the (single) last value to Cluster 3 instead and the rest to Cluster 2
        
        File fileProduced_12_34 = new File("OutputFiles/CubeQueryLoan12_Sum1998_KPIMedianBasedModel.tab");
		File fileReference_12_34 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_KPIMedianBasedModel.tab");
        boolean comparison_12_34 = FileUtils.contentEquals(fileProduced_12_34, fileReference_12_34);
        assertEquals(comparison_12_34 , true);
        
        /* *********** Now, NO model => Generate ranks and outliers ********** */
		String queryForModels12_2 =		"CubeName:loan" + " \n" +
				"Name: CubeQueryLoan12_Sum1998_2" + " \n" +
				"AggrFunc:Sum" + " \n" +
				"Measure:amount" + " \n" +
				"Gamma:account_dim.lvl1,status_dim.lvl1" + " \n" +
				"Sigma:date_dim.lvl2 = '1998-02'";
		String [] modelsToGenerate12_2 = {};	

		testEngine.answerCubeQueryFromStringWithModels(queryForModels12_2, modelsToGenerate12_2);
		File fileProduced_12_1_2 = new File("OutputFiles/CubeQueryLoan12_Sum1998_2.tab");
		File fileReference_12_1_2 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_2.tab");
        boolean comparison_12_1_2 = FileUtils.contentEquals(fileProduced_12_1_2, fileReference_12_1_2);
        assertEquals(comparison_12_1_2 , true);

        File fileProduced_12_2_2 = new File("OutputFiles/CubeQueryLoan12_Sum1998_2_Info.txt");
		File fileReference_12_2_2 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_2_Info.txt");
        boolean comparison_12_2_2 = FileUtils.contentEquals(fileProduced_12_2_2, fileReference_12_2_2);
        assertEquals(comparison_12_2_2 , true);

        File fileProduced_12_2_31 = new File("OutputFiles/CubeQueryLoan12_Sum1998_2_Ranks.tab");
		File fileReference_12_2_31 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_2_Ranks.tab");
        boolean comparison_12_2_31 = FileUtils.contentEquals(fileProduced_12_2_31, fileReference_12_2_31);
        assertEquals(comparison_12_2_31 , true);
        
        File fileProduced_12_2_32 = new File("OutputFiles/CubeQueryLoan12_Sum1998_2_Z-Score_Outliers.tab");
		File fileReference_12_2_32 = new File("src/test/OutputFiles/pkdd99/Reference_CubeQueryLoan12_Sum1998_2_Z-Score_Outliers.tab");
        boolean comparison_12_2_32 = FileUtils.contentEquals(fileProduced_12_2_32, fileReference_12_2_32);
        assertEquals(comparison_12_2_32 , true);
	}//end testanswerCubeQueryFromStringWithModels
}
