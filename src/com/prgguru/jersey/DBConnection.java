package com.prgguru.jersey;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import org.json.JSONObject;
import java.util.Iterator;

import org.codehaus.jettison.json.JSONObject;
 
public class DBConnection {
    /**
     * Method to create DB Connection
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(Constants.dbClass);
            con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
        } catch (Exception e) {
            throw e;
        } finally {
            return con;
        }
    }
    /**
     * Method to check whether uid is correct
     * 
     * @param uid
     * @return
     * @throws Exception
     */
    public static boolean checkLogin(String uid) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user WHERE user_id = '" + uid + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }
    /**
     * Method to insert uid in DB
     * 
     * @param uid
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUser(String uid) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into user(user_id) values('" + uid + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }
    
    /**
     * Method to insert URL and frequency in DB
     * 
     * @param 
     * @param jsonObj
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUrl(JSONObject jsonObj) throws SQLException, Exception {
    	boolean insertStatus = false;
        Connection dbConn = null;
    	try {
            dbConn = DBConnection.createConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	Iterator<?> keys = jsonObj.keys();
    	while( keys.hasNext() ) {
    	    String url = (String)keys.next();
    	    System.out.println(url);
    	    long freq = (long) jsonObj.get(url);
    	    System.out.println(freq);
    	    try {
    	    	Statement stmt = dbConn.createStatement();
    	    	String query = 
    	    			"INSERT into urltable(url,freq,date)"
    	    					+ " values('" + url + "','" + freq + "',NOW())"
    	    					+ " ON DUPLICATE KEY UPDATE freq=freq +" + freq;
    	    	//System.out.println(query);
    	    	int records = stmt.executeUpdate(query);
    	    	//System.out.println(records);
    	    	//When record is successfully inserted
    	    	if (records > 0) {
    	    		insertStatus = true;
    	    	}
    	    }catch (SQLException sqle) {
    	    	//sqle.printStackTrace();
    	    	throw sqle;
    	    }
    	}
    	if (dbConn != null) {
    		dbConn.close();
    	}
    	return insertStatus;
    }
}
