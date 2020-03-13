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


package cubemanager.starschema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Table {
   
    private List<Attribute> LstAttr;
    private String tableName;
    
    public Table(String name){
    	tableName=name;
    	LstAttr=new ArrayList<Attribute>();
    }
    
    public void addAllAttribute(Table newtable){
    	LstAttr.addAll(newtable.LstAttr);
    }
    
    // RDBMS
    public void setAttribute(java.sql.Connection con){
    	try {
    		DatabaseMetaData meta = con.getMetaData();
    	    ResultSet rsColumns = meta.getColumns(null, null, tableName, null);
    	    while (rsColumns.next()) {
    	    	LstAttr.add(new Attribute(rsColumns.getString("COLUMN_NAME"),rsColumns.getString("TYPE_NAME")));
    	      }
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    // Spark
    public void setAttribute(String filename, String path) {
		BufferedReader brTest;
		try {
			brTest = new BufferedReader(new FileReader(path + "/" + filename + ".csv"));
			try {
			    String text;
				text = brTest.readLine();
			    String[] strArray = text.split(",");
			    int maxType = "Type".length();
			    int maxAttribute = "Attribute".length();
			    for (int i = 0; i < strArray.length; i = i + 2) {
					if (maxAttribute < strArray[i].length()) {
						maxAttribute = strArray[i].length();
					}
					if (maxType < strArray[i+1].length()) {
						maxType = strArray[i+1].length();
					}
			    }
			    System.out.print("+");
			    for (int i = 0; i < maxAttribute+2; i++) {
			    	System.out.print("-");
			    }
			    System.out.print("+");
			    for (int i = 0; i < maxType+2; i++) {
			    	System.out.print("-");
			    }
			    System.out.println("+");
			    String format = "| %"+ maxAttribute + "s | %-"+maxType+"s |\n";
			    System.out.format(format, StringUtils.center("Attribute", maxAttribute), StringUtils.center("Type", maxType));
			    System.out.print("+");
			    for (int i = 0; i < maxAttribute+2; i++) {
			    	System.out.print("-");
			    }
			    System.out.print("+");
			    for (int i = 0; i < maxType+2; i++) {
			    	System.out.print("-");
			    }
			    System.out.println("+");
			    for (int i = 0; i < strArray.length; i = i + 2) {
				    System.out.format(format, StringUtils.right(strArray[i], maxAttribute), StringUtils.left(strArray[i+1], maxType));
			    	LstAttr.add(new Attribute(strArray[i], strArray[i+1]));
			    }
			    System.out.print("+");
			    for (int i = 0; i < maxAttribute+2; i++) {
			    	System.out.print("-");
			    }
			    System.out.print("+");
			    for (int i = 0; i < maxType+2; i++) {
			    	System.out.print("-");
			    }
			    System.out.println("+");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    }
    
    public void printColumns(){
    	for(Attribute item : LstAttr){
    		System.out.println("----"+item.getName()+":"+item.getDatatype());
    	}
    }
    
    public String getTableName(){
    	return tableName;
    }
    
    public Attribute getAttribute(String name){
    	for(Attribute item : LstAttr){
    		if (item.getName().equals(name)) return item;
    	}
    	return null;
    }
}

