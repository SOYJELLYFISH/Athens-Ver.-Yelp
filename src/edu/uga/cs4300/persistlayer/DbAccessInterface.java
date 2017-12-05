package edu.uga.cs4300.persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessInterface {
	
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(DbAccessConfiguration.DRIVE_NAME);
			con = DriverManager.getConnection(DbAccessConfiguration.CONNECTION_URL, DbAccessConfiguration.DB_CONNECTION_USERNAME, DbAccessConfiguration.DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	} /// end of connect
	
	public static ResultSet retrieve (Connection con, String query) {
		ResultSet rset = null;
		try {
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}// end of retrieve
	
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of closeConnection

    public static int create (Connection con, String query){
    	int r = 1;
    	try {
			Statement s = con.createStatement();
			r = s.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
    	return r;
    }
	
	public static int update (Connection con, String query){
		int r = 1;
		try {
			Statement s = con.createStatement();
			r = s.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}	
    	return r;
	}
	
	public static int delete (Connection con, String query){
		int r = 1;
		try {
			Statement s = con.createStatement();
			r = s.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}	
    	
    	return r;
	}

}


	
	
