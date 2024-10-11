package ru.specialist.coursesapp.dal;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.specialist.coursesapp.model.*;


public class Repository implements Closeable{
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver"; 
	public static final String CONNECTION_STRING = 
			"jdbc:mysql://localhost:3306/web?user=root&password=demo&characterEncoding=utf8&characterSetResults=utf8";
	
	private Connection conn;
	
	public Repository() throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER_NAME);
		conn = DriverManager.getConnection(CONNECTION_STRING);
	}
	
	public ObservableList<Course> getCourses() throws SQLException
	{
		ObservableList<Course> courseData = 
		FXCollections.observableArrayList();
	
		Statement cmd = conn.createStatement();
		String sql = "SELECT title, length,description FROM courses ORDER BY title";
		ResultSet result = cmd.executeQuery(sql);
		while (result.next())
		{
			String title = result.getString("title");
			int length = result.getInt("length");
			String description = result.getString("description");
			courseData.add(new Course(title, length, description));
		}
		result.close();
		return courseData;
		
	}
	
	// CRUD

	@Override
	public void close() throws IOException {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
