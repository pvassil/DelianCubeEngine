package client.gui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class FileInfoProvider {

	public FileInfoProvider(File aFile) {
		file = aFile;
	}

	public FileInfoProvider(String aFileName) {
		file = new File(aFileName);
	}
	
	/**
	 * Returns a string with the absolute path of the folder containing the file
	 * @return	a string with the absolute path of the folder containing the file
	 */
	public String getParentFolderAbsolutePath() {
		if(file.exists()){
			return file.getParentFile().getAbsolutePath();
		}
		return null;
	}//end method
	
	/**
	 * Returns a string with the absolute path of the _info.txt containing the info file that accompanies the file
	 * @return  a string with the absolute path of the _info.txt containing the info file that accompanies the file
	 */
	public String getInfoFileAbsoluteLocation() {
		String infoLocation = this.getNameForInfoFile(file.getName()); 
		String fullInfoLocation = getParentFolderAbsolutePath() + File.separator + infoLocation; 

		return fullInfoLocation;
	}//end method
	
	/**
	 * Returns a string with the simple file name of the _info.txt containing the info file that accompanies the file
	 * @param 	a string with the filename of the result file, typically of the form xxx.tab
	 * @return  a string with the simple file name of the _info.txt containing the info file that accompanies the file
	 */
	public String getNameForInfoFile(String fileName) {
		String result = "";

		String [] queryNameParts = fileName.split(Pattern.quote("."));
		String queryName = queryNameParts[0];

		result = queryName;
		for(int i=1; i< queryNameParts.length-1; i++)
			result = result + "." + queryNameParts[i];
		result = result + "_info" + ".txt";
		
		return result;
	}//end method
	
	/**
	 * Returns a Boolean flag on whether the info file for the file actually exists
	 * @return a Boolean flag on whether the info file for the file actually exists
	 */
	public Boolean getInfoFileExistence() {
		Boolean exists = false;
		File infoFile = new File(getInfoFileAbsoluteLocation());
		if(infoFile.exists() && !infoFile.isDirectory())
			exists = true;
		return exists;
	}

	/**
	 * Returns a String containing all the contents of the input file.
	 * Typically used for _info.txt files
	 * 
	 * @param fileName the filename of the _info_ file which will be converted to a string 
	 * @return a String containing all the contents of the input file  
	 */
	public String getInfoContents(String fileName) {
		String contents = "";
		File f = new File(fileName);
		if(f.exists() && !f.isDirectory()) { 
			BufferedReader reader = null;
			try {
			    reader = new BufferedReader(new FileReader(f));

			    String line;
			    while ((line = reader.readLine()) != null) {
			        contents = contents + line + "\n";
			    }

			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    try {
			        reader.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}//end finally
		}//end master if
		return contents;
	}//end method

	private File file;
}
