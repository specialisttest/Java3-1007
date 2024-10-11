package ru.specialist.coursesapp.view;

import ru.specialist.coursesapp.model.Course;
import ru.specialist.coursesapp.Main;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CourseOverviewController {
	
	@FXML
	private TableView<Course> courseTable;
	
	@FXML
	private TableColumn<Course, String> titleColumn;

	@FXML
	private TableColumn<Course, Number> lengthColumn;
	
	@FXML
    private void initialize() {
		titleColumn.setCellValueFactory(
				cellData-> cellData.getValue().getTitleProperty());
		lengthColumn.setCellValueFactory(
				cellData-> cellData.getValue().getLengthProperty()
				);
	}
	
	private Main main;
	public void setMain(Main wnd)
	{
		main = wnd;
		courseTable.setItems(main.getCourseData());;
	}
}
