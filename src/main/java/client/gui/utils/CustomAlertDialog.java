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
package client.gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Implements a parameterized utility Dialog
 * <p>
 * Zillion thanks to Marco Jakob for:
 * {@link http://code.makery.ch/blog/javafx-dialogs-official/}
 * 
 * @author pvassil
 * 
 */
public class CustomAlertDialog {

/*
	Enum Constants for AlertType

	CONFIRMATION 
	The CONFIRMATION alert type configures the Alert dialog to appear in a way that suggests the content of the dialog is seeking confirmation from the user.
	 
	ERROR 
	The ERROR alert type configures the Alert dialog to appear in a way that suggests that something has gone wrong.
	 
	INFORMATION 
	The INFORMATION alert type configures the Alert dialog to appear in a way that suggests the content of the dialog is informing the user of a piece of information.
	 
	NONE 
	The NONE alert type has the effect of not setting any default properties in the Alert.
	 
	WARNING 
	The WARNING alert type configures the Alert dialog to appear in a way that suggests the content of the dialog is warning the user about some fact or action. 
*/
		
	private Alert alert = null;
	
	/**
	 * 
	 * @param title a String with the title of the messagebox
	 * @param headerText  better null -- a String with the header of the message box
	 * @param contentText A String with the content of the message box
	 * @param motherStage  the enclosing Stage of the messageBox, can be null
	 */
	public CustomAlertDialog(String title, String headerText, String contentText, Stage motherStage) {
		alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		
		//setUp its stage
		if (motherStage != null)
			alert.initOwner(motherStage);
	}
	
	public void show() {
		alert.showAndWait();
	}
	
}
