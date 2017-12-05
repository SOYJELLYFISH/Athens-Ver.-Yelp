package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessConfiguration {
	
	static final String DRIVE_NAME = "com.mysql.jdbc.Driver";
	
	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/dt";
	
	static final String DB_CONNECTION_USERNAME = "root";
	
	static final String DB_CONNECTION_PASSWORD = "Jccg2008";
	
}
