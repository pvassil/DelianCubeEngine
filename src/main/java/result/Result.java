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

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * The main class for storing the result of a cube query.
 * <p>
 * Contains a <i>list</i> of {@link Cell}s which is an ordered list of the result's cells
 * Contains a  <i>list</i> of Strings, for the names of the database columns used for answering the query that produced the result
 * Contains a  <i>list</i> of Strings, for the labels of the columns at the query expression. Think of it as:
 *    SELECT  columnName AS columnLabel
 * Contains a 2D array of strings (rows x columns), with each tuple of the result being a row and its column being an attribute.
 * 
 *  
 * @author pvassil
 * @author dgkesouli
 * @version v.0.1
 * @since v.0.0 Extracted from Cinecubes and only slightly modified
 * 
 * @see Cell
 */
public class Result {
    
	/**
	 * @uml.property  name="resultArray"
	 */
	private String [][] resultArray; /* 1st row has Column Name, 
										2nd row has Column Labels, 
										the following rows are data. 
										MIN: resultArray[2][columns-1]
										MAX: resultArray[resultArray.length-1][columns-1]
										*/ 	
	private ArrayList<Cell> cells;
	private ArrayList<String> columnNames;
	private ArrayList<String> columnLabels;

    
	public Result(){
		//setRowPivot(new TreeSet<String>());
		//setColPivot(new TreeSet<String>());
		
		cells = new ArrayList<Cell>();
		columnNames = new ArrayList<String>();
		columnLabels = new ArrayList<String>();
		resultArray=null;
	}
	
	public Result(int rows,int columns){
		resultArray=new String[rows][columns];
		cells = new ArrayList<Cell>();
		columnNames = new ArrayList<String>();
		columnLabels = new ArrayList<String>();

	}
	
	/**
	 * Returns the the cells of the result.
	 * Currently, the cells of the result are created in the method createResultArray
	 * 
	 * @version v.0.1
	 * @since v.0.1
	 * @return the cells of the result
	 */
	public ArrayList<Cell> getCells(){
		return cells;
	}
	
	/**
	 * Returns a 2D array of strings with the results of a CubeQuery.
	 * The first 2 lines contain the header.
	 * 
	 * @return a 2d array of strings, holding the result of a CubeQuery
	 */
	public String [][] getResultArray() {
		return resultArray;
	}

	public void setResultArray(String[][] array) {
		resultArray = array;
	}
	
	public String getCellOfResultArray(int row,int column){
		return resultArray[row][column];
	} 
	
	public ArrayList<String> getColumnNames(){
		return columnNames;
	}
	
	public ArrayList<String> getColumnLabels(){
		return columnLabels;
	}
	
	

	/**
	 * Prints the <code>Cell</code>s of this to an output stream.
	 * <p>
	 * Columns are delimited by a delimiter. The first line includes the labels.
	 * 
	 * @since v.0.1
	 * @param printstream The output stream
	 */
	public void printCellsToStream(PrintStream printstream){
		String delim = "\t";
		for (int i =0; i<columnLabels.size();i++)
			printstream.print(columnLabels.get(i)+delim);
		printstream.println();
		for (int i =0; i< cells.size();i++)
			printstream.println(cells.get(i).toString(delim));
	}
	

}//end of class Result




// ************************* KILLED WITH POWER ***************************************

//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.TreeSet;

