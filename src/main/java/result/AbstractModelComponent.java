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
 * The abstract class of model components. All instantiations of model components must extend this class.
 * 
 *  The main idea of components is that for each cell of a result, they hold a label. 
 *  Depending on the type of model, this can be a double measurement, a boolean flag, a label, ...
 *  We require that all concrete components export the labels as Strings (so that they can be output to a result file/screen).
 *  This is done via the abstract method  getCellLabelsAsStrings().
 *  It is important that the exported values are a <i>list</i>, so that they are in one-to-one correspondence with the list of cells
 *  that the result of the containing model  has.
 *  Each concrete instantiation of the class can, of course, have more collections and public methods.
 *   
 * @author pvassil
 *
 */
public abstract class AbstractModelComponent {
	public abstract ArrayList<String> getCellLabelsAsStrings();
	protected String name;
	protected AbstractModel containingModel;

	public AbstractModelComponent(String aName, AbstractModel aModel) {
		name = aName;
		containingModel = aModel;
	}
		
	public String getName() {
		return name;
	}
	
}//end class
