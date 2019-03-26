/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author harshinijayakumar
 */
public class DBConn {

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * @return the port
     */
    public static String getPort() {
        return port;
    }

    /**
     * @param aPort the port to set
     */
    public static void setPort(String aPort) {
        port = aPort;
    }

    /**
     * @return the db
     */
    public static String getDb() {
        return db;
    }

    /**
     * @param aDb the db to set
     */
    public static void setDb(String aDb) {
        db = aDb;
    }

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * @param aUrl the url to set
     */
    public static void setUrl(String aUrl) {
        url = aUrl;
    }
    private static Connection conn=null;
    private DBConn(){
        
    }
   private static String user = "root";
    private static String password = "2509";
    private static String port = "3306";
    private static String db = "termtest";
    private static String url="jdbc:mysql://localhost:3306/termtest";
    
    
//    private static String user = System.getenv("ICSI518_USER");
//    private static String password = System.getenv("ICSI518_PASSWORD");
//    private static String port = System.getenv("ICSI518_PORT");
//    private static String db = System.getenv("ICSI518_DB");
//    private static String url="jdbc:mysql://"+System.getenv("ICSI518_SERVER")+":"+port+"/"+db;
    
    public static Connection getConnection() {
    
        try {   
     
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }

    public static void closeConnection(){
        try{
            conn.close();
            conn=null;
            
        }catch (SQLException e){
            e.printStackTrace();
          
}
    }

}