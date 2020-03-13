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
package result;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * A global model applicable to any query result. Assess Low/OK/High based on their distance from the median
 * 
 * A simple KPI test
 * 
 * @author pvassil
 * @since v.0.2
 */
public class KPIMedianBasedModel extends AbstractModel {

	/**
	 * Simple constructor for the class: creates the components and adds them to the components attributes
	 * 
	 * @param aResult the Result to which the model refers to
	 */
	public KPIMedianBasedModel(Result aResult) {
		super(aResult);

		lowComponent = new KPIMedianBasedModelComponent("Low", this);
		okComponent = new KPIMedianBasedModelComponent("OK", this);
		highComponent = new KPIMedianBasedModelComponent("High", this);
		this.components.put("Low", lowComponent);
		this.components.put("OK", okComponent);
		this.components.put("High", highComponent);
	}

	/** 
	 *	Assigns each cell with a label based on a set of rules. 
	 * 
	 * The actual computation takes place in the applyKPIrules method
	 * 
	 * @see result.AbstractModel#compute()
	 */
	@Override
	public int compute() {
		ArrayList<Cell> cells = this.result.getCells();
		int resultSize = cells.size();
		
		if(resultSize < 1) {
			return -1;
		}

		double[] cellValues = new double[resultSize];	
		int i = 0;
		for (Cell cell: cells) {
			cellValues[i] = cell.toDouble();
			i++;
		}
		
		int [] low= new int[resultSize];
		int [] ok = new int[resultSize];
		int [] high = new int[resultSize];

		applyKPIRules(cellValues, low, ok, high);

		lowComponent.setClassBitmap(low);
		okComponent.setClassBitmap(ok);
		highComponent.setClassBitmap(high);
		
		return 0;
	}//end compute

	/**
	 * Labels cells as "low", "OK", "high" on the basis of a rule
	 * 
	 * @param cellValues array of double with input cell measure values
	 * @param low		array of int with 1 if the respective value is low, 0 otherwise
	 * @param ok		array of int with 1 if the respective value is neither low or high, 0 otherwise
	 * @param high		array of int with 1 if the respective value is high, 0 otherwise
	 */
	private void applyKPIRules(double[] cellValues, int[] low, int[] ok, int[] high) {
		int i;
		
		Arrays.fill(low,0); Arrays.fill(ok,1); Arrays.fill(high,0);
		/* Std recipe to compute stats from commons math stats */
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for(i = 0; i < cellValues.length; i++) {
			stats.addValue(cellValues[i]);
		}
		this.median = stats.getPercentile(50);
		System.out.println("MEDIAN " + median);
		
		for(i = 0; i < cellValues.length; i++) {
			if ( cellValues[i] > 1.5 * median) {
				high[i] = 1;  ok[i] = 0;
			}
			if ( cellValues[i] < 0.5 * median) {
				low[i] = 1;  ok[i] = 0;
			}
		}
	}

	/** 
	 * Return "KPIMedianBasedModel"
	 * 
	 * @see result.AbstractModel#getModelName()
	 */
	@Override
	public String getModelName() {
		return "KPIMedianBasedModel";
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
	
	@Override
	public String getInfoContent() {
		String result = this.getModelName() + "\n-------------------------\n\n"
				+ "We run a simple KPI computation. THe rules are that a cell is (a) low if the measure value is below half of the median value, (b) high if higher than 1.5 of the median, or (c) OK otherwise\n"
				+ "The median is: " + median + "\n"
				+ "Each column of the result pertains to another class of the result set, with each row referring to the respective cell of the query result";
		
		return result;
	}//end method getInfoContent()
	
	private double median = 0.0;
	private KPIMedianBasedModelComponent lowComponent = null;
	private KPIMedianBasedModelComponent okComponent = null;
	private KPIMedianBasedModelComponent highComponent = null;
}//end class
