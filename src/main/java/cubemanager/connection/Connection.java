package cubemanager.connection;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

import cubemanager.starschema.Attribute;
import cubemanager.starschema.Table;
import result.Result;

/**
 * 
 * An abstract class for the Connection of the client.<br>
 * Will change either to RDBMSConnection or SparkConnection from the Factory.
 * 
 * @author kitos14 (Konstantinos Kadoglou)
 * <br>Email : kostas.kadoglou@gmail.com
 *
 */

public abstract class Connection {
	
	// Variables
	protected String typeOfConnection;
	protected List<Table> Tbl;
	public long queryTimeString;
	// Abstracted methods
	/**
	 * 
	 * Generates the tables. On RDBMS using the MySQL Database. On Spark using the "attrs" files inside the cube file.
	 * 
	 */
	protected abstract void generateTableList();
	
	public abstract void registerCubeBase(HashMap<String, String> userInputList);
	
	public abstract Result executeQueryToProduceResult(String queryString, Result result);

	// Common methods
	public long getQueryTime() {
		return queryTimeString;
	}
	
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
