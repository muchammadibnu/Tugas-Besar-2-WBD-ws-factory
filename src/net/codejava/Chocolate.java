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
	public String addNewChocolateRecipe(String chocolateName, String strListBahan, String strListJumlah) {
		List<String> listBahan = Arrays.asList(strListBahan.split(","));
		List<String> listJumlah = Arrays.asList(strListJumlah.split(","));
		
		DbConnector dbConnector = new DbConnector();
		Connection conn = null;
		try {
			conn = dbConnector.getConnection();
			
			for (int i = 0; i<listBahan.size(); ++i) {
				String query = "INSERT INTO resep (chocolate_name, bahan, jumlah) VALUES ('" + chocolateName + "', '" + listBahan.get(i) + "', " + listJumlah.get(i) + ")";
				dbConnector.executeUpdate(conn, query);
			}
			return "success";
			
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
}
