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


package cubemanager;

import java.rmi.RemoteException;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import cubemanager.cubebase.BasicStoredCube;
import cubemanager.cubebase.CubeBase;
import cubemanager.cubebase.CubeQuery;
import cubemanager.cubebase.Measure;
import exctractionmethod.ExtractionMethod;
import exctractionmethod.ExtractionMethodFactory;
import result.Result;

public class CubeManager {

	private CubeBase CBase;
	private ICubeQueryTranslator cubeQueryTranslator;
	private CubeQueryTranslatorFactory cubeQueryTranslatorFactory;

	public CubeManager(String lookupFolder, Boolean isRunningSpark) {
		CBase = new CubeBase(lookupFolder, isRunningSpark);
		cubeQueryTranslatorFactory = new CubeQueryTranslatorFactory();
	}

	public ICubeQueryTranslator setCubeQueryTranslator() {
		ICubeQueryTranslator result = cubeQueryTranslatorFactory.createCubeQueryTranslator("SQL"); 
		if (result == null) {
			System.err.println("CubeQueryTranslator generation failed. Exiting.");
			System.exit(-1);
		}
		cubeQueryTranslator = result;
		return result;
	}
	
	public void CreateCubeBase(String filename, String username,
			String password) {
		CBase.registerCubeBase(filename, username, password);
	}
	
	// Added by Konstantinos Kadoglou
	public void CreateCubeBase(String filename, String cubeName){
		System.out.println("Register cube : " + cubeName);
		CBase.registerCubeBase(filename, cubeName);
	}
	
	public CubeBase getCubeBase(){
		return CBase;
	}
	public void InsertionDimensionLvl(String dimensionName,
			String dimensionTbl, ArrayList<String> fld_Name,
			ArrayList<String> customFld_Name, ArrayList<String> hierachylst) {
		CBase.addDimension(dimensionName);
		CBase.addDimensionTbl(dimensionTbl);
		CBase.setDimensionLinearHierachy(hierachylst, fld_Name, customFld_Name);
	}

	public void InsertionCube(String name_creation, String sqltable,
			ArrayList<String> dimensionlst,
			ArrayList<String> DimemsionRefField, ArrayList<String> measurelst,
			ArrayList<String> measureRefField) {

		CBase.addCube(name_creation);
		CBase.addSqlRelatedTbl(sqltable);
		CBase.setCubeDimension(dimensionlst, DimemsionRefField);
	}


	//REMOVED: a parameter   String msrname   from the method's signature 
	//why was the measureName in the signature of this method at the Cinecubes' code?
	//Probably Dimitris was struggling with sth at the parser...
	//
	public CubeQuery createCubeQueryFromString(String cubeQstring, HashMap<String, String> queryParams)
			throws RemoteException {
		String[] rows = cubeQstring.trim().split("\n");
		String Cbname = null, nameCQ = null, aggregateFunction = null, measureName = null;
		String[][] sigma = null;
		String[][] gamma = null;
		for (int i = 0; i < rows.length; i++) {
			String[] temp = rows[i].split(":");
			if (temp[0].equals("CubeName")) {
				Cbname = temp[1].trim();
			}
			if (temp[0].equals("Name")) {
				nameCQ = temp[1].trim();
			} else if (temp[0].equals("AggrFunc")) {
				aggregateFunction = temp[1].trim();
			} else if (temp[0].equals("Measure")) {
				measureName = temp[1].trim();
			} else if (temp[0].equals("Gamma")) {
				String[] tmp_gamma = temp[1].split(",");
				gamma = new String[tmp_gamma.length][2];
				for (int j = 0; j < tmp_gamma.length; j++) {
					String[] split_gamma = tmp_gamma[j].trim().split("\\.");
					gamma[j][0] = split_gamma[0];
					gamma[j][1] = split_gamma[1];
				}
			} else if (temp[0].equals("Sigma")) {
				String[] tmp_sigma = temp[1].split(",");
				sigma = new String[tmp_sigma.length][3];
				for (int j = 0; j < tmp_sigma.length; j++) {
					String[] split_sigma = tmp_sigma[j].trim().split("=");
					sigma[j][0] = split_sigma[0];
					sigma[j][1] = "=";
					sigma[j][2] = split_sigma[1];
				}
			}
		}
		queryParams.put("QueryName", nameCQ);


		//TODO: opportunity to wrap all this ping-pong with CubeQuery into a method/constructor
		CubeQuery cubequery = new CubeQuery(nameCQ);
		cubequery.setAggregateFunction(aggregateFunction);
		/* Must Create Measure In Cube Parser->> I Have Done this */
		/* Search for Measure */
		Measure msrToAdd = new Measure(1,measureName,CBase.getDatabase().getFieldOfSqlTable(Cbname,
				measureName));
		cubequery.getListMeasure().add(msrToAdd);
		//msrname = measureName;
		/* Need work to done up here */

		for (int i = 0; i < gamma.length; i++) {
			cubequery.addGammaExpression(gamma[i][0], gamma[i][1]);
		}

		for (int i = 0; i < sigma.length; i++) {
			cubequery.addSigmaExpression(sigma[i][0], sigma[i][1], sigma[i][2]);
		}
		for (BasicStoredCube bsc : CBase.BasicCubes) {
			if (bsc.getName().equals(Cbname + "_cube")) {
				cubequery.setBasicStoredCube(bsc);
			}
		}
		return cubequery;
	}

