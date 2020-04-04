package SparkTests;

import java.rmi.RemoteException;
import java.util.HashMap;

import org.junit.Test;

import mainengine.IMainEngine;
import mainengine.SimpleQueryProcessorEngine;

public class SparkInitializeTestsFail {

	private static IMainEngine testEngine;

	@Test(expected = IllegalArgumentException.class)
	public void typeOfConnectionIllegalArgumentException() throws RemoteException {
			testEngine = new SimpleQueryProcessorEngine();
			String typeOfConnection = "wrong";
			HashMap<String, String> userInputList = new HashMap<>();
			userInputList.put("schemaName", "10K-products");
			userInputList.put("cubeName", "sales");
			userInputList.put("inputFolder", "10K-products");
			testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	@Test(expected = NullPointerException.class)
	public void cubeNameNullPointerException() throws RemoteException {
			testEngine = new SimpleQueryProcessorEngine();
			String typeOfConnection = "Spark";
			HashMap<String, String> userInputList = new HashMap<>();
			userInputList.put("schemaName", "10K-products");
			userInputList.put("cubeName", "wrong");
			userInputList.put("inputFolder", "10K-products");
			testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	@Test(expected = NullPointerException.class)
	public void inputFolderNullPointerException() throws RemoteException {
			testEngine = new SimpleQueryProcessorEngine();
			String typeOfConnection = "Spark";
			HashMap<String, String> userInputList = new HashMap<>();
			userInputList.put("schemaName", "10K-products");
			userInputList.put("cubeName", "sales");
			userInputList.put("inputFolder", "wrong");
			testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void schemaNameIOException () throws RemoteException {
		testEngine = new SimpleQueryProcessorEngine();
		String typeOfConnection = "Spark";
		HashMap<String, String> userInputList = new HashMap<>();
		userInputList.put("schemaName", "wrong");
		userInputList.put("cubeName", "sales");
		userInputList.put("inputFolder", "10K-products");
		testEngine.initializeConnection(typeOfConnection, userInputList);
	}

}
