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
    
    // ABSTRACT RDBMS
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
    
    // ABSTRACT SPARK
    // Added by Konstantinos Kadoglou
    // TODO: Folder is static right now. Should be passed with parameter.
    public void setAttribute(String filename, String path) {
		BufferedReader brTest;
		try {
			brTest = new BufferedReader(new FileReader(path + "/" + filename + ".csv"));
			try {
			    String text;
				text = brTest.readLine();
			    String[] strArray = text.split(",");
			    for (int i = 0; i < strArray.length; i = i + 2) {
//					System.out.println("Attribute : " + strArray[i] + "  ~~~ Type : " + strArray[i+1]);
					System.out.format("Attribute : %-20s --> Type : %-3s", strArray[i], strArray[i+1] + "\t\t\t\t\n");
			    	LstAttr.add(new Attribute(strArray[i], strArray[i+1]));
			    }
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

