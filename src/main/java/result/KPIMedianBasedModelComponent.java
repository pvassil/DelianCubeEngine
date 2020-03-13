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
 * Model component for KPIMedianBasedModel. 
 * 
 * @author pvassil
 *
 */
public class KPIMedianBasedModelComponent extends AbstractModelComponent {

	/**
	 * Constructor for the class
	 * 
	 * @param aName  the name of the component
	 * @param aModel the model to which it belongs (here: KPIMedianBasedModel)
	 */
	public KPIMedianBasedModelComponent(String aName, AbstractModel aModel) {
		super(aName, aModel);
		cellLabels = new ArrayList<String>();	
	}

	/**
	 * Simply converting the internal int array to an arraylist of strings
	 * 
	 * @see result.AbstractModelComponent#getCellLabelsAsStrings()
	 */
	@Override
	public ArrayList<String> getCellLabelsAsStrings() {
		int arraySize = classBitmap.length;
		
		for(int i =0; i<arraySize; i++) {
			cellLabels.add(String.valueOf(classBitmap[i]));
		}
		return cellLabels;
	}

	public int [] getClassBitmap() {
		return this.classBitmap;
	}
	public void setClassBitmap(int[] aClassBitmap) {
		this.classBitmap = aClassBitmap;
	}
	
	int [] classBitmap;
	private ArrayList<String> cellLabels;

}
