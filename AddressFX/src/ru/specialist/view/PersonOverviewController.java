package ru.specialist.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.specialist.MainApp;
import ru.specialist.model.Person;

public class PersonOverviewController {
	
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;	
	
    @FXML
	private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;	
	
	private MainApp mainApp;
    
	public PersonOverviewController() {
    }
	
	@FXML
	private void initialize()
	{
		firstNameColumn.setCellValueFactory( cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	}
	
	private void showPersonDetails(Person person) {
	    if (person != null) {
	        // Fill the labels with info from the person object.
	        firstNameLabel.setText(person.getFirstName());
	        lastNameLabel.setText(person.getLastName());
	        streetLabel.setText(person.getStreet());
	        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
	        cityLabel.setText(person.getCity());

	    } else {
	        // Person is null, remove all the text.
	        firstNameLabel.setText("");
	        lastNameLabel.setText("");
	        streetLabel.setText("");
	        postalCodeLabel.setText("");
	        cityLabel.setText("");
	        birthdayLabel.setText("");
	    }
	}
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		personTable.setItems(mainApp.getPersonData());
	}
	
	

}
