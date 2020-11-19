package net.codejava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class RequestChocolate {
	
	@WebMethod
	public int reqAddChocolate(String name, int amount) {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
			
			String query = "INSERT INTO request_add_stock (chocolate_name, amount) VALUES ('" + name + "'," + amount + ")";
			dbConnector.executeUpdate(conn, query);
			
			
			Statement stmt = conn.createStatement(); 
	    	ResultSet rs = stmt.executeQuery("SELECT MAX(id) as id FROM request_add_stock");
	    	int lastId = -1;
	    	if (rs.next()) {
	    		lastId = rs.getInt("id");
	    	}
	    	stmt.close();
			return lastId;
			
		} catch (SQLException e) {
			return 0;
		}
	}
	
	@WebMethod
	public String checkStatus(int id) {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
			Statement stmt = conn.createStatement();
			 
	    	ResultSet rs = stmt.executeQuery("SELECT status FROM request_add_stock WHERE id = " + id);
	    	if (rs.next()) {
	    		return rs.getString("status");
	    	}
			return "id not found";
			
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
	@WebMethod
	public String approveReq(int id) {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT chocolate_name, amount FROM request_add_stock WHERE id = " + id);
	    	if (rs.next()) {
	    		String name = rs.getString("chocolate_name");
	    		int amount = rs.getInt("amount");
	    		stmt.close();
	    		stmt = conn.createStatement();
	    		rs = stmt.executeQuery("SELECT amount FROM chocolate_stock WHERE name = '" + name + "'");
	    		if (rs.next()) {
	    			int amountDiFactory = rs.getInt("amount");
	    			if (amount > amountDiFactory) {
	    				stmt.close();
	    				return "failed, stok kurang, harus bikin coklat dulu";
	    			}
	    			else {
	    				stmt.close();
	    				
	    				String query = "UPDATE chocolate_stock SET amount = " + (amountDiFactory - amount) + " WHERE name = '" + name + "'";
	    				dbConnector.executeUpdate(conn, query);
	    				
	    				query = "UPDATE request_add_stock SET status = 'DELIVERED' " + " WHERE id = " + id;
	    				dbConnector.executeUpdate(conn, query);
	    				
	    				return "request approved";
	    			}
	    		}
	    		else {
	    			return "failed, stok kurang, harus bikin coklat dulu";
	    		}
	    	}
	    	else {
	    		return "id not found";
	    	}
	    	
			
			
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
	
}
