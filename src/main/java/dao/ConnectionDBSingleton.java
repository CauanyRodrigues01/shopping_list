package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.ConnectionException;

public class ConnectionDBSingleton {

	private static Connection connection;
	
	private static Connection newConnection() throws ConnectionException {
		
		Connection con = null;
		String url = "jdbc:mysql://localhost/WishList";
		String user = "root";
		String password = "cacau21";
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: " + e.getMessage(), e);
		}
		
		return con;
	}
	
	public static Connection getConnection() throws ConnectionException {
		if (connection == null) {
			connection = newConnection();
		}
		return connection;
	}
	
	public static void closeConnection() throws ConnectionException {
		try {
			connection.close();
		} catch(SQLException e) {
			throw new ConnectionException("Erro ao fechar a conexão com o banco de dados.", e);
		}
		connection = null;
	}
}