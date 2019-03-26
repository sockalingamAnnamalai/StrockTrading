package com.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.DBConn;

import java.sql.ResultSet;

public class Datainsert {
	
	public static String checkUserExists(String name, String password) {
		
		Connection con = null;
		try {		
			con  = DBConn.getConnection();			

			// Get a prepared SQL statement
			String sql = "SELECT * FROM register WHERE name=? and password=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getString("name");
			}

		} catch (Exception e) {
			System.err.println("Login Exception: " + e.getMessage());
		} //finally {
		//	DBcon.close(con);
		//}
		return null;
	}
	
	public static boolean insertUser(String firstname, String lastname, String address, String phonenumber, String email, String username, String password) {
		Connection con = null;
		try {
			con  = DBConn.getConnection();	

			// Get a prepared SQL statement
			String sql = "INSERT INTO myusers (firstname,lastname,address,phonenumber,email,username,password) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, firstname);
			st.setString(2, lastname);
			st.setString(3, address);
			st.setString(4, phonenumber);
			st.setString(5, email);
			st.setString(6, username);
			st.setString(7, password);
			
			//execute insert SQL Statement
			st.executeUpdate();
			return true;

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} //finally {
			//DBcon.close(con);
		//}
		return false;
	}
	public static boolean updateUser(String firstname, String username) {
		Connection con = null;
		try {
			con  = DBConn.getConnection();	

			// Get a prepared SQL statement
			String sql = "UPDATE myusers SET firstname = ? WHERE username = ?"; 
					
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, firstname);
			//st.setString(2, emailAddress);
			//st.setString(3, password);
			st.setString(2, username);
			
			//execute insert SQL Statement
			st.executeUpdate();
			return true;

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} //finally {
			//DBcon.close(con);
		//}
		return false;
	}
	public static String readUserinfo(String username)  {
		Connection con = null;
		try {
			con  = DBConn.getConnection();	
			// Get a prepared SQL statement
			String sql = "SELECT email from myusers WHERE username = ?"; 		
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			
			
			//execute insert SQL Statement
			st.executeQuery();
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getString("email");
			}

		} catch (Exception e) {
			System.err.println("Login Exception: " + e.getMessage());
		} //finally {
		//	DBcon.close(con);
		//}
		return null;

	}
	public static boolean insertManager(String firstname, String lastname, String address, String phonenumber, String email, String username,String approved, String password) {
		Connection con = null;
		try {
			con  = DBConn.getConnection();	

			// Get a prepared SQL statement
			String sql = "INSERT INTO mymanagers (approved,firstname,lastname,address,phonenumber,email,username,password) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, approved);
			st.setString(2, firstname);
			st.setString(3, lastname);
			st.setString(4, address);
			st.setString(5, phonenumber);
			st.setString(6, email);
			st.setString(7, username);
			
			st.setString(8, password);
			
			//execute insert SQL Statement
			st.executeUpdate();
			return true;

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} //finally {
			//DBcon.close(con);
		//}
		return false;
	}
	public static String viewrequest() {
		Connection con = null;
		try {
			con  = DBConn.getConnection();	

			// Get a prepared SQL statement
			String sql = "select username from mymanagers where approved = 'NO'";
			PreparedStatement st = con.prepareStatement(sql);
			
			
			//execute insert SQL Statement
			st.execute();
			ResultSet rs = st.executeQuery();
			//return rs.getString("username");
			rs.next();
			 String username = rs.getString("username");
			 return username;

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} //finally {
			//DBcon.close(con);
		//}
		return null;
	}
}
	

