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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet.P;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec._;

import client.ClientRMITransferer;
import mainengine.IMainEngine;
import mainengine.ResultFileMetadata;
import parsermgr.PathFolder;
/**
 * A simple client that 
 * (a) locates an RMI server in the HOST at PORT
 * (b) issues queries against a specific cubebase and a specific cube
 * 
 * @author pvassil
 *
 */
public class NaiveJavaClientRunAllTimes {

	// Host or IP of Server
	private static final String HOST = "localhost";
	private static final int PORT = 2020;
	private static Registry registry;
	
	private static HashMap<String, String> userInputList = new HashMap<>();
	private static int counter = 0;
	private static int times = 0;

	public static void main(String[] args) throws Exception {

		while ((counter < 10) && (times < 10)) {
		// Search the registry in the specific Host, Port.
		System.out.println("Working Directory : " + System.getProperty("user.dir"));
		System.out.println("Path to files : " + PathFolder.getPathOfProject() + File.separator);       
		registry = LocateRegistry.getRegistry(HOST, PORT);
		// LookUp for MainEngine on the registry
		IMainEngine service = (IMainEngine) registry.lookup(IMainEngine.class.getSimpleName());
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
		readNaiveIni();
		service.initializeConnection(userInputList.get("connectionType"), userInputList);
		System.out.println("Completed connection initialization");

		//CleanUp client Cache
		File resultFolder = new File(PathFolder.getPathOfProject() + File.separator + "ClientCache");
		deleteAllFilesOfFolder(resultFolder);
		
		//Run queries
		//File f2 = new File("InputFiles/cubeQueriesloan.ini");
		File f2 = new File(PathFolder.getPathOfProject() + File.separator + "InputFiles" + File.separator + userInputList.get("inputFolder") + File.separator + userInputList.get("queryFile"));
		//File f2 = new File("InputFiles/try.txt");
		ArrayList<String> fileLocations = service.answerCubeQueriesFromFile(f2);
		
		// Cube ORDERS and queries
		/*service.initializeConnection("pkdd99", "CinecubesUser",
				 "Cinecubes", "pkdd99", "orders");
		File f4 = new File("InputFiles/cubeQueriesorder.ini");
		service.answerCubeQueriesFromFile(f4);/**/
		
		
		for(String s: fileLocations) {
			System.out.println("Find the next result at " + s);
			File remote = new File(s);
			String sep = "\\" + File.separator;	//Java idioms. You need to add the "\\" before!
			String[] array = s.split(sep);
			String localName = "NoName";
			if (array.length > 0)
				localName = array[array.length-1].trim();
			
			ClientRMITransferer.download(service, remote, new File(PathFolder.getPathOfProject() + File.separator + "ClientCache" + File.separator + localName));
			
		}
		
		/* ******************* now for the models **************/
		
		if (userInputList.get("runModels").equals("YES") || userInputList.get("runModels").equals("Yes") || userInputList.get("runModels").equals("yes")) {
			String queryForModels11 = "CubeName:sales\n" + 
					"Name:CubeQuerySales\n" + 
					"AggrFunc:Avg\n" + 
					"Measure:sales\n" + 
					"Gamma:products_dim.lvl1,locations_dim.lvl1\n" + 
					"Sigma:dates_dim.lvl3='2'";
			String queryName11 = "CubeQuerySales";
			
			
			String queryFired = queryForModels11;
			String queryName = queryName11;
			String [] modelsToGenerate = {"Rank","Outlier", "KMeansApache", "KPIMedianBased"};
			//String [] modelsToGenerate = {"Outlier"};
			//String [] modelsToGenerate = {};
			ResultFileMetadata resMetadata = service.answerCubeQueryFromStringWithModels(queryFired, modelsToGenerate);
			
	
			
			String remoteFolder = resMetadata.getLocalFolder();
			String remoteResultsFile = resMetadata.getResultFile();
			String remoteInfoFile = resMetadata.getResultInfoFile();
			ArrayList<String> models = resMetadata.getComponentResultFiles();
			ArrayList<String> modelInfos = resMetadata.getComponentResultInfoFiles();
			
			System.out.println("\nRES\t" + remoteResultsFile + "\nINFO\t" + remoteInfoFile + "\nCOMP\t" + models.get(0));
			
			String localFolder = PathFolder.getPathOfProject() + File.separator + "ClientCache" + File.separator;
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
					ClientRMITransferer.download(service, remote, new File(PathFolder.getPathOfProject() + File.separator + "ClientCache"+ File.separator  + localModelName));
				}//end for
				for(String modelInfo: modelInfos){
					String sep = "\\" + File.separator;	//Java idioms. You need to add the "\\" before!
					String [] array = modelInfo.split(sep);
					String localModelName = "NoName";
					if (array.length > 0)
						localModelName = array[array.length-1].trim();
					File remote = new File(modelInfo);
					ClientRMITransferer.download(service, remote, new File(PathFolder.getPathOfProject() + File.separator + "ClientCache"+ File.separator  + localModelName));
				}//end for
	
			
			}//end if
		}// End if for runModels
		System.out.println("Execution of client is complete");
		counter++;
		if (counter == 10) {
			counter = 0;
			times++;
		}
		} // END WHILE
	}//end main

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
	
	public static void readNaiveIni() {
		File file = new File(PathFolder.getPathOfProject() + File.separator + "naiveTestFiles" + File.separator + "naive" + times + ".ini"); 
		System.out.println(file.getAbsolutePath());
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			try {
				int counter = 0;
				while ((st = br.readLine()) != null) {
					String[] words = st.split(" ");
					if (!words[words.length-1].isEmpty()) {
						userInputList.put(words[0], words[words.length-1]);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Something went wrong while reading the naive.ini file.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can't find the naive.ini file. The file should be for example in 'ProjectFolder/naive.ini'.");
		}
		userInputList.put("round", "1");
	}
	
}//end class

