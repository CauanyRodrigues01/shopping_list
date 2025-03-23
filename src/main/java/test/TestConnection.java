package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectionDB;

public class TestConnection {
	
	public static void main(String[] args) {
		
		ConnectionDB conn = new ConnectionDB();
		
		try {
			
			ResultSet rsUser = conn.getConnection().createStatement().executeQuery("SELECT * from user");
			
			
			System.out.println("---USUÁRIOS---");
			while (rsUser.next()) {
				System.out.println("Nome: " + rsUser.getString("nome"));
			}
			
			
			ResultSet rsWishes = conn.getConnection().createStatement().executeQuery("SELECT * from wish");
			
			
			System.out.println("---WISHES---");
			while (rsWishes.next()) {
				System.out.println("Nome: " + rsWishes.getString("nome"));
			}			
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao conectar o banco de dados: " + e.getMessage());
		} 
		
		// TODO fechar conexao
		
	}

}
