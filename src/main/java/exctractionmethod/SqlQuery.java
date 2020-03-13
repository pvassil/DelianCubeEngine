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


package exctractionmethod;

import java.util.ArrayList;

import result.Result;

import java.sql.ResultSet;
//import java.sql.SQLException;


public class SqlQuery extends ExtractionMethod {

	private String[] SelectClauseMeasure;  	/* 0-->AggregateFuncName, 1--> field */  
	private ArrayList<String[]> FromClause; 	/* 0-->TABLE, 1-->customName */
	private ArrayList<String[]> WhereClause;	/* 0-->sqlfld1,1-->op,2-->sqlfld2 */
	private ArrayList<String[]> GroupByClause;
	//TODO: explicitly add OrderByClause
	private ResultSet resultSet;
	
	private void init(){
		FromClause=new ArrayList<String[]>();
		WhereClause=new ArrayList<String[]>();
		GroupByClause=new ArrayList<String[]>();
		SelectClauseMeasure=new String[2];		
	}

	/**
	 * ATTN: includes a <code>super()</code> call, that creates also the {@link Result} of the query!
	 * Also calls <code>init()</code> that performs the <codenew</code> construction calls for all the members,
	 * 
	 * @see ExtractionMethod#ExtractionMethod()
	 */
	public SqlQuery(){
		super();
		init();
	}

	/**
	 * NOT currently used! SQL query with a SINGLE aggregate function in the SELECT clause.
	 * <p> 
	 * ATTN: includes a <code>super()</code> call, that creates also the {@link Result} of the query!
	 * Also calls <code>init()</code> that performs the <codenew</code> construction calls for all the members,
	 * 
	 * @param Aggregatefunc The aggregate function that will be applied to the measure
	 * @param Tables	The list of Strings that captures the table names that will be involved inthe query
	 * @param Conditions  The list of Strings that captures the selection filters of the query 
	 * @param GroupAttr  The list of Strings that captures the grouper attributes of the query 
	 */
	public SqlQuery(String Aggregatefunc,ArrayList<String> Tables,ArrayList<String> Conditions,ArrayList<String> GroupAttr){
		super();
		init();
		SelectClauseMeasure=Aggregatefunc.split("~");
		for(String table:Tables){
			FromClause.add(table.split("~"));
		}
		for(String condition:Conditions){
			WhereClause.add(condition.split("~"));
		}
		for(String grouper:GroupAttr){
			GroupByClause.add(grouper.split("~"));
		}

	}

	/**
	 * NOT currently used! SQL query with TWO aggregate functions at the SELECT clause
	 * ATTN: includes a <code>super()</code> call, that creates also the {@link Result} of the query!
	 * Also calls <code>init()</code> that performs the <codenew</code> construction calls for all the members,
	 * 
	 * @param Aggregatefunc A list of 2(??) aggregate function that will be applied to the measure
	 * @param Tables	The list of Strings that captures the table names that will be involved inthe query
	 * @param Conditions  The list of Strings that captures the selection filters of the query 
	 * @param GroupAttr  The list of Strings that captures the grouper attributes of the query 
	 */
	public SqlQuery(String[] Aggregatefunc,ArrayList<String[]> Tables,ArrayList<String[]> Conditions,ArrayList<String[]> GroupAttr){
		super();
		init();
		SelectClauseMeasure[0]=Aggregatefunc[0];
		SelectClauseMeasure[1]=Aggregatefunc[1];
		FromClause.addAll(Tables);
		WhereClause.addAll(Conditions);
		GroupByClause.addAll(GroupAttr);
	}
	

	public ArrayList<String[]> getFromClauseArrayList(){
		return FromClause;
	}

	public String[] getSelectClauseMeasure(){
		return SelectClauseMeasure;
	}   

	public ArrayList<String[]> getGroupByClause(){
		return GroupByClause;
	}

	public ArrayList<String[]> getWhereClauseArrayList(){
		return WhereClause;
	}

//ATTN: the array SelectClauseMeasure is NOT the full SELECT clause, but just the AggF + Measure to produce AggF(Measure) as measure
	public String getSelectClause(){
		String ret_value="";
		ret_value+=getGroupClause()+",";
		ret_value+= SelectClauseMeasure[0]+"("+SelectClauseMeasure[1]+") as measure,COUNT(*) as countOfDetailedCells";
		return ret_value;
	}

	public String getFromClause(){
		int i=0;
		String ret_value="";

		for(String[] x : FromClause ) {    
			if(i>0) ret_value+=",";
			ret_value+=mergeStringTable(x);
			i++;
		}
		return ret_value;
	}

