package net.codejava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class Bahan {

    @WebMethod
    public String addBahan(String nama_bahan, int jumlah) {
    	DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
	    	Statement stmt = conn.createStatement();
	 
	    	ResultSet rs = stmt.executeQuery("SELECT bahan,jumlah FROM list_bahan WHERE bahan = '" + nama_bahan + "'");
	    	Boolean bahanUdahAda = false;
	    	while ( rs.next() ) {
	    		bahanUdahAda = true;
	    		int jumlahNow = rs.getInt("jumlah");
	    		int jumlahNew = jumlahNow + jumlah;
	    		
	    		LocalDateTime expDate = LocalDateTime.now();   
	    		expDate = expDate.plusDays(365);
	    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    		String strDate = dtf.format(expDate);
	    		
	    		String query = "UPDATE list_bahan SET jumlah = " + jumlahNew + ", tanggal_kadaluwarsa = '" + strDate + "' WHERE bahan = '" + nama_bahan + "'";
				dbConnector.executeUpdate(conn, query);
				return "bahan successfully updated" + " => "  + nama_bahan + ", jumlahNew = " + jumlahNew + ", expDateNew = " + strDate;
	    	}
	    	stmt.close();
	    	
	    	if (!bahanUdahAda) {
	    		LocalDateTime expDate = LocalDateTime.now();   
	    		expDate = expDate.plusDays(365);
	    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    		String strDate = dtf.format(expDate);
	    		
	    		String query = "INSERT INTO list_bahan (bahan, jumlah, tanggal_kadaluwarsa) VALUES ('" + nama_bahan + "', " + jumlah + ", '" + strDate + "')";
	    		dbConnector.executeUpdate(conn, query);
	    		
	    		return "bahan successfully added" + " => " + nama_bahan + ", jumlahNew = " + jumlah + ", expdate = " + strDate ;
	    	}
	    		
	    	return "error";
		} catch (SQLException e) {
			return e.getMessage();
		}
    	

    }
}
