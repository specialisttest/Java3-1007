package ru.specialist;

import java.sql.*;
import java.util.Scanner;

public class App {
	
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String CONNECTION_STRING = 
			"jdbc:mysql://localhost:3306/web?user=root&password=demo";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME); // load and auto-registered driver
		
		try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
			
			//CallableStatement sp = conn.prepareCall("? = call countCourses( ? )");
			CallableStatement sp = conn.prepareCall("call countCourses( ? )");
			// IN, INOUT
			//sp.set..(1, value)
			sp.execute();
			// OUT, INOUT
			int countCourses = sp.getInt(1);
			System.out.printf("Всего курсов: %d\n", countCourses);
			
			System.out.print("Поиск курсов: ");
			String search = new Scanner(System.in).nextLine();
			//String search = "основы";
			//String search = "'OR 1 UNION (SELECT 'Hacker' AS title, 0 AS length) ORDER BY length -- ";
			
			/*
			// BAD: SQL injection + degrade perfomance
			String sql = "SELECT title,length FROM courses "
					+ "WHERE title LIKE '%"+search+"%' "
					+ "ORDER BY title";
			
			Statement cmd = conn.createStatement();
			ResultSet result = cmd.executeQuery(sql); // SELECT
			//cmd.executeUpdate(sql); // INSERT, UPDATE, DELETE
			*/
			
			String sql = "SELECT title,length FROM courses "
					+ "WHERE title LIKE ? "
					+ "ORDER BY title";
			
			PreparedStatement cmd = conn.prepareStatement(sql);
			cmd.setString(1, "%"+search+"%");
			ResultSet result = cmd.executeQuery();
			
			while(result.next()) {
				String title = result.getString("title");
				int length = result.getInt("length");
				System.out.printf("%-40s  %d\n", title, length);
			}
			
			result.close();
			
		} // conn.close();
		
		
		
		

	}

}
