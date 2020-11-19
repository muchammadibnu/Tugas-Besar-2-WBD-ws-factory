package net.codejava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.RPC)
public class Chocolate {
	@WebMethod
	public String addNewChocolateRecipe(String chocolateName, String strListBahan, String strListJumlah, int hargaCoklat) {
		// untuk menambahkan resep baru
		// input nya: "nama coklat" , "bahan 1, bahan 2, .." , "jumlah bahan 1, jumlah bahan 2, ..." , harga
		
		List<String> listBahan = Arrays.asList(strListBahan.split(","));
		List<String> listJumlah = Arrays.asList(strListJumlah.split(","));
		
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
			
			String query = "INSERT INTO harga_coklat (name, harga) VALUES ('" + chocolateName + "'," + hargaCoklat + ")";
			dbConnector.executeUpdate(conn, query);
			
			for (int i = 0; i<listBahan.size(); ++i) {
				query = "INSERT INTO resep (chocolate_name, bahan, jumlah) VALUES ('" + chocolateName + "', '" + listBahan.get(i) + "', " + listJumlah.get(i) + ")";
				dbConnector.executeUpdate(conn, query);
			}
			return "success";
			
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
	@WebMethod
	public String getListRecipe() {
		// untuk mendapat semua list recipe coklat
		
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			String strHtml = "";
			
			conn = dbConnector.getConnection();
	    	Statement stmt = conn.createStatement();
	    	// get list name chocolate
	    	ResultSet rs = stmt.executeQuery("SELECT name FROM harga_coklat");
	    	while ( rs.next() ) {
	    		String chocolate_name = rs.getString("name");
	    		strHtml += "<h2>" + chocolate_name + "</h2>";
	    		strHtml += "<p> List Bahan </p>";
	    		strHtml += "<ul>";
	    		Statement stmt2 = conn.createStatement();
	    		ResultSet rs2 = stmt2.executeQuery("SELECT bahan, jumlah FROM resep where chocolate_name='" + chocolate_name + "'");
	    		while(rs2.next()) {
	    			String bahan = rs2.getString("bahan");
	    			int jumlah = rs2.getInt("jumlah");
	    			strHtml += "<li>" + bahan + " : " + jumlah + "</li>";
	    		}
	    		strHtml += "</ul>";
	    		stmt2.close();
	    	}
	    	stmt.close();
	    	return strHtml;
			
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
	
}
