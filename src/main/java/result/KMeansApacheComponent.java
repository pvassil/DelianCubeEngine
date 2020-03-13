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
 * The current class stands for the clusters of a cluster-set derived from the Apache KMeans.
 * 
 * @author pvassil
 * @since v.0.2
 */
public class KMeansApacheComponent extends AbstractModelComponent {

	/**
	 * Constructs the data for the cluster component
	 * 
	 * @param aName the name of the cluster i.e., the current component
	 * @param aModel The KmeansApache cluster-set model
	 */
	public KMeansApacheComponent(String aName, AbstractModel aModel) {
		super(aName, aModel);
		cellLabels = new ArrayList<String>();
	}

	/**
	 * Simply converting the internal clusterId int array to an arraylist of strings
	 *  
	 * @see result.AbstractModelComponent#getCellLabelsAsStrings()
	 */
	@Override
	public ArrayList<String> getCellLabelsAsStrings() {
		int arraySize = cellMembershipLabel.length;
		
		for(int i =0; i<arraySize; i++) {
			cellLabels.add(Integer.toString(cellMembershipLabel[i]));
		}
		return cellLabels;
	}

	/**
	 * Assigns an array of membership flags for each cell of the result, concerning the current cluster (1 if the cells belongs, 0 otherwise)
	 * 
	 * A rather important method as it is really hard to feed the current class with its data otherwise
	 * @param aRankLabel an Array of integers: for each cell of the result of the Model, 0 if it does not belong to this cluster and 1 if it does
	 */
	public void setRankLabel(int [] aBitmapForCells) {
		cellMembershipLabel = aBitmapForCells;
	}
	
	private int[] cellMembershipLabel;
	private ArrayList<String> cellLabels;

}
