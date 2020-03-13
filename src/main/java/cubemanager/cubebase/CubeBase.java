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

import java.time.Instant;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cubemanager.connection.Connection;
import cubemanager.connection.ConnectionFactory;
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
	private Connection connection;
	public List<Dimension> dimensions;
	public List<BasicStoredCube> BasicCubes;
 
	public Connection getConnection(){
		return connection;
	}

	public CubeBase(String typeOfConnection, HashMap<String, String> userInputList) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connection = connectionFactory.createConnection(typeOfConnection, userInputList);
		dimensions = new ArrayList<Dimension>();
		BasicCubes = new ArrayList<BasicStoredCube>();
	}

	public void registerCubeBase(HashMap<String, String> userInputList) {
		connection.registerCubeBase(userInputList);
	}

	public void addDimension(String nameDim) {
		dimensions.add(new Dimension(nameDim));
	}

	public void addDimensionTbl(String dimensionTbl) {
		Table tmp_tbl = connection.getConnectionTableInstance(dimensionTbl);
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
				lvlattr.setAttribute(connection.getFieldOfSqlTable(tmp_str[0],
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

	public void addConnectionRelatedTbl(String connectionTable) {
		Table tmp_tbl = connection.getConnectionTableInstance(connectionTable);
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
			Measure msrToAdd = new Measure(i +1,item, this.connection.getFieldOfSqlTable(tmp[0], tmp[1]));
			last_cube.Msr.add(msrToAdd);
		}
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
		return connection.executeQueryToProduceResult(queryString, result);
	}
	
	public long getQueryTime() {
		return connection.getQueryTime();
	}
	
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

	public List<Dimension> getDimensions(){
		return dimensions;
	}
	
	public List<BasicStoredCube> getBasicCubes(){
		return BasicCubes;
	}

}
