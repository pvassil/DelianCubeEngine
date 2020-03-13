/**
 * 
 */
package result;

import java.util.ArrayList;

/**
 * Model component for KPIModel_SouthBoh_YR_Status
 * 
 * @author pvassil
 *
 */
public class KPI_SouthBohYR_ModelComponent extends AbstractModelComponent {

	/**
	 * Constructor for the class
	 * 
	 * @param aName  the name of the component
	 * @param aModel the model to which it belongs (here: KPIMedianBasedModel)
	 */
	public KPI_SouthBohYR_ModelComponent(String aName, AbstractModel aModel) {
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
