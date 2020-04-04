/*
*    DelianCubeEngine. A simple cube query engine.
*    Copyright (C) 2018  Panos Vassiliadis
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU Affero General Public License as published
*    by the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU Affero General Public License for more details.
*
*    You should have received a copy of the GNU Affero General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*
*/


package mainengine;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//import java.sql.ResultSet;

import org.apache.commons.lang.StringUtils;

import cubemanager.CubeManager;
import cubemanager.cubebase.CubeQuery;
import mainengine.rmiTransfer.RMIInputStream;
import mainengine.rmiTransfer.RMIInputStreamImpl;
import mainengine.rmiTransfer.RMIOutputStream;
import mainengine.rmiTransfer.RMIOutputStreamImpl;
//import exctractionmethod.SqlQuery;
/*
import filecreation.FileMgr;
import filecreation.PptxFile;
import filecreation.WordFile;
*/
import parsermgr.PathFolder;
//import storymgr.FinResult;
//import storymgr.StoryMgr;
import result.Result;
import setup.ModeOfWork;
import setup.ModeOfWork.WorkMode;


/**
 * @author pvassil
 *
 * USed to be MainEngine in Cinecubes. Refactored to address the needs of SimpleOLAP Engine.
 * The class is the main server component of answering _cube_ queries.
 * 
 * Use {@link #answerCubeQueriesFromFile(File file)} to answer the queries contained in a file 
 */
//@SuppressWarnings("serial")
public class SimpleQueryProcessorEngine extends UnicastRemoteObject implements IMainEngine {

	private static final long serialVersionUID = 313553263459485366L;
	private CubeManager cubeManager;
	//private StoryMgr storMgr;
	//	private Options optMgr;
	//	private String msrname;
	
	private CubeQuery currentCubeQuery;
	private Result currentResult;
	private String currentQueryName;
	
