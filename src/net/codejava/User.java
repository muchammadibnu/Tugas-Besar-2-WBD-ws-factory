package net.codejava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class User{
	@WebMethod
	public String login(String username, String password){
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            byte[] messageDigest = md.digest(password.getBytes()); 
   
            BigInteger no = new BigInteger(1, messageDigest); 
   
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 

			conn = dbConnector.getConnection();
			
			String query = "SELECT role FROM user WHERE username = " + username + " AND password = " + hashtext;
			
			Statement stmt = conn.createStatement(); 
	    	ResultSet rs = stmt.executeQuery(query);
	    	String role = "None";
	    	if (rs.next()) {
	    		if (rs.getMetaData().getColumnCount() == 1){
	    			role = rs.getString("role");
	    		}
	    	}
	    	stmt.close();
			return role;
			
		} catch (SQLException e) {
			return e.getMessage();
		}
		catch (NoSuchAlgorithmException e) { 
            return e.getMessage(); 
        } 
	}

	@WebMethod
	public String register(String username, String password, String email){
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            byte[] messageDigest = md.digest(password.getBytes()); 
   
            BigInteger no = new BigInteger(1, messageDigest); 
   
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 

			conn = dbConnector.getConnection();
			
			String query = "INSERT INTO user(username, email, password, role) VALUES ('" + username + "','" + email + "','" + hashtext + "'," + "'user'" +")";
			dbConnector.executeUpdate(conn, query);
	  
			Statement stmt = conn.createStatement(); 
	    	ResultSet rs = stmt.executeQuery("SELECT role FROM user WHERE username = " + username + " AND password = " + hashtext);
	    	String role = "None";
	    	if (rs.next()) {
	    		if (rs.getMetaData().getColumnCount() == 1){
	    			role = rs.getString("role");
	    		}
	    	}
	    	stmt.close();
			return role;;
			
		} catch (SQLException e) {
			return e.getMessage();
		}
		catch (NoSuchAlgorithmException e) { 
            return e.getMessage(); 
        } 
	}
}