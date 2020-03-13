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

package client.gui.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import client.gui.utils.CustomAlertDialog;
import client.gui.utils.ExitController;
import client.gui.utils.FileInfoProvider;
import client.gui.utils.LauncherForViewControllerPairs;

public class DataWindowQueryWithModelsController extends AbstractController {
	@FXML
	private TableView<ObservableList<StringProperty>> dataTable;
	
	@FXML
	private TextArea textLogger;
	
	private File resultsFile = null;
	private File resultsInfoFile = null;
	
	//private String localFolder = "";
	private String localResultsFile = "";
	private String localInfoFile = "";
	private ArrayList<String> localModelFiles = new ArrayList<String>(); 
	private ArrayList<String> localInfoModelFiles = new ArrayList<String>(); 
	
	
	public DataWindowQueryWithModelsController() {
		super();
		localModelFiles = new ArrayList<String>();
		localInfoModelFiles = new ArrayList<String>();
	}

	public DataWindowQueryWithModelsController(String aResultsFile, String aResultInfoFile, ArrayList<String> theLocalModelFiles, ArrayList<String> theLocalInfoModelFiles) {
		super();
		dataTable = new TableView<ObservableList<StringProperty>>();
		textLogger = new TextArea();
		
		localResultsFile = aResultsFile;
		localInfoFile = aResultInfoFile;
		localModelFiles = theLocalModelFiles;
		localInfoModelFiles = theLocalInfoModelFiles;
		
		this.resultsFile = new File(localResultsFile);
		this.resultsInfoFile = new File(localInfoFile);
	}
	
	public void autoloadFile() {		
		if(resultsFile == null)
			return;
		try {
			System.out.println("OPEN RESULT FILE FOR DWQ&M : " + resultsFile.getAbsolutePath());		
			DataWindowQueryWithModelsController.readTSVandPopulateTableView(dataTable, resultsFile,true);
		} catch (IOException eio) {
			eio.printStackTrace();
		}

		if(resultsInfoFile == null)
			return;
		else {
			FileInfoProvider infoProvider  = new FileInfoProvider(resultsFile);
			String infoContents = infoProvider.getInfoContents(localInfoFile);
			logToTextLogger(infoContents);
		}
		
	}//end autoloadFile
	
	private void logToTextLogger(String textLogged) {
		String prevText = textLogger.getText();
		textLogger.setText(prevText + "\n" + textLogged + "\n");
	}
	
	@FXML
	private void handleClose() {
		ExitController x = new client.gui.utils.ExitController(this.stage);
    	x.exit();
	}
	
	@FXML
	private void handleDWLoad() {
		FileChooser fileChooser = new FileChooser();
		resultsFile = fileChooser.showOpenDialog(this.stage);
		if(resultsFile == null)
			return;
		try {
			DataWindowQueryWithModelsController.readTSVandPopulateTableView(dataTable, resultsFile,true);
		} catch (IOException eio) {
			eio.printStackTrace();
		}

		String textLogged = "File Opened:" + resultsFile;
		logToTextLogger(textLogged);
	}

	@FXML
	private void handleInfo() {
		String infoContents = "No Info Available for the current Data Windows";
		
		if(resultsInfoFile != null){
			FileInfoProvider infoProvider  = new FileInfoProvider(resultsFile);
			infoContents = infoProvider.getInfoContents(localInfoFile);
		}//end if

		CustomAlertDialog a = new CustomAlertDialog("Info", null, infoContents, this.stage); 
		a.show();
	}//end handleInfo
	
	@FXML
	private void handleAbout() {
		String contentText = "This window presents the result of a query. The Models menu allows you to see the models that have automatically been computed for it.";
		CustomAlertDialog a = new CustomAlertDialog("about", null, contentText, this.stage); 
		a.show();
		
		String textLogged = "About clicked.";
		logToTextLogger(textLogged);		
	}//end handleAbout