	private HashMap<String, String> userInputList = new HashMap<>();


/**
 * Simple constructor for the class
 * @throws RemoteException
 */
	public SimpleQueryProcessorEngine() throws RemoteException {
		super();
		currentResult = null;
		currentCubeQuery = null;
		currentQueryName = null;
		
		//storMgr = new StoryMgr();
		//optMgr = new Options();
	}
	
	
	@Override
	public OutputStream getOutputStream(File f) throws IOException {
	    return new RMIOutputStream(new RMIOutputStreamImpl(new 
	    FileOutputStream(f)));
	}
	@Override
	public InputStream getInputStream(File f) throws IOException {
	    return new RMIInputStream(new RMIInputStreamImpl(new FileInputStream(f)));
	}//end method
	
	
	/* 
	 * TODO: at some point, connections must be initialized at the beginning and NOT on a per query basis!!
	 * 
	 * (non-Javadoc)
	 * @see mainengine.IMainEngine#initializeConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void initializeConnection(String typeOfConnection, HashMap<String, String> userInputList) throws RemoteException {
		this.userInputList = userInputList;
		cubeManager = new CubeManager(typeOfConnection, userInputList);
		cubeManager.CreateCubeBase(userInputList);
		cubeManager.constructCube(userInputList.get("inputFolder"), userInputList.get("cubeName"));
		cubeManager.setCubeQueryTranslator();
		System.out.println("\nok - Done with initialization! \n");
	}

	/* (non-Javadoc)
	 * @see mainengine.IMainEngine#answerCubeQueriesFromFile(java.io.File)
	 */
	@Override
	public ArrayList<String> answerCubeQueriesFromFile(File file) throws RemoteException {
		ArrayList<String> fileLocations = new ArrayList<String>();
		Scanner sc;
		try {
			Scanner scanner = new Scanner(file);
			sc = scanner.useDelimiter("@");
			while (sc.hasNext()) {
				String filename;
				//long startTime = System.nanoTime();
				String queryString = sc.next();
				filename = answerCubeQueryFromString(queryString);
				fileLocations.add(filename);
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileLocations;
	}	
	

	/** 
	 * Gets the query from a string, executes it and produces the output of a query
	 * <p>
	 * The main idea is:
	 * (1) construct the query via <code>createCubeQueryFromString</code> of <code>CubeManager</code>, see {@link CubeManager}
	 * (2) execute the query again via {@link CubeManager} and obtain a {@link Result}
	 * (3a) produce a file in the directory <code>OutputFiles</code> with the name of the query
	 * (3x) btw., the results are also output to the console
	 * 
	 * @param queryRawString a String with the query
	 * @return a String containing the location of output file at the server
	 *  
	 * @author pvassil
	 * @since v.0.1
	 * @see mainengine.IMainEngine#answerCubeQueryFromString(java.lang.String)
	 * @see cubemanager.CubeManager#executeQuery(CubeQuery)
	 */
	@Override
	public String answerCubeQueryFromString(String queryRawString) throws RemoteException{

		//Use a hashmap to get any useful data (like queryname) from the raw query string
		HashMap<String, String> queryParams = new HashMap<String, String>();
		
		//1. parse query and produce a CubeQuery
		CubeQuery currentCubQuery = cubeManager.createCubeQueryFromString(queryRawString, queryParams);
		this.currentCubeQuery = currentCubQuery;
		
		Instant t0 = Instant.now();
		//2. execute the query AND populate Result with a 2D string
		//Result res = cubeManager.getCubeBase().executeQuery(currentCubQuery);
		Result res = cubeManager.executeQuery(currentCubQuery);
		this.currentResult = res;
		
		Instant tExecuted = Instant.now();
		
		long queryTimeString = cubeManager.getQueryTime();
		long resultTime = Duration.between(t0, tExecuted).toMillis();
		resultTime = resultTime - queryTimeString;

		long durationExecution = Duration.between(t0, tExecuted).toMillis();

		//3a. print result to file and screen
		String queryName = queryParams.get("QueryName");
		this.currentQueryName = queryName;
		
				
		//3b. print result to file
		String outputLocation = this.printToTabTextFile(currentCubQuery, PathFolder.getPathOfProject() + File.separator + "OutputFiles" + File.separator);
		//TODO SUPER MUST: devise a nice way to handle the output to console when in development mode
		if ((ModeOfWork.mode == WorkMode.DEBUG_GLOBAL)||(ModeOfWork.mode == WorkMode.DEBUG_QUERY)) {
			res.printCellsToStream(System.out);
		}
		Instant tOutputed = Instant.now();
		
		
		long durationExecToOutput = Duration.between(tExecuted, tOutputed).toMillis();
		long durationExecTotal = Duration.between(t0, tOutputed).toMillis();
		
		// Code to beautify printing & output the data in the script_time folder.
		System.out.println("(Print from SQPE.answerCubeQueryFromString)");
		String[] allStrings = {queryName, Long.toString(resultTime) + " ms = " + resultTime / 1000F + " secs",  Long.toString(queryTimeString) + " ms = " + queryTimeString / 1000F + " secs", Long.toString(durationExecution) + " ms = " + durationExecution / 1000F + " secs" , Long.toString(durationExecToOutput) + " ms = " + durationExecToOutput / 1000F + " secs", Long.toString(durationExecTotal) + " ms = " + durationExecTotal / 1000F + " secs" };
	    int maxString = getLongestString(allStrings);
	    
	    System.out.print("+");
	    for (int i = 0; i < 19 + maxString; i++) {
	    	System.out.print("-");
	    }
	    System.out.println("+");
	    int centerAt = 17 + maxString;
	    String format = "| %"+ centerAt + "s |\n";
	    System.out.format(format, StringUtils.center("Done with " + queryName, centerAt));

	    System.out.print("+");
	    for (int i = 0; i < 19 + maxString; i++) {
	    	System.out.print("-");
	    }
	    System.out.println("+");
		System.out.format("|%-15s : %-"+maxString+"s |\t\n", "Model", queryName);
		System.out.format("|%-15s : %-"+maxString+"s |\t\n", "Total Result", res.getCells().size());
		System.out.format("|%-15s : %-"+maxString+"s |\t\n", "Query Time", queryTimeString + " ms = " + queryTimeString / 1000F + " secs");
		System.out.format("|%-15s : %-"+maxString+"s |\t\n", "Result Time", resultTime + " ms = " + resultTime / 1000F + " secs");
		System.out.format("|%-15s : %-"+maxString+"s |\t\n", "Execution Time", durationExecution + " ms = " + durationExecution / 1000F + " secs");
		System.out.format("|%-15s : %-"+maxString+"s |\t\n", "Output Time", durationExecToOutput + " ms = " + durationExecToOutput / 1000F + " secs");
		System.out.format("|%-15s : %-"+maxString+"s |\t\n", "Total Time", durationExecTotal + " ms = " + durationExecTotal / 1000F + " secs");
	    System.out.print("+");
	    for (int i = 0; i < 19 + maxString; i++) {
	    	System.out.print("-");
	    }
	    System.out.println("+");
	    
	    // Export Data To File. Used to check MySQL and Spark result times
//	    try {
//	        File myObj = new File(PathFolder.getPathOfProject() + File.separator + "OutputFiles" + File.separator + "script_times" + File.separator
//	        		+ userInputList.get("round") + "_" + userInputList.get("connectionType") + "_" + userInputList.get("schemaName") + ".csv");
//	        if (myObj.createNewFile()) {
//	          System.out.println("File created: " + myObj.getName());
//	          try {
//	              FileWriter myWriter = new FileWriter(myObj);
//	              myWriter.write("model,total_result,query_time,result_time,execution_time,output_time,total_time\n");
//	              myWriter.close();
//	              System.out.println("Successfully wrote to the file.");
//	            } catch (IOException e) {
//	              System.out.println("An error occurred.");
//	              e.printStackTrace();
//	            }
//	        }
//	        try {
//	            Files.write(Paths.get(myObj.toString()), (queryName + "," + res.getCells().size() + "," + queryTimeString + "," + resultTime + "," + durationExecution + "," + durationExecToOutput + "," + durationExecTotal + "\n").getBytes(), StandardOpenOption.APPEND);
//	        }catch (IOException e) {
//	            //exception handling left as an exercise for the reader
//	        }
//
//	    } catch (IOException e) {
//	    	System.out.println("An error occurred.");
//	    	e.printStackTrace();
//	    }
	    // End Data Export Here
	    
	    // Print Ends here
		return outputLocation;
	}//answerCubeQueryFromString

	  public static int getLongestString(String[] array) {
	      int maxLength = 0;
	      String longestString = null;
	      for (String s : array) {
	          if (s.length() > maxLength) {
	              maxLength = s.length();
	              longestString = s;
	          }
	      }
	      return longestString.length();
	  }
	/** 
	 * Gets the query from a string, executes it and produces the output of a query as a ResultFileMetadata object
	 * <p>
	 * The main idea is:
	 * (1) construct the query via <code>createCubeQueryFromString</code> of <code>CubeManager</code>, see {@link CubeManager}
	 * (2) execute the query again via {@link CubeManager} and obtain a {@link Result}
	 * (3a) produce a queryName.tab file in the directory <code>OutputFiles</code> with the queryName being name of the query
	 * (3b) produce an queryName_info.txt file that contains the query definition in the same folder
	 * (3x) btw., the results are also output to the console
	 * 
	 * @param queryRawString a String with the query
	 * @return a ResultFileMetadata containing info on the location of output files at the server
	 * 
	 * @author pvassil
	 * @since v.0.2
	 * @see mainengine.IMainEngine#answerCubeQueryFromString(java.lang.String)
	 * @see cubemanager.CubeManager#executeQuery(CubeQuery)
	 */
	@Override
	public ResultFileMetadata answerCubeQueryFromStringWithMetadata(String queryRawString) throws RemoteException {
//		//Use a hashmap to get any useful data (like queryname) from the raw query string
//		HashMap<String, String> queryParams = new HashMap<String, String>();
//		
//		//1. parse query and produce a CubeQuery
//		CubeQuery currentCubQuery = cubeManager.createCubeQueryFromString(queryRawString, queryParams);
//
//		//2. execute the query AND populate Result with a 2D string
//		//Result res = cubeManager.getCubeBase().executeQuery(currentCubQuery);
//		Result res = cubeManager.executeQuery(currentCubQuery);
//				
//		//3a. print result to screen
//		String queryName = queryParams.get("QueryName");
//		
//		//Replaced all printing of String[][] with printing of Cells which seems to be identical 
//		//res.printStringArrayTostream(System.out, res.getResultArray());
//		//System.out.println("------- Done with printString, go for printCells  --------------------------"+"\n");
//		res.printCellsToStream(System.out);
//		System.out.println("------- Done with " + queryName + " --------------------------"+"\n");
//				
//		//3b. print result to file
//		String outputLocation = this.printToTabTextFile(currentCubQuery,  "OutputFiles/");


		String outputLocation = answerCubeQueryFromString(queryRawString);
		String outputInfoLocation = this.printQueryInfo(this.currentCubeQuery,  "OutputFiles" + File.separator);
		
		ResultFileMetadata resMetadata = new ResultFileMetadata();
		
		resMetadata.setComponentResultFiles(null);
		resMetadata.setComponentResultInfoFiles(null);
		resMetadata.setLocalFolder("OutputFiles" + File.separator);
		resMetadata.setResultFile(outputLocation);
		resMetadata.setResultInfoFile(outputInfoLocation);
		System.out.println("@SRV: FOLDER\t" + resMetadata.getLocalFolder());
		System.out.println("@SRV: DATA FILE\t" + resMetadata.getResultFile());
		System.out.println("@SRV: INFO FILE\t" + resMetadata.getResultInfoFile());		


		
		return resMetadata;
	}//answerCubeQueryFromStringWithMetada
	
	@Override
	public ResultFileMetadata answerCubeQueryFromStringWithModels(String queryRawString, String[] modelsToGenerate)
			throws RemoteException {
		
		int numOfModelsGenerated = 0;
		int numOfModelsRequested = 0;
		
		//0. answer the query and get its result and info files
		ResultFileMetadata resMetadata = answerCubeQueryFromStringWithMetadata(queryRawString);
		/* 
		 * postConditions: Result, cubeQuery and cubeQueryName are populated; resMetadata has info on folder, query results and query info
		*/

// Used to work fine. Replace so that we can introduce model selection explicitly via a dedicated class
//		/* 
//		 * 1. Choosing which models to fire. 
//		 *    We will work with the modelNames variable; 
//		 *    if you pass an non-empty parameter it works with your parameter, else it works with the defaults.
//		*/
//		String [] modelNames = {"Rank","Outlier"};
//		if(modelsToGenerate.length > 0) {
//			modelNames = modelsToGenerate.clone(); 
//		}
//		numOfModelsRequested = modelNames.length;
//		System.out.println("\nModel selection of " + numOfModelsRequested + " models");		

		if((modelsToGenerate == null) ||(modelsToGenerate.length == 0)) {
			numOfModelsRequested = 0;
		}
		else 
			numOfModelsRequested = modelsToGenerate.length;
		
		String [] modelNames;
		ModelSelector modelSelector = new ModelSelector(currentQueryName);
		modelNames = modelSelector.decideModelsToExecute(currentQueryName, modelsToGenerate);

		
		//2. select the models to fire
		ModelManager modelManager = new ModelManager(this.currentResult);
		modelManager.selectModelsToLaunch(modelNames);
		//3. execute the selected models
		int modelGenFlag = modelManager.executeModelConstruction(this.currentQueryName);
		//4.Populate resMetadata with the outcome of model generation
		if (modelGenFlag == 0) { 			//all went OK
			numOfModelsGenerated = modelManager.addComponentsToResultMetadata(resMetadata);
						

			if( (numOfModelsRequested > 0) &&(numOfModelsGenerated < numOfModelsRequested)) {
				System.err.println("\nSIMPLEQUERYPROCESSORENGINE # answerCubeQueryFromStringWithModels\nModel generation of " + numOfModelsGenerated + " models, for " + numOfModelsRequested + " requested models");
				System.err.println("Shutting down server\n");
				System.exit(-1);
			}
			
		}//end if modelGenFlag

		
		return resMetadata;
	}//end method answerCubeQueryFromStringWithModels


	
	/**
	 * Populates a tab-separated file where the result of a query is stored and Ï�eturns its location.
	 * <p>
	 * The goal of this method is to output a file containing the result of a query
	 * The name of the file is the name of the query + extension tab 
	 * 
	 * @param cubequery The query whose result is being outputed
	 * @return  A String with the location of the file holding the results
	 * @author pvassil
	 * @author dgkesouli
	 * @version v.0.1
	 * @since v.0.0 from Cincecubes
	 */
	public String printToTabTextFile(CubeQuery cubequery, String outputFolder) {
		Result res = cubequery.getResult();
		String fileName = outputFolder + cubequery.getName() + ".tab";
		File file=new File(fileName);
		FileOutputStream fileOutputStream=null;
		PrintStream printStream=null;
		try {
			fileOutputStream=new FileOutputStream(file);
			printStream=new PrintStream(fileOutputStream);
			
			//res.printStringArrayTostream(printStream, res.getResultArray());
			res.printCellsToStream(printStream);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fileOutputStream!=null){
					fileOutputStream.close();
				}
				if(printStream!=null){
					printStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}//end finally try
		}//end finally
		
		return fileName;
	}//end method
	

	/**
	 * Populates a file, XXX_info.txt, XXX being the query name, containing information for the query launched and returns its location.
	 * 
	 * @param cubequery   the CubeQuery whose info is recorded 
	 * @param outputFolder the folder to which the file is going to be stored
	 * @return  the String with the location of the produced file
	 */
	public String printQueryInfo(CubeQuery cubequery, String outputFolder) {
		//Result res = cubequery.getResult();
		String fileName = computeQueryInfoName(cubequery, outputFolder);
		File file=new File(fileName);
		FileOutputStream fileOutputStream=null;
		PrintStream printStream=null;
		try {
			fileOutputStream=new FileOutputStream(file);
			printStream=new PrintStream(fileOutputStream);
			
			//printStream.print(cubequery.getName()+"\n\n");
			printStream.print(cubequery.toString()+"\n\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fileOutputStream!=null){
					fileOutputStream.close();
				}
				if(printStream!=null){
					printStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}//end finally try
		}//end finally
		
		return fileName;
	}//end method

	/**
	 * Given an query (and thus its name), and an output folder, computes the path of the _info.txt file of the query
	 * 
	 * @param cubequery a CubeQuery whose info file name is requested
	 * @param outputFolder  a String representing a target folder, if the form of "MyFolder/" where the output will be placed. Don't forget the "/"
	 * @return the path of the _info.txt file of the query
	 */
	private String computeQueryInfoName(CubeQuery cubequery, String outputFolder) {
		String fileName = outputFolder + cubequery.getName() + "_info.txt";
		return fileName;
	}
	
}
