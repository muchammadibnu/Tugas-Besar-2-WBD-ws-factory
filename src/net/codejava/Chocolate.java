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
	
	@WebMethod
	public String makeChocolate(String chocolate_name, int amount) {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			
			conn = dbConnector.getConnection();
	    	Statement stmt = conn.createStatement();
	    	
	    	ArrayList<String> listNamaBahan = new ArrayList<String>();
	    	ArrayList<Integer> listJumlahBahan = new ArrayList<Integer>();
	    	
	    	// get list bahan dan jumlah
	    	ResultSet rs = stmt.executeQuery("SELECT bahan, jumlah FROM resep WHERE chocolate_name = '" +  chocolate_name + "'");
	    	
	    	if (!rs.isBeforeFirst() ) {    
	    	    return "not found in list recipe, you must add the recipe first";
	    	} 

	    	while ( rs.next() ) {
	    		String namaBahan = rs.getString("bahan");
	    		int jumlahBahan = rs.getInt("jumlah");
	    		
	    		listNamaBahan.add(namaBahan);
	    		listJumlahBahan.add(jumlahBahan);
	    	}
	    	stmt.close();
	    	
	    	// cek apakah bahannya mencukupi
	    	for (int i = 0; i<listNamaBahan.size(); ++i) {
	    		String namaBahan = listNamaBahan.get(0);
	    		int jumlahBahan = listJumlahBahan.get(0);
	    
	    		Bahan objBahan = new Bahan();
	    		int jumlahDiGudang = objBahan.getJumlahBahan(namaBahan);
	    		
	    		if (jumlahDiGudang==-1) {
	    			return "error when finding jumlah bahan";
	    		}
	    		
	    		if (jumlahDiGudang < jumlahBahan*amount) {
	    			return "error: "  + namaBahan + " kurang"; 
	    		}
	    	}
	    	
	    	// kalau mencukupi, kurangi bahan2 nya
	    	for (int i = 0; i<listNamaBahan.size(); ++i) {
	    		String namaBahan = listNamaBahan.get(i);
	    		int jumlahBahan = listJumlahBahan.get(i);
	    		    		
	    		Bahan objBahan = new Bahan();
	    		objBahan.decreaseBahan(namaBahan, jumlahBahan*amount);
	    	}
	    	
	    	// tambahi coklat ke tabel chocolate_stock
	    	// cek dulu sebelumnya udah ada coklat itu ga
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery("SELECT amount FROM chocolate_stock WHERE name = '" +  chocolate_name + "'");
	    	if (rs.next()) {	// found, so do the update
	    		int jumlahNow = rs.getInt("amount") + amount;
	    		stmt.close();
	    		
	    		// update
	    		String query = "UPDATE chocolate_stock SET amount = " + jumlahNow +  " WHERE name = '" + chocolate_name + "'";
				dbConnector.executeUpdate(conn, query);
				
				return " stock updated => " + chocolate_name + " = " + jumlahNow;
	    	}
	    	else { // not found, then insert
	    		stmt.close();
	    		
	    		String query = "INSERT INTO chocolate_stock (name, amount) VALUES ('" + chocolate_name + "', " + amount + ")";
				dbConnector.executeUpdate(conn, query);
				
				return " chocolate created => " + chocolate_name + " = " + amount;
	    	}
			
		} catch (SQLException e) {
			return e.getMessage();
		}

	}
}
