package ru.specialist.fxstartfxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainWindowController implements Initializable {
	PressedModel model;
	
    @FXML
    private Button myButton;

    @FXML
    void onMyButtonPressed(ActionEvent event) {
    	myButton.setText("Pressed: " + (++model.counter));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		model = new PressedModel();
		
	}

}
