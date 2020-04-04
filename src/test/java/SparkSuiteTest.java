import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import SparkTests.SparkAnswerQueryFromFileSuccess;
import SparkTests.SparkAnswerQueryFromStringSuccess;
import SparkTests.SparkAnswerQueryFromStringWithMetadataSuccess;
import SparkTests.SparkAnswerQueryFromStringWithModelsSuccess;
import SparkTests.SparkInitializeTestsFail;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SparkInitializeTestsFail.class,								//test case 1
        SparkAnswerQueryFromFileSuccess.class,						//test case 2
        SparkAnswerQueryFromStringSuccess.class,					//test case 3
        SparkAnswerQueryFromStringWithMetadataSuccess.class,		//test case 4
        SparkAnswerQueryFromStringWithModelsSuccess.class			//test case 5
})
public class SparkSuiteTest {
	//normally, this is an empty class
}