	@FXML
	private void handleModels() {
		if((localModelFiles == null) || (localModelFiles.size() == 0)) {
			CustomAlertDialog a = new CustomAlertDialog("No models available", null, "There are no models available for these data", this.stage); 
			a.show();
			return;
		}//end if
		else {
			System.out.println("Attempting to fire models");
			for(String nextModelFile: localModelFiles) {
				VBox dwLayout = null;
				int launchResult = -100;

				//TODO: Launch launcher in a new Stage at a new position + set its title !
				//Now, and not only for models, all windows open at the same place, one on top of the other, and without any title.
				LauncherForViewControllerPairs launcher = new LauncherForViewControllerPairs();
				//ATTN: if we update how we show models in the future, i.e., NOT with Data Windows
				//these two lines must be maintained with the new FXML file + the new controller
				DataWindowController controller = new DataWindowController(nextModelFile);
				launchResult = launcher.launchViewControllerPairNoFXController(this.getApplication(), this, this.getStage(), true, 
						"DataWindow.fxml", controller, dwLayout);
				controller.autoloadFile();
				
				//Just logging what happened.
				String prevText = textLogger.getText();
				if(launchResult <0 ) {
					textLogger.setText(prevText + "\n" + "Failed to open a Data Window for Model " + nextModelFile + "\n");
				}
				else {
					textLogger.setText(prevText + "\n" + "Opened a Data Window for Model " + nextModelFile + "\n");			
				}
				
			}//end for
		}//end else
	}//end handleModels
	
	/**
	 * Reads a tab delimited file and populates a TableView
	 * <p>
	 * From {@link https://community.oracle.com/message/10731570}
	 * Many thanks!!
	 * 
	 * @param tableView  the TableView to be populated
	 * @param fileName   a String with the tsv file name to be loaded
	 * @param hasHeader  whether the file has a header or not
	 * 
	 * @throws IOException
	 */
	public static void readTSVandPopulateTableView(TableView<ObservableList<StringProperty>> tableView, File fileName,  boolean hasHeader) throws IOException {
		BufferedReader bufferedReader = null;

		tableView.getItems().clear();
		tableView.getColumns().clear();
		tableView.setPlaceholder(new Label("Loading..."));


		if(fileName != null){
			bufferedReader = new BufferedReader(new FileReader(fileName));
		}

		// Header line
		if (hasHeader) {
			final String headerLine = bufferedReader.readLine();
			final String[] headerValues = headerLine.split("\t");
			for (int column = 0; column < headerValues.length; column++) {
				tableView.getColumns().add(
						createColumn(column, headerValues[column]));
			}
		}
		// Data:

		String dataLine;
		while ((dataLine = bufferedReader.readLine()) != null) {
			final String[] dataValues = dataLine.split("\t");
			// Add additional columns if necessary:
			for (int columnIndex = tableView.getColumns().size(); columnIndex < dataValues.length; columnIndex++) {
				tableView.getColumns().add(createColumn(columnIndex, ""));
			}
			// Add data to table:
			ObservableList<StringProperty> data = FXCollections.observableArrayList();
			for (String value : dataValues) {
				data.add(new SimpleStringProperty(value));
			}
			tableView.getItems().add(data);
		}
	}// end readCSVandPopulateTableView

	/** Auxiliary to readTSVandPopulateTableView()
	 * 
	 * @param columnIndex
	 * @param columnTitle
	 * @return
	 */
	private static TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex, String columnTitle) {
		TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
		String title;
		if (columnTitle == null || columnTitle.trim().length() == 0) {
			title = "Column " + (columnIndex + 1);
		} else {
			title = columnTitle;
		}
		column.setText(title);
		column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(
					CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
				ObservableList<StringProperty> values = cellDataFeatures.getValue();
				if (columnIndex >= values.size()) {
					return new SimpleStringProperty("");
				} else {
					return cellDataFeatures.getValue().get(columnIndex);
				}
			}
		});
		return column;
	}//end createColumn
	
}//end class DataWindowController
