/**
 * 
 */
package result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * A KPI for the query on South Bohemia Loans per year and status
 * 
 * Specifically tailored to see if we can address KPI that pertain to specific queries.
 * 
 * @author pvassil
 *
 */
public class KPIModel_SouthBoh_YR_Status extends AbstractModel {

	/**
	 * Constructor
	 * 
	 * @param aResult The result to which the KPI pertains
	 */
	public KPIModel_SouthBoh_YR_Status(Result aResult) {
		super(aResult);
		expectedValues = new HashMap<String, Double> ();
		expectedValues.put("1995"+"\t"+"Contract Finished/Loan not Payed",  105112.2948 );
		expectedValues.put("1996"+"\t"+"Contract Finished/Loan not Payed",  78997.13213 );
		expectedValues.put("1996"+"\t"+"Running Contract/Client in Debt",  28284.13038 );
		expectedValues.put("1994"+"\t"+"Contract Finished/Loan not Payed",  519609.4954 );
		expectedValues.put("1993"+"\t"+"Contract Finished/No Problems",  330541.4876 );
		expectedValues.put("1995"+"\t"+"Contract Finished/No Problems",  606872.7004 );
		expectedValues.put("1997"+"\t"+"Running Contract/Client in Debt",  52982.55675 );
		expectedValues.put("1996"+"\t"+"Contract Finished/No Problems",  795471.4401 );
		expectedValues.put("1994"+"\t"+"Contract Finished/No Problems",  45557.37412 );
		expectedValues.put("1997"+"\t"+"Contract Finished/No Problems",  744565.4723 );
		expectedValues.put("1995"+"\t"+"Running Contract/OK",  704182.8168 );
		expectedValues.put("1998"+"\t"+"Running Contract/Client in Debt",  830215.9913 );
		expectedValues.put("1997"+"\t"+"Running Contract/OK",  1511991.542 );
		expectedValues.put("1996"+"\t"+"Running Contract/OK",  1050231.323 );
		expectedValues.put("1998"+"\t"+"Running Contract/OK",  2265432.396 );

		lowComponent = new KPI_SouthBohYR_ModelComponent("Low", this);
		okComponent = new KPI_SouthBohYR_ModelComponent("OK", this);
		highComponent = new KPI_SouthBohYR_ModelComponent("High", this);
		this.components.put("Low", lowComponent);
		this.components.put("OK", okComponent);
		this.components.put("High", highComponent);
	
	}//end constructor

	/* (non-Javadoc)
	 * @see result.AbstractModel#compute()
	 */
	@Override
	public int compute() {
		String [][] actualValues = result.getResultArray();
		
		int resultSize = actualValues.length;
		int [] low= new int[resultSize];
		int [] ok = new int[resultSize];
		int [] high = new int[resultSize];
		Arrays.fill(low,0); Arrays.fill(ok,1); Arrays.fill(high,0);

		
		for (int i = 2; i<actualValues.length;i++) {
			String coordinates = actualValues[i][0].trim() + "\t" + actualValues[i][1].trim();
			Double measure = Double.valueOf(actualValues[i][2]);
			Double expected = expectedValues.get(coordinates);
			if(expected == null) {
				System.out.println(coordinates);
				System.exit(-1000);
			}
			Double diff = measure - expected;
			if ( diff > 200000) {
				high[i] = 1;  ok[i] = 0;
			}
			if ( diff < -200000) {
				low[i] = 1;  ok[i] = 0;
			}
		}
		
		lowComponent.setClassBitmap(low);
		okComponent.setClassBitmap(ok);
		highComponent.setClassBitmap(high);
		return 0;
	}

	/** 
	 * KPI name
	 * @see result.AbstractModel#getModelName()
	 */
	@Override
	public String getModelName() {
		return "KPI_for_SouthBohemia_Loans_per_year_and_status";
	}

	/**
	 * Formats the model as a 2D array of strings
	 * Each column pertains to a component
	 * First row is the header per component
	 * Each row, next, pertains to the respective cell
	 * 
	 * @see result.AbstractModel#printAs2DStringArray()
	 */
	@Override
	public String[][] printAs2DStringArray() {

		ArrayList<String> lowLabels = lowComponent.getCellLabelsAsStrings();
		ArrayList<String> okLabels = okComponent.getCellLabelsAsStrings();
		ArrayList<String> highLabels = highComponent.getCellLabelsAsStrings();
		
		int numRows = okLabels.size()+1;
		int numCols = 3;
		
		String[][] output = new String[numRows][numCols];
		output[0][0] = "Low";
		output[0][1] = "OK";
		output[0][2] = "High";
		for(int i = 1; i < numRows; i++) {
			output[i][0] = lowLabels.get(i-1);
			output[i][1] = okLabels.get(i-1);
			output[i][2] = highLabels.get(i-1);
		}
		
		return output;
	}//end print2D

	/** 
	 * Info content
	 * 
	 * @see result.AbstractModel#getInfoContent()
	 */
	@Override
	public String getInfoContent() {
		String result = this.getModelName() + "\n-------------------------\n\n"
				+ "We run a simple KPI computation. We have a randomly generated expected value per cell dimensions and compare it to the actual one";
		return result;
	}

	private HashMap<String, Double> expectedValues;
	private KPI_SouthBohYR_ModelComponent lowComponent = null;
	private KPI_SouthBohYR_ModelComponent okComponent = null;
	private KPI_SouthBohYR_ModelComponent highComponent = null;
}//end class
