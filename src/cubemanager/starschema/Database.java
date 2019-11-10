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

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import result.Cell;
import result.Result;


public class Database {
	/**
	 * @uml.property  name="dBName"
	 */
	private String DBName;
	/**
	 * @uml.property  name="tbl"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="CubeMgr.StarSchema.Table"
	 */
	private List<Table> Tbl;

	/**
	 * @uml.property  name="connectionString"
	 */
	private String ConnectionString;
	/**
	 * @uml.property  name="dBMS"
	 */
	private String DBMS;
	/**
	 * @uml.property  name="connect"
	 */
	private Connection connect;
	/**
	 * @uml.property  name="username"
	 */
	private String Username;
	/**
	 * @uml.property  name="password"
	 */
	private String Password;

	public Database() {
		Tbl = new ArrayList<Table>();
	}

	public Database(String ConnStr, String dbtype) {
		setConnectionString(ConnStr);
		DBMS = dbtype;
		Tbl = new ArrayList<Table>();
	}

	public void registerDatabase() {
		try {
			try {
				Class.forName(DBMS).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		try {
			setConnection(DriverManager.getConnection(ConnectionString,
					Username, Password));
		} catch (SQLException ex) {
			System.err.println(ConnectionString);
			System.err.println("Connection Failed! Check output console");
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("LocalState: " + ex.getLocalizedMessage());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
	}

	public void GenerateTableList() {
		try {
			DatabaseMetaData Metadata = connect.getMetaData();
			ResultSet rs = Metadata.getTables(null, null, "%", null);
			;
			while (rs.next()) {
				Table tmp = new Table(rs.getString(3));
				tmp.setAttribute(connect);
				this.Tbl.add(tmp);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			(new ErrorClass()).printErrorMessage(ex.getMessage());
		}
	}
	
	// Added by Konstantinos Kadoglou
	// This method uses setAttribute(String) instead of setAttribute(Connection)
	// TODO: Path must be dynamic
	public void GenerateTableListSpark(String folderName, String cubeName) {
		String path = "InputFiles/" + folderName + "/" + cubeName;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("\nReading file : " + FilenameUtils.removeExtension(listOfFiles[i].getName()));
			String attrs = FilenameUtils.removeExtension(listOfFiles[i].getName());
			String name =  attrs.substring(0, attrs.length() - 6);
			System.out.println("Table : " + name);
			Table tmp = new Table(name);
			tmp.setAttribute(attrs, path);
			System.out.println();
			this.Tbl.add(tmp);
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		System.out.println();
	}

	//Not apparently used?
	public void PrintTableList() {
		for (Table item : this.Tbl) {
			System.out.println(item. getTableName());
			item.printColumns();
		}
	}

	/**
	 * @return
	 * @uml.property  name="connectionString"
	 */
	public String getConnectionString() {
		return ConnectionString;
	}

	/**
	 * @param connectionString
	 * @uml.property  name="connectionString"
	 */
	public void setConnectionString(String connectionString) {
		ConnectionString = connectionString;
	}

	public Connection getConnection() {
		return connect;
	}

	public void setConnection(Connection connect) {
		this.connect = connect;
	}

	/**
	 * @return
	 * @uml.property  name="username"
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * @param username
	 * @uml.property  name="username"
	 */
	public void setUsername(String username) {
		Username = username;
	}

	/**
	 * @return
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * @param password
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * @return
	 * @uml.property  name="dBName"
	 */
	public String getDBName() {
		return DBName;
	}

	/**
	 * @param dBName
	 * @uml.property  name="dBName"
	 */
	public void setDBName(String dBName) {
		DBName = dBName;
	}

	/**
	 * @return
	 * @uml.property  name="dBMS"
	 */
	public String getDBMS() {
		return DBMS;
	}

	/**
	 * @param dbms
	 * @uml.property  name="dBMS"
	 */
	public void setDBMS(String dbms) {
		DBMS = dbms;
	}

	public Table getDBTableInstance(String nameTbl) {
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
		return this.getDBTableInstance(table).getAttribute(field);

	}

	/**
	 * Returns the result set of a query that this method executes
	 * The connection of the database is necessary to create a <code>Statement</code> which is then executed
	 * @param query  A string with the SQL query expression to be fired
	 * @return  the result set of the input query's execution
	 */
	public ResultSet executeSql(String query) {
		ResultSet res = null;
		try {
			Statement createdStatement = connect.createStatement();
			res = createdStatement.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Returns a populated <code>Result</code> after the an input SQL query string is given as input
	 * <p>
	 * Two steps: 
	 * First the abovementioned, synonymous <code>executeSql(queryString)</code> is fired and a ResultSet is obtained.
	 * Second, the Result Set is convereted to a set of cells and a 2D String array inside the {@link Result} object
	 *  
	 * @param  queryString  A string with the SQL query to be executed
	 * @param  result  A {@link Result} that is populated with {@link Cell}s after the query is executed
	 * @return  the populated {@link Result}
	 * 
	 */
	public Result executeQueryToProduceResult(String queryString, Result result) {
		//1. Execute SQL and return a ResultSet
		ResultSet resultSet = executeSql(queryString);
		
		//2. Populate Result with the ResultSet's data via ExtractionMethod method calls
		if (populateResult(resultSet, result) == false) {
			System.out.println("Exiting due to failure to populate the result");
			System.exit(-100);
		}
		else
			System.out.println("\n\n"+ "Result produced"+"\n");

		return result;
	}


	public boolean populateResult(ResultSet resultSet, Result result){			
		int columnCount;
		int rowCount;
		boolean ret_value=false;

		try {
			resultSet.last();
			rowCount=resultSet.getRow();
			if(rowCount>0){

				ret_value=true;
				columnCount=resultSet.getMetaData().getColumnCount();

				//unclear what this little check does, for the case the result has a single column
				if(columnCount==1) {
					resultSet.beforeFirst();
					//while(resultSet.next()) titleOfColumns=resultSet.getString(1);
					return ret_value;
				}

				//back to first line
				//resultSet.first();
				resultSet.beforeFirst();
				String resultArray [][] = new String[rowCount+2][columnCount];

				for(int i=1;i<=columnCount;i++) {
					resultArray[0][i-1]=resultSet.getMetaData().getColumnName(i);
					result.getColumnNames().add(resultSet.getMetaData().getColumnName(i));
					resultArray[1][i-1]=resultSet.getMetaData().getColumnLabel(i);
					result.getColumnLabels().add(resultSet.getMetaData().getColumnLabel(i));
				}

				//TitleOfColumns=resultSet.getMetaData().getColumnName(1);

				while(resultSet.next()){
					String [] values = new String[resultSet.getMetaData().getColumnCount()];

					for(int i=0;i<columnCount;i++){
						String value = resultSet.getString(i+1);
						resultArray[resultSet.getRow()+1][i]=value;
						values[i] = value;
					}

					/*
					 * VERY IMPORTANT: here is where cells are created!
					 * We need them to be in a List, so that they are ordered!
					 */
					result.getCells().add(new Cell(values));

					result.setResultArray(resultArray);

				}


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret_value;
	}

	/* NOT used. 
	 * Unfortunately, if you call this, this injects a dependency to the caller, and then the caller depends on SQLQuery.
	 * Even if you change the SqlQuery to Extraction Method, as the method's parameter, they are still in the same package with SqlQuery
	 * So, in any case you would need some massive refactoring, anyway.
	 * 
	public void executeSqlQuery(SqlQuery query) {
		String queryString = query.toString();
		if (queryString.isEmpty()) {
			System.out.println("Query expression is empty. Exiting.");
			System.exit(-100);
		}

		ResultSet res = null;
		try {
			Statement createStatement = connect.createStatement();
			res = createStatement.executeQuery(queryString);

			query.setResultSet(res);
			query.populateResultArray();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	 */
}
