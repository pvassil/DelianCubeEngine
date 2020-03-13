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
import java.util.TreeMap;

import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;

/**
 * The class to hold ranking information. Originally designed to hold
 * a bitmap for cells with the min and max values and an array with the rankings of the cells
 * with respect to their measure
 *  
 * @author pvassil
 *
 */
public class RankModel extends AbstractModel {

	/**
	 * Creates the components of the model and adds them to the "components" attribute
	 *   
	 * @param aResult  The result to which the ranking belongs to
	 */
	public RankModel(Result aResult) {
		super(aResult);
		maxComponent = new RankModelComponent("max", this);
		minComponent = new RankModelComponent("min", this);
		rankComponent = new RankModelComponent("rank", this);
		
		components.put("max", maxComponent);
		components.put("min", minComponent);
		components.put("rank", rankComponent);
	}

	/** Computes three model components, (a) a ranking of cell values, (b) the max and (c) the min cell.
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
		double[] cellAuxRanks = new double[resultSize];
		int [] cellRanks = new int[resultSize];
		int [] cellMax = new int[resultSize];
		int [] cellMin = new int[resultSize];
		
		//unnecessary, as Java guarantees zero init, yet ...
		Arrays.fill(cellRanks, 0);
		Arrays.fill(cellMax, 0);
		Arrays.fill(cellMin, 0);
		
		int i = 0;
		for (Cell cell: cells) {
			cellValues[i] = cell.toDouble();
			i++;
		}
		
		NaturalRanking ranking = new NaturalRanking(NaNStrategy.MAXIMAL, TiesStrategy.SEQUENTIAL);
		cellAuxRanks = ranking.rank(cellValues);

		for (i=0; i<resultSize; i++) {
			cellRanks[i] = (int) Math.round(cellAuxRanks[i]);
			
			if (cellRanks[i] == 1.0)
				cellMin[i] = 1;
			if (cellRanks[i] == resultSize)
				cellMax[i] = 1;
		}
				
		//adding the arrays to the respective components...
		maxComponent.setRankLabel(cellMax);
		minComponent.setRankLabel(cellMin);
		rankComponent.setRankLabel(cellRanks);
		
		return 0;
	}//end method

	/** Formats the output as a 2D string array. The order of columns is (a) Rank, (b) Max, and (c) Min.
	 * The first row is the header with the name of the component.
	 * 
	 * @see result.AbstractModel#printAs2DStringArray()
	 */
	@Override
	public String[][] printAs2DStringArray() {
		ArrayList<String> rankLabels = rankComponent.getCellLabelsAsStrings();
		ArrayList<String> maxLabels = maxComponent.getCellLabelsAsStrings();
		ArrayList<String> minLabels = minComponent.getCellLabelsAsStrings();
		
		int numRows = rankLabels.size()+1;
		int numCols = 3;
		
		String[][] output = new String[numRows][numCols];
		output[0][0] = "Rank";
		output[0][1] = "Max";
		output[0][2] = "Min";
		for(int i = 1; i < numRows; i++) {
			output[i][0] = rankLabels.get(i-1);
			output[i][1] = maxLabels.get(i-1);
			output[i][2] = minLabels.get(i-1);
		}
		
		return output;
	}//end method
	
	@Override
	public String getModelName() {
		return "Ranks";
	}//end method
	
	@Override
	public String getInfoContent() {
		String result = this.getModelName() + "\n-------------------------\n\n"
				+ "We run a simple ranking computation. We compute the rank of each cell based on its measure value, and provide a bitmap for the max and min values\n"  
				+ "Each column of the result pertains to another component of the result set, with each row referring to the respective cell of the query result";
		
		return result;
	}//end method getInfoContent()
	
	
	private RankModelComponent rankComponent;
	private RankModelComponent maxComponent;
	private RankModelComponent minComponent;
}//end class



//@Override
//public int compute() {
//	ArrayList<Cell> cells = this.result.getCells();
//	int resultSize = cells.size();
//	
//	if(resultSize < 1) {
//		return -1;
//	}
//	Double [] cellValues = new Double[resultSize];
//	int [] cellRanks = new int[resultSize];
//	int [] cellMax = new int[resultSize];
//	int [] cellMin = new int[resultSize];
//	
//	//unnecessary, as Java guarantees zero init, yet ...
//	Arrays.fill(cellRanks, 0);
//	Arrays.fill(cellMax, 0);
//	Arrays.fill(cellMin, 0);
//	
//	int i = 0;
//	for (Cell cell: cells) {
//		cellValues[i] = cell.toDouble();
//		i++;
//	}
//	
//	//Tree map internally sorts keys, so we just sorted the cell values by putting them in the tree
//	//Then, by just iterating over the keys, you just compute its rank one after one
//	//ATTN: no care taken for ties + ranking starts at "1", not "0" (this is intended to reach end-users)
//	TreeMap<Double,Integer> map = new TreeMap<>();
//	for(int ii=0;i<cellValues.length;ii++) {
//	    map.put(cellValues[ii],ii);
//	}
//	
//	//iterating over the positions of the cells, i.e., the values of the treemap (here, expressed via the 'value' variable), 
//	//we can just compute their rank by their order of appearance
//	int rank = 0;
//	for(int value : map.values()) {
//	    rank++; 
//	    cellRanks[value] = rank;
//	    
//	    if(rank==1)
//	    	cellMin[value] = 1;
//	    if (rank == resultSize)
//	    	cellMax[value] = 1;
//	}
//	
//	//adding the arrays to the respective components...
//	maxComponent.setRankLabel(cellMax);
//	minComponent.setRankLabel(cellMin);
//	rankComponent.setRankLabel(cellRanks);
//	
//	return 0;
//}//end method