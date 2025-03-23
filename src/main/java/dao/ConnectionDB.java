package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	public Connection getConnection() {
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost/WishList";
		String user = "root";
		String password = "cacau21";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão com banco de dados realizado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar o banco de dados: " + e.getMessage());
		}
		
		return conn;
	
	}

}
