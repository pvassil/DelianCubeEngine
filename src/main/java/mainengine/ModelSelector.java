/**
 * 
 */
package mainengine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Selects which models will be executed per query
 * 
 * For the moment, the existence of this class is practically a luxury.
 * But, here we expect a large field of open research problems.
 * 
 * @author pvassil
 * since v.0.2
 */
public class ModelSelector {

	public ModelSelector(String aQueryName) {
		queryName = aQueryName;
		defaultModelNames = new String[] {"Rank","Outlier"};
		queryKPIs = new HashMap<String, String []>();
		setupQueryKPICreation();
	}//end constructor
	
	public String [] decideModelsToExecute(String queryName, String [] modelsOriginallyRequested) {
		String [] workingModelNames = null;
		String [] finalModelNames = null;

		System.out.println("\nMODELSELECTOR # decideModelsToExecute:\nRequest of " + modelsOriginallyRequested.length + " models by " + queryName);

		/* 
		 *    if you pass an non-empty parameter it works with your parameter, else it works with the defaults.
		*/
		if(modelsOriginallyRequested.length == 0) {
			workingModelNames = this.defaultModelNames;
		}
		
		if(modelsOriginallyRequested.length > 0) {
			workingModelNames = new String [modelsOriginallyRequested.length]; 
			for (int i=0; i< modelsOriginallyRequested.length; i++)
				workingModelNames[i] = modelsOriginallyRequested[i];
					//modelsOriginallyRequested.clone(); 
		}
		
		String [] finalModelNamesTry = null;
		querySpecificModelNames = lookupKPIsOfQuery(queryName);
		if(querySpecificModelNames != null) {
			finalModelNamesTry = Stream.concat(Arrays.stream(workingModelNames), Arrays.stream(querySpecificModelNames)).toArray(String[]::new);
			System.out.println("\\nMODELSELECTOR # decideModelsToExecute:\nQUERY SPECIFIC MODELS: " + finalModelNamesTry.toString());
			finalModelNames = finalModelNamesTry.clone();  
		}
		else {
			//		System.err.println("\nMODELSELECTOR # decideModelsToExecute:\nAUX Model array of " + workingModelNames.length + " models");
			finalModelNames = workingModelNames.clone();
			//		System.err.println("\nMODELSELECTOR # decideModelsToExecute:\nModel selection of " + finalModelNames.length + " models");
		}

		
		return finalModelNames;
	}

	public String [] lookupKPIsOfQuery(String queryName) {
		String [] result = queryKPIs.get(queryName);
		return result;
	}
	/**
	 * Creates a hashmap that, for each queryName, creates an array of strings with the models to be generated specifically for this query
	 */
	private void setupQueryKPICreation() {
			String [] arrayOfKPI = new String []{"loan_KPIdemo_SouthBoh_YR_Status"};
			queryKPIs.put("loan_KPIdemo_SouthBoh_YR_Status", arrayOfKPI);
			
			arrayOfKPI = new String []{"KPI_loanMonth"};
			queryKPIs.put("loan_KPIdemo_Month", arrayOfKPI);
		
	}//end method
	
	private String queryName;
	private String [] defaultModelNames;
	private String [] modelNamesToExecute;
	private String [] querySpecificModelNames;
	private HashMap<String, String[]> queryKPIs;
}//end class