	/**
	 * Returns the {@link Result} of a {@link CubeQuery}.
	 * <p>
	 * The <code>CubeQuery</code> object must have been prepared before via invoking <code>createQueryFromString</code> of this object. 
	 * Then, the execution is passed to <code>CubeBase</code> that actually orchestrates the execution of this query 
	 * 
	 * The method takes a CubeQuery as input and 
	 * (a) generates an extraction method (currently SQL query only) 
	 * (b) executes the extraction method
	 * (c) by simply using the Result object of the extraction method, it also populates a Result object
	 * (d) converts the resultSet of the query execution to a 2d String array
	 * 
	 * @param currentCubeQuery the {@link CubeQuery} that it to be executed (must have been prepared before)
	 * @return the {@link Result} of the execution
	 * 
	 * 
	 * @see CubeQuery
	 * @see Result
	 * @see mainengine.SimpleQueryProcessingEngine#answerCubeQueryFromString(String)
	 */
	public Result executeQuery(CubeQuery currentCubeQuery) {
		//return this.CBase.executeQuery(currentCubeQuery);

		//1. Create an ExtractionMethod (for the moment: SQLQuery)
		ExtractionMethod extractionMethod = ExtractionMethodFactory.createMethod();  
		currentCubeQuery.setExtractionMethod(extractionMethod);   

		
		//OLD setup, where you had the execution at CubeQuery!
		//Result res = extractionMethod.getResult();
		//currentCubeQuery.produceExtractionMethod();
		//String queryString = extractionMethod.toString();	
		
		//2. Convert the cubequery to sth that the DBMS can execute, i.e., a query string
		String queryString = produceExtractionMethod(currentCubeQuery);
		//	if you DEBUG
		System.out.println("\n"+queryString);		

		Result finalResult = CBase.executeQueryToProduceResult(queryString, extractionMethod.getResult()) ;
		if (finalResult == null) {
			System.err.println("Exiting due to failure to populate the result");
			System.exit(-100);
			
		}

// OLD code		
//		//2. Fire the query and get a result set
//		ResultSet resultSet=CBase.getDatabase().executeSql(queryString);
//		
//		//3. Populate Result with the ResultSet's data via ExtractionMethod method calls
//		if (res.createResultArray(resultSet) == false) {
//			System.err.println("Exiting due to failure to populate the result");
//			System.exit(-100);
//		}
//		else
//			System.out.println("\n\n"+ "Result produced"+"\n");
		
		
		
		return finalResult;
	}

	/**
	 * Returns the string of the extraction Method of a CubeQuery
	 * <p>
	 * See the simple abstract coupling to translators via the interface {@link ICubeQueryTranslator} and its implementations.
	 * <p>
	 * @param cubeQuery  A {@link CubeQuery} that needs to be converted to SQL
	 * @return the string of the (currently) {@link SQLQuery} of a CubeQuery
	 */
	public String produceExtractionMethod(CubeQuery cubeQuery) {
		if (this.cubeQueryTranslator == null) {
			System.err.println("Unable to locate a logical to physical translator. Exiting.");
			System.exit(-1);
		}		
		return cubeQueryTranslator.produceExtractionMethod(cubeQuery);
	}//end method produceExtractionMethod(CubeQuery)

}//end class CubeManager
