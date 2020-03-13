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

import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 * This class supports k-Means by Apache which requires that the values fed to it implement the Clusterable interface
 * 
 *  Practically this means that the getPoint() method is implemented.
 *  
 * @author pvassil
 *
 */
public class CellWrapper implements Clusterable {
		    private double[] points;
		    private Cell cell;

		    public CellWrapper(Cell aCell) {
		        this.cell = aCell;
		        this.points = new double [1];
		        this.points[0] = this.cell.toDouble().doubleValue();
		    }

		    public Cell getCell() {
		        return cell;
		    }

		    public double[] getPoint() {
		        return points;
		    }
}
