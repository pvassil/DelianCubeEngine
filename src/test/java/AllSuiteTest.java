import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	SparkSuiteTest.class,
	RDBMSSuiteTest.class
})
public class AllSuiteTest {
	//normally, this is an empty class
}