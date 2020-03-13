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


package client.naiveJavaClient;

import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

import client.ClientRMITransferer;
import mainengine.IMainEngine;
import mainengine.ResultFileMetadata;
/**
 * A simple client that 
 * (a) locates an RMI server in the HOST at PORT
 * (b) issues queries against a specific cubebase and a specific cube
 * 
 * @author pvassil
 *
 */
public class NaiveJavaClientExperiments {

	// Host or IP of Server
	private static final String HOST = "localhost";
	private static final int PORT = 2020;
	private static Registry registry;

	public static void main(String[] args) throws Exception {
		// Search the registry in the specific Host, Port.
		registry = LocateRegistry.getRegistry(HOST, PORT);
		// LookUp for MainEngine on the registry
		IMainEngine service = (IMainEngine) registry.lookup(IMainEngine.class
				.getSimpleName());
		if(service == null) {
			System.out.println("Unable to commence server, exiting");
			System.exit(-100);
		}
		// The first arg is audio; the second is for Word
		//service.optionsChoice(false, false);
		
		
		
		// Cube ADULT and queries
		/*service.initializeConnection("adult_no_dublic", "CinecubesUser",
				"Cinecubes", "adult", "adult");
		File f = new File("InputFiles/cubeQueries.ini");
		service.answerCubeQueriesFromFile(f);/**/
				
		// Cube LOAN and queries
		HashMap<String, String> userInputList = new HashMap<>();
		userInputList.put("username", "CinecubesUser");
		userInputList.put("password", "Cinecubes");
		userInputList.put("schemaName", "pkdd99");
		userInputList.put("cubeName", "loan");
		userInputList.put("inputFolder", "pkdd99");
		service.initializeConnection("RDBMS", userInputList);
		System.out.println("Completed connection initialization");

		//CleanUp client Cache
		File resultFolder = new File("ClientCache");
		deleteAllFilesOfFolder(resultFolder);


		
		/* ******************* now for the models **************/
		
		String queries[] = {"CubeName:loan " + " \n" +
				"Name: LoanQuery101_S1_CG-Disjoint " + " \n" +
				"AggrFunc:Sum " + " \n" +
				"Measure:amount " + " \n" +
				"Gamma:account_dim.lvl2,date_dim.lvl3 " + " \n" +
				"Sigma: account_dim.lvl3 = 'ALL'",

				"CubeName:loan " + " \n" +
				"Name: LoanQuery102_S1_CG-Disjoint " + " \n" +
				"AggrFunc:Sum " + " \n" +
				"Measure:amount " + " \n" +
				"Gamma:account_dim.lvl2,date_dim.lvl3 " + " \n" +
				"Sigma:status_dim.lvl1='Running Contract/OK' ",

				"CubeName:loan " + " \n" +
				"Name: LoanQuery103_S1_CG-Disjoint " + " \n" +
				"AggrFunc:Sum " + " \n" +
				"Measure:amount " + " \n" +
				"Gamma:account_dim.lvl1,date_dim.lvl3 " + " \n" +
				"Sigma:status_dim.lvl1='Running Contract/OK'",

				"CubeName:loan " + " \n" +
				"Name: LoanQuery104_S1_CG-Disjoint " + " \n" +
				"AggrFunc:Sum " + " \n" +
				"Measure:amount " + " \n" +
				"Gamma:account_dim.lvl2,date_dim.lvl2 " + " \n" +
				"Sigma:status_dim.lvl1='Running Contract/OK'" ,

				"CubeName:loan " + " \n" +
				"Name: LoanQuery105_S1_CG-Disjoint " + " \n" +
				"AggrFunc:Sum " + " \n" +
				"Measure:amount " + " \n" +
				"Gamma:account_dim.lvl2,date_dim.lvl2 " + " \n" +
				"Sigma:account_dim.lvl2 = 'south Moravia'"
			};
	
		String queryNames[] = {"LoanQuery101_S1_CG-Disjoint", "LoanQuery102_S1_CG-Disjoint", 
				"LoanQuery103_S1_CG-Disjoint", "LoanQuery104_S1_CG-Disjoint", "LoanQuery105_S1_CG-Disjoint"};
		
		/*String queryForModels12 =		"CubeName:loan" + " \n" +
				"Name: CubeQueryLoan12_Sum1998" + " \n" +
				"AggrFunc:Sum" + " \n" +
				"Measure:amount" + " \n" +
				"Gamma:account_dim.lvl1,status_dim.lvl1" + " \n" +
				"Sigma:date_dim.lvl2 = '1998-01'";
		String queryName12 = "CubeQueryLoan12_Sum1998";
		
		
		String queryFired = queryForModels12;
		String queryName = queryName12;
		*/
		
		String [] modelsToGenerate = {"Rank","Outlier", "KMeansApache", "KPIMedianBased"};
		for (int i=0; i<queryNames.length;i++) {
			String queryFired = queries[i];
			String queryName = queryNames[i];
			
			ResultFileMetadata resMetadata = service.answerCubeQueryFromStringWithModels(queryFired, modelsToGenerate);
			brindDataToTheClient(service, queryName, resMetadata);
		}
		
		System.out.println("Execution of client is complete");
	}//end main

