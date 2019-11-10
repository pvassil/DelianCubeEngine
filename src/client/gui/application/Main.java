package client.gui.application;


import javafx.scene.image.Image;
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
import client.gui.controllers.MainController;
import client.gui.utils.LauncherForViewControllerPairs;

import mainengine.IMainEngine;

public class Main extends AbstractApplication {
	
	private Stage primaryStage;
	private VBox rootLayout = null;

	public Main() {
		;
	}

	@Override
	public void start(Stage primaryStage) {
		
		super.start(primaryStage);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Delian Cubes Client Application");
		primaryStage.getIcons().add(new Image("res\\icon.png"));

		MainController controller = new MainController(primaryStage);
		int launchResult = -100;
		
		LauncherForViewControllerPairs launcher = new LauncherForViewControllerPairs();
		launchResult = launcher.launchViewControllerPairNoFXController(this, null, primaryStage, false, 
				"Main.fxml", controller, rootLayout);
		
		if(launchResult < 0) {
			System.out.println("Launch Result was (miserably): " + launchResult + "\n");
			System.exit(0);
		}

		this.setOriginalStage(primaryStage);
		this.setCurrentStage(primaryStage);
		this.setFirstCalledController(controller);
		this.setLastCalledController(controller);

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public IMainEngine getServer() {
		// TODO Auto-generated method stub
		return null;
	}

}