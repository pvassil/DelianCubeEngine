package cubemanager.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cubemanager.cubebase.BasicStoredCube;
import cubemanager.cubebase.Dimension;
import cubemanager.starschema.Database;

public class ConnectionFactory {
	
	public Connection createConnection(String typeOfConnection, HashMap<String, String> userInputList) {
		
		Connection connection = null;
		
		if (typeOfConnection.equals("RDBMS")) {
			// userInputList are the values pulled from GUI
			try {
				String line;
				Scanner scanner = new Scanner(new FileReader("InputFiles/" + userInputList.get("schemaName")
						+ "/dbc.ini"));
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					String results[] = line.split(";");
					System.out.println(results[1] + " : " + results[3]);
					return new RDBMSConnection(results[1], results[3]);
				}
				scanner.close();
			} catch (FileNotFoundException e1) {
				System.err.println("Unable to work correctly with dbc.ini for the setup of the Cubebase");
				e1.printStackTrace();
				return null;
			}
		}
		else if (typeOfConnection.equals("Spark")) {
			return new SparkConnection();
		}
		return null;
	}

}