	public String getWhereClause(){
		int i=0;
		String ret_value="";

		for(String[] x : WhereClause ) {    
			if(i>0) ret_value+=" AND ";
			ret_value+=mergeStringTable(x);
			i++;
		}
		return ret_value;
	}

	public String getGroupClause(){/*add this to play groupper=1 to select clause*/
		String ret_value="";
		int i=0;

		for(String[] x : GroupByClause ){
			if(i>0) ret_value+=",";
			ret_value+=mergeStringTable(x);
			i++;
		}

		return ret_value;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	
	public void printQuery(){
		System.out.println(toString());
	}

	/**
	 * Returns the SQL expression of the Cube query.
	 * <p>
	 * Used for the execution of the query, as well as the debugging of what is actually executed towards the underlying database.
	 * 
	 * 
	 * @since 0.0.0 (extracted from the Cinecubes system
	 */
	public String toString(){
		//Waiting for refactoring, to fix groupers 
		//@version 0.0.1 (ORDER BY groupers rather than by measure)
		String ret_value="SELECT "+getSelectClause();
		ret_value+="\nFROM "+ getFromClause();
		ret_value+="\nWHERE "+getWhereClause();
		ret_value+="\nGROUP BY "+getGroupClause(); 
		ret_value+=getOrderClauseByMeasure(0);
		//ret_value+=getOrderClauseByGroupers(0);
		if(getGroupClause().length()>0)	return ret_value ;
		else return "SELECT '"+this.SelectClauseMeasure[0]+"'";
	}

	/*  order_type=0 -> ASCENING
	 *  order_type=1 -> DESCENDING
	 */
	public String getOrderClauseByMeasure(int order_type){
		if(order_type==0) return "\nORDER BY measure ASC";
		return "\nORDER BY measure DESC";
	}
	
	/**
	 * Waiting to be used.
	 * 
	 * 2018-08-21: Have fixed dates in the pkdd99 database (months are listed as YYY-MM now)
	 * Can now: sort by groupers in toString()
	 * @param order_type
	 * @return
	 */
	
	public String getOrderClauseByGroupers(int order_type){
		String orderClauseString = "\nORDER BY "+ getGroupClause(); 
		if(order_type==0) return orderClauseString + " ASC";
		return orderClauseString + " DESC";
	}

	private String mergeStringTable(String[] tomerge){
		String ret_value="";
		for(int i=0;i<tomerge.length;i++){
			ret_value+=" "+tomerge[i];
		}
		return ret_value;
	}

	@Override
	public void addReturnedFields(String aggregationFuction, String attribute){
		SelectClauseMeasure[0] =aggregationFuction;
		SelectClauseMeasure[1]= attribute;
	}


	@Override
	public boolean compareExtractionMethod(ExtractionMethod toCompare) {
		int count=0;
		SqlQuery tocomp=(SqlQuery)toCompare;
		for(int i=0;i<this.WhereClause.size();i++){
			if(tocomp.WhereClause.get(i)[0]==this.WhereClause.get(i)[0]){
				if(tocomp.WhereClause.get(i)[1]==this.WhereClause.get(i)[1]){
					if(tocomp.WhereClause.get(i)[2]==this.WhereClause.get(i)[2]){
						count++;
					}
				}
			}
		}
		return (count == this.WhereClause.size() ? true : false);
	}

	@Override
	public void addFilter(String[] index) {
		this.WhereClause.add(index);
	}

	@Override
	public void addGroupers(String[] index) {
		this.GroupByClause.add(index);
	}

	@Override
	public void addSourceCube(String[] index) {
		this.FromClause.add(index);
	}


//	public boolean populateResultArray(){			
//		Result result = this.getResult();
//		
//		String[][] resultArray = result.getResultArray();
//		int columns;
//        int rows;
//        boolean ret_value=false;
//     
//		try {
//
//			resultSet.last();
//			rows=resultSet.getRow();
//			if(rows>0){
//				
//				ret_value=true;
//				columns=resultSet.getMetaData().getColumnCount();
//
//				//back to first line
//		        resultSet.first();
//		        resultSet.beforeFirst();
//		        resultArray = new String[rows+2][columns];
//		        for(int i=1;i<=columns;i++) {
//		        	resultArray[0][i-1]=resultSet.getMetaData().getColumnName(i);
//		        	resultArray[1][i-1]=resultSet.getMetaData().getColumnLabel(i);
//		        }
//		        
//		        //TitleOfColumns=resultSet.getMetaData().getColumnName(1);
//						
//		        while(resultSet.next()){
//		           for(int j=0;j<columns;j++){
//		        	   resultArray[resultSet.getRow()+1][j]=resultSet.getString(j+1);
//		           }
//		           
//		        }
//		       
//		      
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ret_value;
//	}

}//end of class SqlQuery
