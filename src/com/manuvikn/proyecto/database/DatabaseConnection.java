package com.manuvikn.proyecto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/cursoJava";
	private static final String user = "manu";
	private static final String pass = "user1234";
	private static Connection conn;
	
	private DatabaseConnection() {
	}
	
	public static Connection getInstance() {
		
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, pass);				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
		
	}

}
