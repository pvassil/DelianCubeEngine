package mainengine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ResultFileMetadata implements Serializable{

	/**
	 * UID by Eclipse 
	 */
	private static final long serialVersionUID = 3980458219744779036L;
	
	public ResultFileMetadata() {
		;
	}

	public String getResultFile() {
		return resultFile;
	}
	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	public String getResultInfoFile() {
		return resultInfoFile;
	}
	public void setResultInfoFile(String resultInfoFile) {
		this.resultInfoFile = resultInfoFile;
	}
	public ArrayList<String> getComponentResultFiles() {
		return componentResultFiles;
	}
	public void setComponentResultFiles(ArrayList<String> componentResultFiles) {
		this.componentResultFiles = componentResultFiles;
	}
	public ArrayList<String> getComponentResultInfoFiles() {
		return componentResultInfoFiles;
	}
	public void setComponentResultInfoFiles(ArrayList<String> componentResultInfoFiles) {
		this.componentResultInfoFiles = componentResultInfoFiles;
	}

	public String getLocalFolder() {
		return localFolder;
	}

	public void setLocalFolder(String localFolder) {
		this.localFolder = localFolder;
	}

	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.writeObject(resultFile);   
		oos.writeObject(resultInfoFile);
		oos.writeObject(componentResultFiles);
		oos.writeObject(componentResultInfoFiles);
		oos.writeObject(localFolder);
	}

	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		resultFile = (String) ois.readObject();
		resultInfoFile = (String) ois.readObject();
		componentResultFiles = (ArrayList<String>) ois.readObject();
		componentResultInfoFiles = (ArrayList<String>) ois.readObject();
		localFolder = (String) ois.readObject();
	}
	
	private String resultFile;
	private String resultInfoFile;
	private ArrayList<String> componentResultFiles;
	private ArrayList<String> componentResultInfoFiles;
	private String localFolder;
} //end class
