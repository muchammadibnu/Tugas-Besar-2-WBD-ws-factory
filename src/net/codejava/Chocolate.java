package net.codejava;

import java.sql.Connection;
import java.sql.SQLException;
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
	public String addBahanAndHarga(String name, int harga) {
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
			
			String query = "INSERT INTO harga_bahan (name, harga) VALUES ('" + name + "', " + harga + ")";
			dbConnector.executeUpdate(conn, query);
			
			return "success";
			
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
}
