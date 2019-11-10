package client.gui.controllers;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;

import client.gui.application.MainApp;
import client.gui.application.MainAppSpark;
import client.gui.utils.LauncherForViewControllerPairs;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mainengine.IMainEngine;
import mainengine.Server;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class MainController extends AbstractController implements Initializable{
	private Stage primaryStage;

	public MainController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

    @FXML
    private Pane leftPane;

    @FXML
    private JFXButton jdbcButton;

    @FXML
    private JFXButton sparkButton;

    @FXML
    void onActionRunServer() throws Exception {
    	MainApp m = new MainApp();
    	m.start(primaryStage);
    }

    @FXML
    void onActionRunSpark() {
    	MainAppSpark m = new MainAppSpark();
    	m.start(primaryStage);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		Image image = new Image("res\\database.png");
//		jdbcButton.setGraphic(new ImageView(image));
		
	}

}
