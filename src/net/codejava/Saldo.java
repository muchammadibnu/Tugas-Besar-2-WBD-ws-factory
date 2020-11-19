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
public class Saldo {
	@WebMethod
	public String getSaldo() {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
	    	Statement stmt = conn.createStatement();
	 
	    	ResultSet rs = stmt.executeQuery("SELECT saldo FROM perubahan_saldo WHERE id=(SELECT MAX(id) FROM perubahan_saldo)");
	    	while ( rs.next() ) {
	    		int saldo = rs.getInt("saldo");
	    		stmt.close();	
	 
				return Integer.toString(saldo);
	    	}
	    	return "not found";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
	@WebMethod
	public String addSaldo(int penambahan) {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
	    	Statement stmt = conn.createStatement();
	 
	    	ResultSet rs = stmt.executeQuery("SELECT saldo FROM perubahan_saldo WHERE id=(SELECT MAX(id) FROM perubahan_saldo)");
	    	while ( rs.next() ) {
	    		int saldo = rs.getInt("saldo");
	    		stmt.close();	
	 
	    		int newSaldo = saldo + penambahan;
	    		String query = "INSERT INTO perubahan_saldo (saldo) VALUES (" + newSaldo + ")";
				dbConnector.executeUpdate(conn, query);
				return "success, saldo sekarang : " + Integer.toString(newSaldo);
	    	}
	    	return "saldo data not found";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
	@WebMethod
	public String decreaseSaldo(int pengurangan) {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
	    	Statement stmt = conn.createStatement();
	 
	    	ResultSet rs = stmt.executeQuery("SELECT saldo FROM perubahan_saldo WHERE id=(SELECT MAX(id) FROM perubahan_saldo)");
	    	while ( rs.next() ) {
	    		int saldo = rs.getInt("saldo");
	    		stmt.close();	
	    		
	    		if (pengurangan > saldo) {
	    			return "saldo kurang!";
	    		}
	    		
	    		int newSaldo = saldo - pengurangan;
	    		String query = "INSERT INTO perubahan_saldo (saldo) VALUES (" + newSaldo + ")";
				dbConnector.executeUpdate(conn, query);
				return "success, saldo sekarang : " + Integer.toString(newSaldo);
	    	}
	    	return "saldo data not found";
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
}
