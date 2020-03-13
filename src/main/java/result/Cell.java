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

/**
 * The class storing the cells of a cube
 * @author pvassil
 *
 */
public class Cell {

	private ArrayList<String> dimensionMembers;
	private String measure;
	private Integer countOfDetailedCells;

	public Cell() {
		dimensionMembers = new ArrayList<String>();
	}

	public Cell(String[] values) {
		int numFields = values.length;
		String[] aux;
		
		dimensionMembers = new ArrayList<String>();
		aux = Arrays.copyOfRange(values, 0, numFields-2);
		dimensionMembers.addAll(Arrays.asList(aux));
		
		measure = values[numFields-2];
		countOfDetailedCells = Integer.parseInt(values[numFields-1]);
		
	/*  Testing @ development	
		System.out.println("---------------");
		System.out.println("Size of dim's: " + dimensionMembers.size());
		for(String s : dimensionMembers)
			 System.out.print(s + "\t");
		System.out.print("| " +measure + "\t");
		System.out.println(countOfDetailedCells);
    */		
	}

	public ArrayList<String> getDimensionMembers() {
		return dimensionMembers;
	}

	public String getMeasure() {
		return measure;
	}

	public Integer getCountOfDetailedCells() {
		return countOfDetailedCells;
	}

	public String toString(String delimiter) {
		String result = new String();
		for(int i=0; i<dimensionMembers.size();i++)
			result = result + dimensionMembers.get(i) + delimiter;
		result = result + measure + delimiter;
		result = result+ countOfDetailedCells + delimiter;
		
		return result;
	}
	
	public Double toDouble() {
		return Double.valueOf(measure);
	}
}