package RDBMSTests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

import org.junit.Test;

import mainengine.IMainEngine;
import mainengine.SimpleQueryProcessorEngine;

public class RDBMSInitializeTestsFail {

	private static IMainEngine testEngine;

	@Test(expected = IllegalArgumentException.class)
	public void typeOfConnectionIllegalArgumentException() throws RemoteException {
			testEngine = new SimpleQueryProcessorEngine();
			String typeOfConnection = "wrong";
			HashMap<String, String> userInputList = new HashMap<>();
			userInputList.put("schemaName", "10K-products");
			userInputList.put("cubeName", "sales");
			userInputList.put("inputFolder", "10K-products");
			userInputList.put("username", "root");
			userInputList.put("password", "password");
			testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	@Test(expected = java.lang.RuntimeException.class)
	public void cubeNameNullPointerException() throws RemoteException {
			testEngine = new SimpleQueryProcessorEngine();
			String typeOfConnection = "RDBMS";
			HashMap<String, String> userInputList = new HashMap<>();
			userInputList.put("schemaName", "10K-products");
			userInputList.put("cubeName", "wrong");
			userInputList.put("inputFolder", "10K-products");
			userInputList.put("username", "root");
			userInputList.put("password", "password");
			testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	@Test(expected = java.lang.RuntimeException.class)
	public void inputFolderNullPointerException() throws RemoteException {
			testEngine = new SimpleQueryProcessorEngine();
			String typeOfConnection = "RDBMS";
			HashMap<String, String> userInputList = new HashMap<>();
			userInputList.put("schemaName", "10K-products");
			userInputList.put("cubeName", "sales");
			userInputList.put("inputFolder", "wrong");
			userInputList.put("username", "root");
			userInputList.put("password", "password");
			testEngine.initializeConnection(typeOfConnection, userInputList);
	}
	
	@Test(expected = java.lang.Exception.class)
	public void schemaNameIOException () throws RemoteException {
		testEngine = new SimpleQueryProcessorEngine();
		String typeOfConnection = "RDBMS";
		HashMap<String, String> userInputList = new HashMap<>();
		userInputList.put("schemaName", "wrong");
		userInputList.put("cubeName", "sales");
		userInputList.put("inputFolder", "10K-products");
		userInputList.put("username", "root");
		userInputList.put("password", "password");
		testEngine.initializeConnection(typeOfConnection, userInputList);
	}

}
