import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import RDBMSTests.RDBMSAnswerQueryFromFileSuccess;
import RDBMSTests.RDBMSAnswerQueryFromStringSuccess;
import RDBMSTests.RDBMSAnswerQueryFromStringWithMetadataSuccess;
import RDBMSTests.RDBMSAnswerQueryFromStringWithModelsSuccess;
import RDBMSTests.RDBMSInitializeTestsFail;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RDBMSInitializeTestsFail.class,								//test case 1
        RDBMSAnswerQueryFromFileSuccess.class,						//test case 2
        RDBMSAnswerQueryFromStringSuccess.class,					//test case 3
        RDBMSAnswerQueryFromStringWithMetadataSuccess.class,		//test case 4
        RDBMSAnswerQueryFromStringWithModelsSuccess.class			//test case 5
})
public class RDBMSSuiteTest {
	//normally, this is an empty class
}

