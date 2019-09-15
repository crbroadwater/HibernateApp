package com.chandler.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * A method that tests the SQL database connection
 * @author Chandler Broadwater
 *
 */
public class TestJdbc {

	public static void main(String[] args) {
		String Url = "jdbc:mysql://localhost:3306/hb_customer_database?useSSL=false";
		String user = "hbemployee";
		String pass = "hbemployee";
		
		try {
			System.out.println("Connecting to database: " + Url);
			Connection conn = DriverManager.getConnection(Url, user, pass);
			System.out.println("Connection Successful!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
