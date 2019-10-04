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

package client.gui.application;


import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import javafx.application.Application;
//import javafx.scene.image.Image;
import mainengine.IMainEngine;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.gui.controllers.MainAppController;
import client.gui.utils.LauncherForViewControllerPairs;

import mainengine.IMainEngine;

public class MainApp extends AbstractApplication {

	private static final String HOST = "localhost";
	private static final int PORT = 2020;
	private static Registry registry;
	
	private Stage primaryStage;
	private VBox rootLayout = null;

	private IMainEngine service=null;
	


	public MainApp() {
		;
	}

	@Override
	public void start(Stage primaryStage) {
	
		String diagnostics = "";
		
		// Search the registry in the specific Host, Port.
		try {
			registry = LocateRegistry.getRegistry(HOST, PORT);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		// LookUp for MainEngine on the registry
		
		try {
			service = (IMainEngine) registry.lookup(IMainEngine.class.getSimpleName());
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		if(service == null) {
			diagnostics = "Unable to lookup RMI server, exiting";
			System.out.println(diagnostics);
			System.exit(-100);
		}
		else {
			diagnostics = "Successfully found RMI server";
			System.out.println(diagnostics);
		}
		
		super.start(primaryStage);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Delian Cubes Client Application");

		// Set the application icon.
		//this.primaryStage.getIcons().add(new Image("file:resources/images/ZZZZ.png"));

		MainAppController controller = new MainAppController();
		int launchResult = -100;
		
		LauncherForViewControllerPairs launcher = new LauncherForViewControllerPairs();
		launchResult = launcher.launchViewControllerPairNoFXController(this, null, primaryStage, false, 
				"MainApp.fxml", controller, rootLayout);
		
		if(launchResult < 0) {
			System.out.println("Launch Result was (miserably): " + launchResult + "\n");
			System.exit(0);
		}

		this.setOriginalStage(primaryStage);
		this.setCurrentStage(primaryStage);
		this.setFirstCalledController(controller);
		this.setLastCalledController(controller);
		
		
		// Cube ADULT and queries
		/*service.initializeConnection("adult_no_dublic", "CinecubesUser",
				"Cinecubes", "adult", "adult");
		File f = new File("InputFiles/cubeQueries.ini");
		service.answerCubeQueriesFromFile(f);/**/
				
		// Cube LOAN and queries
		try {
			service.initializeConnection("pkdd99", "root",
					"password", "pkdd99", "loan");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("Completed connection initialization");
		

		
		//initRootLayout();

	}//end start


	public IMainEngine getServer() {
		return service;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}//end MainApp class


//public void initRootLayout() {
//// Load root LAYOUT from fxml file.
//FXMLLoader loader = new FXMLLoader();
//MainAppController controller = new MainAppController();
//
//
//loader.setController(controller);
//loader.setLocation(MainApp.class.getResource("../views/MainApp.fxml"));
//URL loc = loader.getLocation();
//if (loc == null) {
//	System.out.println("--------------EMPTY fxml-------------- \n");
//	System.exit(-1);
//}
//else System.out.println(loc.toString() + "\n ---------------------------- \n");
//
//
//try {
//	//rootLayout = (VBox) FXMLLoader.load(MainApp.class.getResource("views/MainApp.fxml"));
//	rootLayout = (VBox) loader.load();
//	//loader.load();
//} catch (IOException e) {
//	e.printStackTrace();
//}
//
//// Setup the SCENE containing the root layout.
//Scene scene = new Scene(rootLayout);
//primaryStage.setScene(scene);
//controller.setScene(scene);
//controller.setStage(primaryStage);
//controller.setApplication(this);
//controller.setCallerController(null);
//
////Welcome to the SHOW()
//primaryStage.show();
//
//}//end method