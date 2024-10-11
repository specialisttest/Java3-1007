package ru.specialist.coursesapp.model;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class Course {
	
	private final StringProperty title;
	private final IntegerProperty length;
	private final StringProperty description;
	
	public Course()
	{
		this(null, 0, null);
	}
	
	public Course(String title, int length, String description)
	{
		this.title = new SimpleStringProperty(title);
		this.length = new SimpleIntegerProperty(length);
		this.description = new SimpleStringProperty(description);
		
	}
	
	
	public StringProperty getTitleProperty() {
		return title;
	}
	public String getTitle()
	{
		return title.get();
	}
	public void setTitle(String title)
	{
		this.title.set(title);
	}
	
	public IntegerProperty getLengthProperty() {
		return length;
	}
	
	public int getLength()
	{
		return length.get();
	}
	
	public void setLength(int length)
	{
		this.length.set(length);
	}
	public StringProperty getDescriptionProperty() {
		return description;
	}
	public String getDescription()
	{
		return description.get();
	}
	public void setDescription(String description)
	{
		this.description.set(description);
	}
	
	
	
	
	

}
