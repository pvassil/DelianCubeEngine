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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The model component for Outlier detection. Relates to ΟutlierModel.
 * 
 * @author pvassil
 *
 */
public class OutlierModelComponent extends AbstractModelComponent {

	/**
	 * Constructor for the class
	 * 
	 * @param aName  the name of the component
	 * @param aModel the model to which it belongs (here: OutlierModel)
	 */
	public OutlierModelComponent(String aName, AbstractModel aModel) {
		super(aName, aModel);
		cellLabels = new ArrayList<String>();	}

	/**
	 * Simply converting the internal double array to an arraylist of strings
	 * Taking care of locale's too -- see also the way to do it in the private static format attributes
	 * 
	 * @see result.AbstractModelComponent#getCellLabelsAsStrings()
	 */
	@Override
	public ArrayList<String> getCellLabelsAsStrings() {
		int arraySize = outlierLabel.length;
		
		for(int i =0; i<arraySize; i++) {
			cellLabels.add(df2.format(outlierLabel[i]));
		}
		return cellLabels;
	}//end method


	public double[] getOutlierLabel() {
		return outlierLabel;
	}

	public void setOutlierLabel(double[] outlierLabel) {
		this.outlierLabel = outlierLabel;
	}

	double [] outlierLabel;
	private ArrayList<String> cellLabels;
	
	//to write as an int out of double via df0 and to use only 2 decimals (plus a "." for the decimal), independently if this runs in GR, FR, USA, ....
	private static final DecimalFormatSymbols usSymbols = new DecimalFormatSymbols(new Locale("en", "US"));
	private static DecimalFormat df0 = new DecimalFormat("###", usSymbols);//no decimals
	private static DecimalFormat df2 = new DecimalFormat("###.##", usSymbols); //two decimals i.e., 2 # after the .
}//end class