	/**
	 * Brings the data from the server to the client at the folder ClientCache
	 * 
	 * @param service   The IMainEngine where the server is
	 * @param queryName  The String with the query name that was executed
	 * @param resMetadata  The metadata of the executed query, containing the file names of the queries
	 * 
	 * @throws IOException
	 */
	private static void brindDataToTheClient(IMainEngine service, String queryName, ResultFileMetadata resMetadata)
			throws IOException {
		String remoteFolder = resMetadata.getLocalFolder();
		String remoteResultsFile = resMetadata.getResultFile();
		String remoteInfoFile = resMetadata.getResultInfoFile();
		ArrayList<String> models = resMetadata.getComponentResultFiles();
		ArrayList<String> modelInfos = resMetadata.getComponentResultInfoFiles();
		
		System.out.println("\nRES\t" + remoteResultsFile + "\nINFO\t" + remoteInfoFile + "\nCOMP\t" + models.get(0));
		
		String localFolder = "ClientCache" + File.separator;
		File remoteRes = new File(remoteResultsFile);
		ClientRMITransferer.download(service, remoteRes, new File( localFolder + queryName + ".tab"));
		File remoteIRes = new File(remoteInfoFile);
		ClientRMITransferer.download(service, remoteIRes, new File(localFolder + queryName + "_Info.txt"));
		
		if(models.size() > 0) {	
			for(String model: models){
				String sep = "\\" + File.separator;	//Java idioms. You need to add the "\\" before!
				String [] array = model.split(sep);
				String localModelName = "NoName";
				if (array.length > 0)
					localModelName = array[array.length-1].trim();
				File remote = new File(model);
				ClientRMITransferer.download(service, remote, new File("ClientCache"+ File.separator  + localModelName));
			}//end for
			for(String modelInfo: modelInfos){
				String sep = "\\" + File.separator;	//Java idioms. You need to add the "\\" before!
				String [] array = modelInfo.split(sep);
				String localModelName = "NoName";
				if (array.length > 0)
					localModelName = array[array.length-1].trim();
				File remote = new File(modelInfo);
				ClientRMITransferer.download(service, remote, new File("ClientCache"+ File.separator  + localModelName));
			}//end for

		
		}//end if
	}

	/**
	 * Deletes all files from a folder
	 * @param dir the folder to be cleaned up
	 * @return the number of files removed
	 */
	public static int deleteAllFilesOfFolder(File dir) {
		if(!dir.isDirectory())
			return -1;
		int i = 0;
		for(File file: dir.listFiles()) { 
		    if (!file.isDirectory() && !file.getName().equals("README.txt")) {
		        file.delete();
		        i++;
		    }
		}
		return i;
	}//end method
	
}//end class


