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


import javafx.scene.Scene;
import javafx.stage.Stage;
import mainengine.IMainEngine;
import client.gui.application.AbstractApplication;

/**
 * The abstract class for _any_ controller.
 * 
 * As typically happens with JavaFX controllers all extensions of this class should have:
 * (1) private @FXML fields corresponding to the fx:id labels of the fxml file's nodes
 * (2) a constructor to run BEFORE the loader understands @FXML declarations (constructor is _unaware_ of @FXML)
 * (3) void initialize() to run _AFTER_ the constructor and the interpretation of @FXML with any handling of the @FXML parts
 * (4) all the handleXXX() or handleXX(ActionEvent Event) methods that correspond to the fxml files' onAction="#handleXXX" declarations
 *     and handle the events coming from the fxml view
 * 
 * What we do extra here is to add _explicitly_
 * (1) Stage and Scene of the controller
 * (2) The controller who is firing the creation of the current controller
 *     e.g., MenuController handles the clicking of the event "New File" that launches a newFile.fxml and a NewFileController.java
 *     Then, the  NewFileController class will point back to the MenuController as it callerController
 * (3) In a similar vein, the application within which all happens. We explicitly introduce AbstractApplication for this purpose.
 * All fields are <i>protected</i> so that they can be used directly by concrete extensions of the class
 *     
 * @author pvassil
 * @see {@link GUI.application.AbstractApplication}
 * 
 */
public abstract class AbstractController {
	
	public AbstractController() {
		;
	}

	public AbstractController(AbstractApplication anApp, AbstractController aCallerController, Scene aScene, Stage aStage) {
		this.application = anApp;
		this.callerController = aCallerController;
		this.scene = aScene;
		this.stage = aStage;
	}

	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}


	public AbstractApplication getApplication() {
		return application;
	}

	public void setApplication(AbstractApplication application) {
		this.application = application;
	}

	public AbstractController getCallerController() {
		return callerController;
	}

	public void setCallerController(AbstractController callerController) {
		this.callerController = callerController;
	}


	protected AbstractApplication application = null;
	protected AbstractController callerController = null;
	protected Scene scene = null;
	protected Stage stage = null;	

	

} //end class Abstract Controller



//import javafx.event.ActionEvent;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//
//protected Node sourceNode = null;
//public AbstractController() {
//;
//}
//
//public AbstractController(ActionEvent event) {
//this.setSourceNode(event);
//this.setScene(event);
//this.setStage(event);        
//}	
//
//public Node setSourceNode(ActionEvent event) {
//if(event != null) {
//	this.sourceNode = (Node) event.getSource();
//	return this.sourceNode;
//}
//return null;
//}
//public Scene setScene(ActionEvent event) {
//if(this.sourceNode!= null) {
//    this.scene = this.sourceNode.getScene();
//    return this.scene;
//}
//return null;
//}
//public Stage setStage(ActionEvent event) {
//if(this.scene!= null) {
//	stage = (Stage) scene.getWindow();
//	return this.stage;
//}
//return null;
//}
//
//
//
//public Node getSourceNode() {
//return sourceNode;
//}
//
//public void setSourceNode(Node sourceNode) {
//this.sourceNode = sourceNode;
//}
//
//