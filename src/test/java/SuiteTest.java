import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RDBMSTest.class, //test case 1
        SparkTest.class     //test case 2
})
public class SuiteTest {
	//normally, this is an empty class
}

