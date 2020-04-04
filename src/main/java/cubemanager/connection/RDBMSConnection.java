package cubemanager.connection;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import cubemanager.starschema.ErrorClass;
import cubemanager.starschema.Table;
import result.Cell;
import result.Result;

public class RDBMSConnection extends Connection {

	private String inputFolder;
	private String cubeName;
	private String DBName;
	private String ConnectionString;
	private String DBMS;
	private java.sql.Connection connect;
	private String Username;
	private String Password;
	
	public RDBMSConnection(String jdbcURL, String driverURL) {
		ConnectionString = jdbcURL;
		DBMS = driverURL;
		Tbl = new ArrayList<Table>();
	}
	
	@Override
	public void generateTableList() {
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
	
	@Override
	public void registerCubeBase(HashMap<String, String> userInputList) {
		DBName = userInputList.get("schemaName");
		Username = userInputList.get("username");
		Password = userInputList.get("password");
		inputFolder = userInputList.get("inputFolder");
		cubeName = userInputList.get("cubeName");
		registerDatabase();
		generateTableList();
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
	
	public void setConnection(java.sql.Connection connect) {
		this.connect = connect;
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
			System.out.println("\nrunning - Running query. Please wait...");
			Instant startTime = Instant.now();
			Statement createdStatement = connect.createStatement();
			res = createdStatement.executeQuery(query);
			Instant finalTime = Instant.now();
			queryTimeString = Duration.between(startTime, finalTime).toMillis();
			System.out.println("\nok - Query executed! :)");
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
		System.out.println("\n\nrunning - Populating results. Please wait...");
		if (populateResult(resultSet, result) == false) {
			System.out.println("Exiting due to failure to populate the result");
			System.exit(-100);
		}
		else
			System.out.println("\n"+ "ok - Result produced! :)"+"\n");

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
						String value;
						if(StringUtils.isNumeric(resultSet.getString(i+1).replace(".", ""))) {
							if (!StringUtils.isNumeric(resultSet.getString(i+1))) {

								DecimalFormat decim = new DecimalFormat("0.0000");
								value = decim.format(Float.parseFloat(resultSet.getString(i+1)))+"";
							} else {
								value = resultSet.getString(i+1);
							}
						} else {
							value = resultSet.getString(i+1);
						}
						resultArray[resultSet.getRow()+1][i]=value.toString().trim().replaceAll("\n ", "");
						values[i] = value.toString().trim().replaceAll("\n ", "");
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
}
