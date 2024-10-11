package ru.specialist.coursesapp;
	
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import ru.specialist.coursesapp.dal.Repository;
import ru.specialist.coursesapp.model.Course;
import ru.specialist.coursesapp.view.CourseOverviewController;



public class Main extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	private Repository db;
	
	
	public ObservableList<Course> getCourseData() {
		try {
			return db.getCourses();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return FXCollections.observableArrayList();
		}
	}

	public Main()
	{
		try {
			db = new Repository();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Учебные курсы");
		
		initRootLayout();
	}
	
	public void initRootLayout()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CourseOverview.fxml"));
			rootLayout = (AnchorPane)loader.load();
			
			Scene scene = new Scene(rootLayout);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			CourseOverviewController controller = 
					loader.getController();
			
			controller.setMain(this);
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
