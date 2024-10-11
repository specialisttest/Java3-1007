package ru.specialist;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import ru.specialist.model.Person;
import ru.specialist.view.PersonOverviewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.collections.*;

import java.time.LocalDate;


public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    
    @Override
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();		
    	
	}
    

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }    
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public MainApp() {
		personData.add(new Person("Сергей", "Иванов", LocalDate.of(1987, 10, 29)));
		personData.add(new Person("Наталия", "Петрова", LocalDate.of(1982, 4, 23)));
		personData.add(new Person("Костя", "Сидоров", LocalDate.of(2006, 4, 23)));
		personData.add(new Person("Саша", "Васечник", LocalDate.of(2010, 11, 22)));
	}


	public ObservableList<Person> getPersonData() {
		return personData;
	}
	
	
}
