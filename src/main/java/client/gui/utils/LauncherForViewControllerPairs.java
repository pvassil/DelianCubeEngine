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

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.gui.application.AbstractApplication;
import client.gui.controllers.AbstractController;

public class LauncherForViewControllerPairs {
	
	//under the bin folder, you must have a folder views with the FXML
	public static final String _FXML_FOLDER = "../views/";

	/**
	 * Launches a pair of (i) view (i.e., fxml file) and (ii) controller (a concrete extension of class AbstractController).
	 * <p>
	 * The method
	 * (i) verifies some constraints for null or illegal input
	 * (ii) loads the fxml and binds its controller to it
	 * (iii) setsUp Stage and Screen appropriately
	 * (iv) setsUp all the info the new controller is required to have (@see {link GUI.controllers.AbstractController})
	 * <p>
	 * Assumption:
	 * The fxml does NOT contain any fx:controller property and we need to programmatically assign it here.
	 * 
	 * @param callerController   the controller (concrete instantiation of AbstractController) that fires the event
	 * @param callerStage  the Stage of the callerController 
	 * @param needsNewStage  true if we will open a new Stage, false otherwise
	 * @param launchedFXMLView  the name of the fxml file. At runtime it must be within the bin/views folder
	 * @param launchedController  the controller (concrete instantiation of AbstractController) that handles the new fxml
	 * @param rootNodeLayout  the type of the root layout container of the FXML. Child of Parent hopefully
	 * 
	 * @return 0 is all OK, negative value otherwise.
	 */
	public Integer launchViewControllerPairNoFXController(AbstractApplication mainApp, AbstractController callerController, Stage callerStage, Boolean needsNewStage, 
			String launchedFXMLView, AbstractController launchedController, Parent rootNodeLayout) {


		//either you reuse the old stage or make a new one. 
		//If you reuse the old one, callerStage cannot be null
		if((needsNewStage == false) && (callerStage == null)) {
			System.out.println("LauncherFor ViewControllerPairs: Cannot have both needsNewStage == false and a null callerStage");
			return -2;
		}

		// FXMLLOADER
		URL location = getClass().getResource(_FXML_FOLDER + launchedFXMLView);
		if (location == null) {
			System.out.println("Empty/invalid location for the fxml: " + launchedFXMLView);
			return -3;
		}
		FXMLLoader loader = new FXMLLoader(location);
		
		//in case the controller is not set within the fxml
		if (loader.getController() != null) {
			//TODO: not tested yet!
			String loadedLoaderClassName = loader.getController().getClass().getName();
			String localLoaderClassName = launchedController.getClass().getSimpleName();
			if(localLoaderClassName.compareTo(loadedLoaderClassName) != 0) {
				System.out.println("FXML controller and local controller of different class.");
				System.out.println("FXML controller:\t" + loadedLoaderClassName);
				System.out.println("Local controller:\t" + localLoaderClassName);
				return (-4);
			}
		}
		loader.setController(launchedController);
		try {
			rootNodeLayout = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	

		//Finally, setUp stage and scene
		Stage launchedStage = null;		
		if(needsNewStage) {
			launchedStage = new Stage();
		}
		else {
			launchedStage = callerStage;
		}
		
		Scene launchedScene = new Scene(rootNodeLayout);
		launchedStage.setScene(launchedScene);
		
		
		//Set up the pointers as required by AbstracController
		launchedController.setScene(launchedScene);
		launchedController.setStage(launchedStage);
		launchedController.setApplication(mainApp);
		launchedController.setCallerController(callerController);
		
		
		//Welcome to the show, Welcome to the show (Savatage thanks for Magellan!)
		launchedStage.show();

		return 0;
	}
	
	
}//end class LauncherForViewControllerPairs
