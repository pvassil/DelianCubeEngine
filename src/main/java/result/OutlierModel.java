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

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * The class to hold outlier detection model components
 * Also computes z-score
 * 
 * @author pvassil
 *
 */
public class OutlierModel extends AbstractModel {

	/**
	 * Simple constructor for the class: creates the components and adds them to the components attributes
	 * 
	 * @param aResult the Result to which the model refers to
	 */
	public OutlierModel(Result aResult) {
		
		super(aResult);
		
		zScoreComponent = new OutlierModelComponent("zScore", this);
		zScoreOutlierComponent = new OutlierModelComponent("zScoreOutliers", this);
		zScoreNonOutlierComponent = new OutlierModelComponent("zScoreNonOutliers", this);
		
		components.put("zScore", zScoreComponent);
		components.put("zScoreOutlier", zScoreOutlierComponent);
		components.put("zScoreNonOutlier", zScoreNonOutlierComponent);	
	}

	/**
	 * This is the method where the computation of outliers takes place
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
		double [] zScores= new double[resultSize];
		double [] outliers = new double[resultSize];
		double [] nonOutliers = new double[resultSize];

		//If new methods for computing outliers are added in the future, 
		//here is the place to add a call to the respective method
		//
		//Math.util is supposed to have Grubb's test implemented
		//giving out the top OUtlier https://en.wikipedia.org/wiki/Grubbs%27_test_for_outliers

		
		int i = 0;
		for (Cell cell: cells) {
			cellValues[i] = cell.toDouble();
			i++;
		}
		processZScoreOutliers(cellValues, zScores, outliers, nonOutliers);

		//Don't forget to feed the arrays of labels to the components
		zScoreComponent.setOutlierLabel(zScores);
		zScoreOutlierComponent.setOutlierLabel(outliers);
		zScoreNonOutlierComponent.setOutlierLabel(nonOutliers);
		
		return 0;
	}//end method

	/**
	 * The method computes zScores and determines outliers on the basis of their z-Score
	 * and an outlier threshold held by the static attribute ABS_ZSCORE_OUTLIER_THRESHOLD
	 *  	
	 * @param cellValues the input double [] array of values
	 * @param zScores    the output double [] array of zScores
	 * @param outliers   the output double [] array of 0's for nonOutliers and 1 for outliers
	 * @param nonOutliers the output double [] array of 1's for nonOutliers and 0 for outliers
	 * @return the length of the zScore array 
	 */
	private int processZScoreOutliers(double [] cellValues, double [] zScores, double [] outliers, double [] nonOutliers) {
		
		/* Std recipe to compute stats from commons math stats */
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for( int i = 0; i < cellValues.length; i++) {
			stats.addValue(cellValues[i]);
		}
System.out.println(stats.getValues().length);
		double mean = stats.getMean();
		double std = stats.getStandardDeviation();
System.out.println("MEAN " + mean + " STDEV " + std);		
		//		double median = stats.getPercentile(50);
		//		double max = stats.getMax();
		//		double min = stats.getMin();

		for(int i = 0; i < zScores.length; i++) {
			if (std != 0.0)
				zScores[i] = (cellValues[i] - mean)/std;
			else
				zScores[i] = 0;
			if ( Math.abs(zScores[i]) > ABS_ZSCORE_OUTLIER_THRESHOLD) {
				outliers[i] = 1; nonOutliers[i] = 0;  
			}
			else {
				outliers[i] = 0; nonOutliers[i] = 1;
			}
		}

		return zScores.length;
	}//end method processZScoreOutliers
	
	
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
		
		ArrayList<String> zScoreLabels = zScoreComponent.getCellLabelsAsStrings();
		ArrayList<String> zScoreOutlierLabels = zScoreOutlierComponent.getCellLabelsAsStrings();
		ArrayList<String> zScoreΝonOutlierLabels = zScoreNonOutlierComponent.getCellLabelsAsStrings();
		
		int numRows = zScoreLabels.size()+1;
		int numCols = 3;
		
		String[][] output = new String[numRows][numCols];
		output[0][0] = "zScore";
		output[0][1] = "zScoreOutlier";
		output[0][2] = "zScoreNonOutlier";
		for(int i = 1; i < numRows; i++) {
			output[i][0] = zScoreLabels.get(i-1);
			output[i][1] = zScoreOutlierLabels.get(i-1);
			output[i][2] = zScoreΝonOutlierLabels.get(i-1);
		}
		
		return output;
	}//end method

	@Override
	public String getModelName() {
		return "Z-Score_Outliers";
	}//end method

	@Override
	public String getInfoContent() {
		String result = this.getModelName() + "\n-------------------------\n\n"
				+ "We run a simple outlier detection. We compute the zScore of each measure value, and assign to (a) the outlier class, if the absolute zscore is above "+ ABS_ZSCORE_OUTLIER_THRESHOLD + " or (b) non-outlier, otherwise\n"  
				+ "Each column of the result pertains to another class of the result set, with each row referring to the respective cell of the query result";
		
		return result;
	}//end method getInfoContent()
	
	
	private OutlierModelComponent zScoreComponent;
	private OutlierModelComponent zScoreOutlierComponent;
	private OutlierModelComponent zScoreNonOutlierComponent;
	public static final Double ABS_ZSCORE_OUTLIER_THRESHOLD = 2.2;
	
}//end class
