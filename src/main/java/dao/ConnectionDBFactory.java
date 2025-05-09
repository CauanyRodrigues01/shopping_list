package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBFactory {
	
	private Connection connection;
	
	public ConnectionDBFactory() {
		String url = "jdbc:mysql://localhost/shopping_list";
		String user = "root";
		String password = "cacau21";
		
		try {
			this.connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão com banco de dados realizado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar-se o banco de dados: " + e.getMessage());
		}
	}
	
	public Connection getConnectionDB() {
		return this.connection;
	}
	
	public void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
				System.out.println();
			} catch (SQLException e) {
				System.out.println("Erro ao encerrar a conexão com o Banco de Dados: " + e.getMessage());
			}
		}

	}

}