///**
//* This is the core method that currently takes the output of a concrete extraction method and populates the internal constructs of this class.
//* <p>
//* The constructs currently populated are (a) a 2D string array of rows x columns + (b) a List of cells.
//* ATTN: it is a _LIST_ because the cells, in the future, will be populated with highlights, that will also be LISTS of values.
//* Therefore the ordering is important.
//* 
//* Grossly Refactored Too relationally dependent. Takes a ResultSet and parses it.
//*       Probably must move this functionality somewhere in the (Relational) Database "circuit"
//*       where you execute sth like executeQueryAndPRoduceResult(String query, Result result)
//*       
//* @param resultSet A ResultSet that has resulted from the execution of a concrete SQLQuery
//* @return false, if there is only one column (the count(*)) or true otherwise
//* 
//* @author pvassil (small touches, added Cells)
//* @author dgkesouli (mainly)
//* @version 0.1
//* @since v.0.0 Cinecubes (this is why it contains stuff that might need reconsideration)
//*/
//public boolean createResultArray(ResultSet resultSet){			
//		int columnCount;
//       int rowCount;
//       boolean ret_value=false;
//       String titleOfColumns;
//    
//		try {
//			resultSet.last();
//			rowCount=resultSet.getRow();
//			if(rowCount>0){
//				
//				ret_value=true;
//				columnCount=resultSet.getMetaData().getColumnCount();
//				
//				//unclear what this little check does, for the case the result has a single column
//				if(columnCount==1) {
//					resultSet.beforeFirst();
//					while(resultSet.next()) titleOfColumns=resultSet.getString(1);
//					return ret_value;
//				}
//				
//		        //back to first line
//		        resultSet.first();
//		        resultSet.beforeFirst();
//		        resultArray=new String[rowCount+2][columnCount];
//		        for(int i=1;i<=columnCount;i++) {
//		        	resultArray[0][i-1]=resultSet.getMetaData().getColumnName(i);
//		        	columnNames.add(resultSet.getMetaData().getColumnName(i));
//		        	resultArray[1][i-1]=resultSet.getMetaData().getColumnLabel(i);
//		        	columnLabels.add(resultSet.getMetaData().getColumnLabel(i));
//		        }
//		        
//		        //TitleOfColumns=resultSet.getMetaData().getColumnName(1);
//						
//		        while(resultSet.next()){
//		        	String [] values = new String[resultSet.getMetaData().getColumnCount()];
//		        	
//		           for(int i=0;i<columnCount;i++){
//		        	   String value = resultSet.getString(i+1);
//		        	   resultArray[resultSet.getRow()+1][i]=value;
//		        	   values[i] = value;
//		           }
//		           
//		           /*
//		            * VERY IMPORTANT: here is where cells are created!
//		            * We need them to be in a List, so that they are ordered!
//		            */
//		           cells.add(new Cell(values));
//		           
//		           //SILENCED
//		           //this.ColPivot.add(resultSet.getString(1));
//                  //this.RowPivot.add(resultSet.getString(2));
//		        }
//		       
//		      
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ret_value;
//	}
//
///**
//* Not used any more: receives a String[][] of tuples and prints then in a PrintStream
//* 
//* @param printstream The output stream
//* @param result A 2D array of Strings containing the input
//*/
//public void printStringArrayTostream(PrintStream printstream, String[][] result){
//	String delim = "\t";
//
//	/*
//	 * Observe how resultArray gets populated above:
//	 * 			    resultArray[0][i-1]=resultSet.getMetaData().getColumnName(i);
//		        	resultArray[1][i-1]=resultSet.getMetaData().getColumnLabel(i);
//	 * So, we omit the 0th column from the output, and start from i = 1	        	
//	 */
//	
//   for(int i=1;i<result.length;i++){
//       for(int j=0;j<result[i].length;j++) printstream.print(result[i][j]+delim);
//       printstream.println();
//   }
//
//}

///**
//* @uml.property  name="colPivot"
//* @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.String"
//*/
//// private TreeSet<String> ColPivot; /* In all option Colpivot has the values of 1st Column of resultArray exept when merge two tables like max,min comparison*/
///**
//* @uml.property  name="rowPivot"
//* @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.String"
//*/
////private TreeSet<String> RowPivot; /* In all option RowPivot has the values of 2nd Column of resultArray exept when merge two tables like max,min comparison*/
///**
//* @uml.property  name="titleOfColumns"
//*/
////private String TitleOfColumns;
///**
//* @uml.property  name="titleOfRows"
//*/
////private String TitleOfRows;
///**
//* @uml.property  name="max"
//*/
////private Float max;
///**
//* @uml.property  name="min"
//*/
////private Float min;
//
///**
//* @return the maxValue
//*/
///*	public float getMaxValue() {
//	return max;
//}
//*/
///**
//* @param maxValue the maxValue to set
//*/
///*	public void setMaxValue(float val) {
//	this.max = val;
//}*/
//
///**
//* @return the maxValue
//*/
///*	public float getMinValue() {
//	return min;
//}
//*/
///**
//* @param maxValue the maxValue to set
//*/
///*	public void setMinValue(float val) {
//	this.min = val;
//}
//*/	
///**
//* @return the colPivot
//*/
///*	public TreeSet<String> getColPivot() {
//	return ColPivot;
//}
//*/
///**
//* @param colPivot the colPivot to set
//*/
///*	public void setColPivot(TreeSet<String> colPivot) {
//	ColPivot = colPivot;
//}
//*/
///**
//* @return the rowPivot
//*/
///*	public TreeSet<String> getRowPivot() {
//	return RowPivot;
//}
//*/
///**
//* @param rowPivot the rowPivot to set
//*/
///*	public void setRowPivot(TreeSet<String> rowPivot) {
//	RowPivot = rowPivot;
//}	
//*/	
//
///*public String getTitleosColumns(){
//	return TitleOfColumns;
//}*/
//
//
///**
//* @param stringArrayOfResults
//* @uml.property  name="resultArray"
//*/
///* SILENCED due to absence of usage
//public void setResultArray(String [][] stringArrayOfResults) {
//	this.resultArray = new String [stringArrayOfResults.length][stringArrayOfResults[0].length]; 
//	for(int i=0;i<stringArrayOfResults.length;i++){
//		for(int j=0;j<stringArrayOfResults[i].length;j++) setCellOfResultArray(stringArrayOfResults[i][j],i,j); 
//	}
//}
//
//public void setCellOfResultArray(String setValue,int row,int column){
//	resultArray[row][column]=new String(setValue);
//}
//
//*/
