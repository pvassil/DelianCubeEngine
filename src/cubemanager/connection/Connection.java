package cubemanager.connection;

import java.util.HashMap;
import java.util.List;

import cubemanager.starschema.Attribute;
import cubemanager.starschema.Table;
import result.Result;

public abstract class Connection {
	
	// Variables
	protected String typeOfConnection;
	protected List<Table> Tbl;
	
	// Abstracted methods
	protected abstract void generateTableList();
	
	public abstract void registerCubeBase(HashMap<String, String> userInputList);
	
	public abstract Result executeQueryToProduceResult(String queryString, Result result);

	// Common methods
	public Table getConnectionTableInstance(String nameTbl) {
		Table retTbl = null;
		for (int i = 0; i < this.Tbl.size(); i++) {
			if (this.Tbl.get(i). getTableName().equals(nameTbl))
				retTbl = this.Tbl.get(i);
		}
		if (retTbl == null) {
			System.err.println("Sql Table no exist");
			System.exit(1);
		}
		return retTbl;
	}
	
	public Attribute getFieldOfSqlTable(String table, String field) {
		return this.getConnectionTableInstance(table).getAttribute(field);
	}
	
	public String getTypeOfConnection() {
		return typeOfConnection;
	}
	
	public void printTableList() {
		for (Table item : this.Tbl) {
			System.out.println(item. getTableName());
			item.printColumns();
		}
	}
}
