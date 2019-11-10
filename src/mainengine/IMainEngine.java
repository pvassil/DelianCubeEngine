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
//import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author pvassil
 *
 */
public interface IMainEngine extends IServer {

	/**
	 * Use initializeConnection as the first step between a CLIENT and any implementation of the IMainEngine.
	 * 
	 * @param schemaName  	The name of the database 
	 * @param login  		The user login
	 * @param passwd  		The user passwd
	 * @param inputFolder	The folder where the cube description lies (includes a dbc.ini for the connection and cubde descriptions too)
	 * @param cubeName		The name of the cube + the name of the file where the cube is described = <cubename>.ini
	 * @throws RemoteException
	 */
	void initializeConnection(String schemaName, String login, String passwd,
			 String inputFolder, String cubeName) throws RemoteException;
	
	// Added by Konstantinos Kadoglou
	void initializeSpark(String schemaName, String inputFolder,
			String cubeName) throws RemoteException;

	/**
	 * Answers a query as prescribed in a String
	 * 
	 * An exemplary query definition is as follows:
	 * <p>
	 * <code>CubeName:loan</code>
	 * <code>Name:CubeQueryLoan2</code>
	 * <code>AggrFunc:Avg</code>
	 * <code>Measure:amount</code>
	 * <code>Gamma:account_dim.lvl2,date_dim.lvl2</code>
	 * <code>Sigma:account_dim.lvl1='Liberec',status_dim.lvl0='Running Contract/OK'</code>
	 * <p>
	 * Observe:
	 * The query has a name, in the example,CubeQueryLoan2
	 * The query comes with two groupers
	 * The selection condition Sigma, comes as a COMMA-SEPARATED list of atomic expressions of the form Level=value
	 * 
	 * TODO: test with less/more groupers + with alternative selection atoms
	 * 
	 * @param queryRawString	A String with the query definition
	 * @return the path of the result file
	 * @throws RemoteException
	 * 
	 */
	String answerCubeQueryFromString(String queryRawString) throws RemoteException;

	
	/**
	 * This is the method to call for answering several queries from a single file.
	 *  
	 * Inside the file, queries are separated by a '@'
	 * 
	 * @param file	The PATH of the filename containing the queries
	 * @return	An ArrayList of Strings with the names of the files containing the results of the queries.
	 * @throws RemoteException
	 * @see answerCubeQueryFromString
	 */
	ArrayList<String> answerCubeQueriesFromFile(File file) throws RemoteException;
	
	
	/**
	 * Answers a query as prescribed in a String and returns (a) a file with the results of the query and (b) an info file with metadata
	 * 
	 * See the answerCubeQueryFromString
	 * 
	 * @param queryRawString	A String with the query definition
	 * @return	A ResultFileMetadata object containing the respective String values on the location of the two produced files and their parent folder 
	 * @throws RemoteException
	 * @see answerCubeQueryFromString
	 */
	ResultFileMetadata answerCubeQueryFromStringWithMetadata(String queryRawString) throws RemoteException;

	
	
	/**
	 * Answers a query as prescribed in a String and returns a file with the results of the query and a set of models produced over the result of the query 
	 * 
	 * See the answerCubeQueryFromString
	 * 
	 * @param queryRawString	A String with the query definition
	 * @param modelsToGenerate	An array of Strings with the model names to generate; if empty, a list of default models are applied
	 * @return	A ResultFileMetadata object containing the respective String values on the location of the produced files and their parent folder 
	 * @throws RemoteException
	 * @see answerCubeQueryFromString
	 */
	ResultFileMetadata answerCubeQueryFromStringWithModels(String queryRawString, String [] modelsToGenerate) throws RemoteException;
	
	
	
	/**
	 * @deprecated	Not to be used in the context of the DelianCubeEngine project.
	 * This is an old-to-be-removed method, from Cinecubes, that turns on/off audio and MS word generation
	 * 
	 * @param audio	To turn on/off audio generation
	 * @param word	To turn on/off MS Word generation
	 * @throws RemoteException
	 */
//	void optionsChoice(boolean audio, boolean word)throws RemoteException;

}