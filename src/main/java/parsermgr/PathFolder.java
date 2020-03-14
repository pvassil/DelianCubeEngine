package parsermgr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PathFolder {
	
	public PathFolder() {
		
	}
	
	public static String getPathOfProject() {
		File file = new File("project_path.ini"); 
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			try {
				st = br.readLine();
				if (st != null) {
					return st;
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Something went wrong while reading the spark.ini file.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can't find the spark.ini file. The file should be for example in 'InputFiles/pkdd99/spark.ini'.");
		}
		return null;
	}
}
