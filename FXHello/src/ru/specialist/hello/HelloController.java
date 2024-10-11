package ru.specialist.hello;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController implements Initializable {
	
	Hello model;

    @FXML
    private Label labelHello;

    @FXML
    private TextField txtUserName;
    
    @FXML
    private Label copyLabel;

    @FXML
    void onHelloPress(ActionEvent event) {
    	//model.setUserName(txtUserName.getText());
    	//labelHello.setText(model.getHello());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model = new Hello();
		
		txtUserName.textProperty().bindBidirectional(model.userNameProperty());
		labelHello.textProperty().bind(model.helloProperty());
		
		copyLabel.textProperty().bind(txtUserName.textProperty());
		
		
		
	}

}
