package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectionDBFactory;

public class TestConnectionFactory {
	
	public static void main(String[] args) {
		
		ConnectionDBFactory connecctionFactory1 = new ConnectionDBFactory();
		ConnectionDBFactory connecctionFactory2 = new ConnectionDBFactory();

		
		try {
			
			ResultSet rsUsers1 = connecctionFactory1.getConnectionDB().createStatement().executeQuery("SELECT * from user");
			System.out.println("---USUÁRIOS---");
			while (rsUsers1.next()) {
				System.out.println("Nome: " + rsUsers1.getString("nome"));
			}
			
			ResultSet rsWishes1 = connecctionFactory1.getConnectionDB().createStatement().executeQuery("SELECT * from wish");
			System.out.println("---DESEJOS---");
			while (rsWishes1.next()) {
				System.out.println("Nome: " + rsWishes1.getString("nome"));
			}	
			
			
			ResultSet rsUsers2 = connecctionFactory2.getConnectionDB().createStatement().executeQuery("SELECT * from user");
			System.out.println("---USUÁRIOS---");
			while (rsUsers2.next()) {
				System.out.println("Nome: " + rsUsers2.getString("nome"));
			}
			
			ResultSet rsWishes2 = connecctionFactory2.getConnectionDB().createStatement().executeQuery("SELECT * from wish");
			System.out.println("---DESEJOS---");
			while (rsWishes2.next()) {
				System.out.println("Nome: " + rsWishes2.getString("nome"));
			}	
			
		} catch (SQLException e) {
			System.out.println("Erro ao conectar o banco de dados: " + e.getMessage());
		} finally {
			connecctionFactory1.closeConnection();
			connecctionFactory2.closeConnection();
		}
	}
}
