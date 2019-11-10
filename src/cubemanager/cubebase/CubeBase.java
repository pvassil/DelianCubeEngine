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


package cubemanager.cubebase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cubemanager.starschema.Database;
import cubemanager.starschema.DimensionTable;
import cubemanager.starschema.FactTable;
import cubemanager.starschema.Table;
//import exctractionmethod.ExtractionMethod;
//import exctractionmethod.ExtractionMethodFactory;
//import exctractionmethod.Result;
import result.Result;

public class CubeBase {

	private String username;
	private String password;
	private String name;
	private Database DB;
	public List<Dimension> dimensions;
	public List<BasicStoredCube> BasicCubes;
 
	public Database getDatabase(){
		return DB;
	}
	/**
	 * The method looks for the file dbc.ini inside the folder prescribed as the method's parameter.
	 * Then it processed the file and relates to the database prescribed in the dbc.ini file
	 *  
	 * @param lookupFolder	The FOLDER where the input info for the project lies. Must be in the InputFiles folder.
	 */
	public CubeBase(String lookupFolder, Boolean isRunningSpark) {
		if (isRunningSpark == false) {
			try {
				String line;
				Scanner scanner = new Scanner(new FileReader("InputFiles/" + lookupFolder
						+ "/dbc.ini"));
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					String results[] = line.split(";");
					DB = new Database(results[1], results[3]);
					dimensions = new ArrayList<Dimension>();
					BasicCubes = new ArrayList<BasicStoredCube>();
				}
				scanner.close();
			} catch (FileNotFoundException e1) {
				System.err.println("Unable to work correctly with dbc.ini for the setup of the Cubebase");
				e1.printStackTrace();
			}
		} else {
			DB = new Database();
			dimensions = new ArrayList<Dimension>();
			BasicCubes = new ArrayList<BasicStoredCube>();
		}
	}

	public void registerCubeBase(String filename, String username,
			String password) {
		name = filename;
		this.username = username;
		this.password = password;
		DB.setDBName(name);
		DB.setUsername(username);
		DB.setPassword(password);
		DB.registerDatabase();
		DB.GenerateTableList();
	}
	
	// Added by Konstantinos Kadoglou
	public void registerCubeBase(String filename, String cubeName) {
		DB.setDBName(filename);
		DB.GenerateTableListSpark(filename, cubeName);
	}

	public void addDimension(String nameDim) {
		dimensions.add(new Dimension(nameDim));
	}

	public void addDimensionTbl(String dimensionTbl) {
		Table tmp_tbl = DB.getDBTableInstance(dimensionTbl);
		DimensionTable dm = new DimensionTable(tmp_tbl. getTableName());
		dm.addAllAttribute(tmp_tbl);
		this.getLastInsertedDimension().setDimTbl(dm);
	}

	public void setDimensionLinearHierachy(ArrayList<String> hierachylst,
			List<String> fld_tbl, List<String> custFld_name) {
		Dimension tmp;
		tmp = this.getLastInsertedDimension();
		LinearHierarchy LinHier = new LinearHierarchy();
		LinHier.setDimension(tmp);
		for (int i = 0; i < hierachylst.size(); i++) {
			if (custFld_name.get(i).equals(hierachylst.get(i))) {
				Level lvl = new Level(i, hierachylst.get(i));

				String[] tmp_str = fld_tbl.get(i).split("\\.");

				LevelAttribute lvlattr = new LevelAttribute(tmp_str[1],
						tmp_str[0]);
				lvlattr.setLevel(lvl);
				lvlattr.setAttribute(DB.getFieldOfSqlTable(tmp_str[0],
						tmp_str[1]));

				lvl.addLevelAttribute(lvlattr);
				lvl.setHierarchy(LinHier);

				LinHier.lvls.add(lvl);
			}
		}
		tmp.getHier().add(LinHier);
	}

	public Dimension getLastInsertedDimension() {
		return dimensions.get(dimensions.size() - 1);
	}

	public void addCube(String name_creation) {
		BasicCubes.add(new BasicStoredCube(name_creation));

	}

	public void addSqlRelatedTbl(String sqltable) {
		Table tmp_tbl = DB.getDBTableInstance(sqltable);
		BasicStoredCube tmp = BasicCubes.get(BasicCubes.size() - 1);
		FactTable fctbl = new FactTable(tmp_tbl. getTableName());
		fctbl.addAllAttribute(tmp_tbl);
		tmp.setFactTable(fctbl);
	}

	public void setCubeDimension(ArrayList<String> dimensionlst,
			ArrayList<String> DimemsionRefField) {
		BasicStoredCube last_cube = BasicCubes.get(BasicCubes.size() - 1);
		int i = 0;
		for (String item : dimensionlst) {
			int tmp = findDimensionIdByName(item);
			if (tmp == -1) {
				System.err.println("Error with Dimension At Cube construction!");
				System.exit(1);
			}
			last_cube.addDimension(this.dimensions.get(tmp));
			last_cube.addDimensionRefField(DimemsionRefField.get(i));
			i++;
		}
	}

	public void setCubeMeasure(ArrayList<String> measurelst,
			ArrayList<String> measureRefField) {
		BasicStoredCube last_cube = BasicCubes.get(BasicCubes.size() - 1);
		int i = 0;
		for (String item : measurelst) {
			String[] tmp = measureRefField.get(i).split("\\.");
			Measure msrToAdd = new Measure(i +1,item, this.DB.getFieldOfSqlTable(tmp[0], tmp[1]));
			last_cube.Msr.add(msrToAdd);
		}
	}

	public Connection getSqlConnection() {
		return DB.getConnection();
	}

	private Integer findDimensionIdByName(String nameDimension) {
		int ret_val = -1;
		int i = 0;
		while (ret_val == -1 && i < this.dimensions.size()) {
			if (this.dimensions.get(i).hasSameName(nameDimension))
				ret_val = i;
			i++;
		}
		return ret_val;
	}

	public boolean returnIfTableIsDimensionTbl(String table) {
		boolean ret_value = true;
		for (BasicStoredCube basiccube : this.BasicCubes) {
			if (basiccube.FactTable(). getTableName().equals(table))
				ret_value = false;
		}
		return ret_value;
	}

	public Result executeQueryToProduceResult(String queryString, Result result) {
		return DB.executeQueryToProduceResult(queryString, result);
	}
	
//	/*
//	 * OPEN SINCE CINECUBES:
//	 * Needs a code or parameter to finding proper cube. Now we check only the last cube in the cubebase 
//	 */
//	public Measure getMeasureInstanceByName(String name) {
//		BasicStoredCube last_cube = BasicCubes.get(BasicCubes.size() - 1);
//		for (int i = 0; i < last_cube.Msr.size(); i++) {
//			Measure msr = last_cube.Msr.get(i);
//			if (msr.getName().equals(name))
//				return msr;
//		}
//		return null;
//	}
	
	public String getChildOfGamma(String[] gamma_tmp) {
		String ret_value = null;
		for (int i = 0; i < dimensions.size(); i++) {
			Dimension tmp = dimensions.get(i);
			if (tmp.hasSameName(gamma_tmp[0])) {
				for (Hierarchy hier : tmp.getHier()) {
					for (int j = 0; j < hier.lvls.size(); j++) {
						if (hier.lvls.get(j).getName().equals(gamma_tmp[1])) {
							if (j > 0)
								ret_value = hier.lvls.get(j - 1).getName();
						}
					}
				}
			}
		}
		return ret_value;
	}


}
