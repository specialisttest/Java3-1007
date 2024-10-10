package ru.specialist;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String CONNECTION_STRING = 
			"jdbc:mysql://localhost:3306/web?user=root&password=demo";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME); // load and auto-registered driver
		
		try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
			DatabaseMetaData meta = conn.getMetaData();
			
			System.out.println("TABLES:");
			try (ResultSet result = meta.getTables("web", "web", null, null)) {
				while(result.next() ) {
					String tableName = result.getString("TABLE_NAME");
					System.out.println(tableName);
				}
			}
			
			System.out.println("STORED PROCUDURES:");
			try(ResultSet result = meta.getProcedures("web", "web", null)) {
				while(result.next()) {
					String procName = result.getString("PROCEDURE_NAME");
					System.out.println(procName );
				}
			}			
			
			
		}
	}

}
