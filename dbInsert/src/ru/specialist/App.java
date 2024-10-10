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
			Scanner sc = new Scanner(System.in);
			System.out.print("Название курса: ");
			String title = sc.nextLine().trim();
			System.out.print("Длительность курса: ");
			int length = sc.nextInt();
			if (sc.hasNextLine()) sc.nextLine();
			System.out.print("Описание  курса: ");
			String description = sc.nextLine().trim();
			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			//Connection.TRANSACTION_READ_UNCOMMITTED
			//Connection.TRANSACTION_READ_COMMITTED
			//Connection.TRANSACTION_REPEATABLE_READ
			//Connection.TRANSACTION_SERIALIZABLE
			
			
			String sql = "INSERT INTO Courses (title,length, description)"
					+ " VALUES (?, ?, ?)";
			
			PreparedStatement cmd = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			cmd.setString(1, title);
			cmd.setInt(2, length);
			cmd.setString(3, description);
			
			if (cmd.executeUpdate() == 1) {
				try(ResultSet ids = cmd.getGeneratedKeys()) {
					if (ids.next()) {
						int generated_id = ids.getInt(1);
						System.out.printf("Курс добавлен. Id: %d\n", generated_id);	
					}
				}
				
				conn.commit();
			} else
				conn.rollback();
			
			
			
		}

	}

}
