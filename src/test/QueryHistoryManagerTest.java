package test;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import cubemanager.cubebase.CubeQuery;
import cubemanager.cubebase.QueryHistoryManager;
import mainengine.SessionQueryProcessorEngine;

public class QueryHistoryManagerTest {

	private static SessionQueryProcessorEngine queryEngine;
	private static QueryHistoryManager historyMng;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		queryEngine = new SessionQueryProcessorEngine(); 
		
		queryEngine.initializeConnection("pkdd99", "CinecubesUser",
				"Cinecubes", "pkdd99","loan");
		
		historyMng = queryEngine.getQueryHistoryMng();
		
		queryEngine.answerCubeQueryFromString("CubeName:loan\n" + 
				"Name: LoanQuery11_S1_CG-Prtl\n" + 
				"AggrFunc:Avg\n" + 
				"Measure:amount\n" + 
				"Gamma:account_dim.lvl1,date_dim.lvl2\n" + 
				"Sigma:account_dim.lvl2='north Moravia'");
		queryEngine.answerCubeQueryFromString("CubeName:loan\n" + 
				"Name: LoanQuery31_S3_CG-Prtl\n" + 
				"AggrFunc:Sum\n" + 
				"Measure:amount\n" + 
				"Gamma:account_dim.lvl1,date_dim.lvl3\n" + 
				"Sigma:account_dim.lvl2='west Bohemia',status_dim.lvl0='Contract Finished/No Problems', date_dim.lvl3 = '1996'");

	}
	
	@Test
	public void sessionIdExistanceTest() {
		Assert.assertNotNull(historyMng.getSessionId());
	}
	
	@Test
	public void queryHistorySizeTest() {
		List<CubeQuery> history = historyMng.getQueryHistory();
		Assert.assertEquals(history.size(), 2);
	}
	
	@Test
	public void getLastQueryTest() {
		CubeQuery lastQuery = historyMng.getLastQuery();
		Assert.assertEquals(lastQuery.getName(), "LoanQuery31_S3_CG-Prtl");
		Assert.assertEquals(lastQuery.getAggregateFunction(), "Sum");
		Assert.assertEquals(lastQuery.getListMeasure().get(0).getName(), "amount");
		String[] gamma0 = lastQuery.getGammaExpressions().get(0);
		Assert.assertEquals(gamma0[0], "account_dim");
		Assert.assertEquals(gamma0[1], "lvl1");
		String[] sigma0 = lastQuery.getSigmaExpressions().get(0);
		Assert.assertEquals(sigma0[0], "account_dim.lvl2");
		Assert.assertEquals(sigma0[1], "=");
		Assert.assertEquals(sigma0[2], "'west Bohemia'");
	}
	
	@Test
	public void getQueryByName() {
		CubeQuery query = historyMng.getQueryByName("LoanQuery31_S3_CG-Prtl");
		Assert.assertEquals(query, historyMng.getLastQuery());
		Assert.assertEquals(query, historyMng.getQueryHistory().get(1));
	}
	
	@Test
	public void addAndDeleteQueriesTest() throws RemoteException {
		Assert.assertEquals(historyMng.getQueryHistory().size(), 2);
		queryEngine.answerCubeQueryFromString("CubeName:loan\n" + 
				"Name: Query3\n" + 
				"AggrFunc:Sum\n" + 
				"Measure:amount\n" + 
				"Gamma:account_dim.lvl1,date_dim.lvl3\n" + 
				"Sigma:account_dim.lvl2='west Bohemia',status_dim.lvl0='Contract Finished/No Problems', date_dim.lvl3 = '1996'");
		Assert.assertEquals(historyMng.getQueryHistory().size(), 3);
		Assert.assertEquals(historyMng.getLastQuery().getName(), "Query3");
		CubeQuery query3 = historyMng.getQueryByName("Query3");
		historyMng.deleteQuery(query3);
		Assert.assertEquals(historyMng.getQueryHistory().size(), 2);
		Assert.assertEquals(historyMng.getLastQuery().getName(), "LoanQuery31_S3_CG-Prtl");
	}
}
