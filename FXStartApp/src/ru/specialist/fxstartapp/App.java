package ru.specialist.fxstartapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



// --module-path C:\javafx-sdk-23\lib --add-modules javafx.controls,javafx.fxml
public class App extends Application {

	int counter = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		AnchorPane root = new AnchorPane();
		Button btn = new Button();
		btn.setText("Press me");
		root.getChildren().add(btn);
		
		btn.setOnAction( e ->{
			btn.setText("Pressed: " + (++counter));
		});
		
		Scene scene = new Scene(root, 400, 300);
		window.setScene(scene);
		window.show();
		
	}

}
