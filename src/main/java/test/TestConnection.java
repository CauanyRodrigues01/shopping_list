package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.ConnectionDB;

public class TestConnection {
	
	public static void main(String[] args) {
		
		ConnectionDB conn = new ConnectionDB();
		
		try {
			
			ResultSet rsUsers = conn.getConnectionDB().createStatement().executeQuery("SELECT * from user");
			System.out.println("---USUÁRIOS---");
			while (rsUsers.next()) {
				System.out.println("Nome: " + rsUsers.getString("nome"));
			}
			
			ResultSet rsWishes = conn.getConnectionDB().createStatement().executeQuery("SELECT * from wish");
			System.out.println("---DESEJOS---");
			while (rsWishes.next()) {
				System.out.println("Nome: " + rsWishes.getString("nome"));
			}	
		} catch (SQLException e) {
			System.out.println("Erro ao conectar o banco de dados: " + e.getMessage());
		} finally {
			conn.closeConnection();
		}
	}
}
