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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import client.gui.utils.CustomAlertDialog;
import client.gui.utils.ExitController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 * @author pvassil
 *
 */
public class TextEditorController extends AbstractController {

	@FXML
	private TextArea textArea;

	public TextEditorController() {
		super();
	}

	@FXML
	private void handleClose() {
		ExitController x = new client.gui.utils.ExitController(this.stage);
		x.exit();
	}//end handleClose


	@FXML
	public void handleOpenFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.setTitle("File Open");
		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			textArea.clear();
			String nextLine;
			try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
				while ((nextLine  = buffReader.readLine()) != null) {
					textArea.appendText(nextLine  + "\n");
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}	
		}
	}//end method 

	@FXML
	public void handleNewFile() {
		textArea.clear();
	}//end method

	@FXML
	public void handleSaveFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.setTitle("File Save As");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Text Files", "*.txt"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		File file = fileChooser.showSaveDialog(stage);

		if(saveToFile(file) < 0) {
			CustomAlertDialog a = new CustomAlertDialog("Failed Saving", null, "Saving the text failed. Plz., try again!", this.stage); 
			a.show();
		}
	}//end method

	public int saveToFile(File file) {
		if(file==null)
			return -1;

		String currentText;
		currentText = textArea.getText();

		BufferedWriter outputFile = null;
		try {
			outputFile = new BufferedWriter(new FileWriter(file));
			outputFile.write(currentText);
			outputFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return 0;
	}//end method

}//end class
