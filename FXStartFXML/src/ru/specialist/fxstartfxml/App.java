package ru.specialist.fxstartfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));

		Scene scene = new Scene(root, 400, 300);
		window.setScene(scene);
		window.show();
		
	}

}
