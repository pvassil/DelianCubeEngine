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

/**
 * The model components for simple ranking -- relates to a RankModel model
 * Used to carry represent max, min and rank components.
 * Max (min) are "bitmaps" with 1 for the cells with the max(min) values and 0's for the rest
 * Rank is an array with the rank of the cells wrt their measure values.
 * 
 * @author pvassil
 *
 */
public class RankModelComponent extends AbstractModelComponent {

	/**
	 * @param aName Component's name
	 * @param aModel To which model (here: RankModel) the component belongs to
	 */
	public RankModelComponent(String aName, AbstractModel aModel) {
		super(aName, aModel);
		cellLabels = new ArrayList<String>();
	}

	/**
	 * Simply converting the internal ranking int array to an arraylist of strings
	 *  
	 * @see result.AbstractModelComponent#getCellLabelsAsStrings()
	 */
	@Override
	public ArrayList<String> getCellLabelsAsStrings() {
		int arraySize = rankLabel.length;
		
		for(int i =0; i<arraySize; i++) {
			cellLabels.add(Integer.toString(rankLabel[i]));
		}
		return cellLabels;
	}

	
	public void setRankLabel(int [] aRankLabel) {
		rankLabel = aRankLabel;
	}
	
	private int[] rankLabel;
	private ArrayList<String> cellLabels;
}//end class
