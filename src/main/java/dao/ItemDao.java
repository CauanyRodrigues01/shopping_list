package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ConnectionException;

public class ItemDao {
	
	public void addItem(String name, Integer quantity, Integer user_id) throws SQLException, ConnectionException {
		
		String sql = "INSERT INTO item (name, quantity, user_id) VALUES (?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setInt(2, quantity);
			ps.setInt(3, user_id);
			ps.executeUpdate();
			System.out.println("Item criado com sucesso!");
			    
		} catch (SQLException e) {
		    System.out.println("Erro ao executar o SQL: " + e.getMessage()); 
		    System.out.println("Código de erro SQL: " + e.getErrorCode()); // Adicionar código de erro
		    throw new SQLException("Erro ao inserir o item no banco de dados.", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados.", e1);
		} finally {
	    	if (ps != null) ps.close();
	    	if (connection != null) ConnectionDBSingleton.closeConnection();
	    }
	}
	

	public boolean isNameAlreadyUsed(String name) throws SQLException, ConnectionException {
	    String sql = "SELECT COUNT(*) FROM item WHERE name = ?";
	    
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	connection = ConnectionDBSingleton.getConnection();
	        ps = connection.prepareStatement(sql);
	        ps.setString(1, name);
	        rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1) > 0;
	    } catch (SQLException e) {
	    	throw new SQLException("Erro ao verificar o nome do item: " + e.getMessage());
	    } finally {
	    	if (rs != null) rs.close();
	    	if (ps != null) ps.close();
	    	if (connection != null) ConnectionDBSingleton.closeConnection();
	    }
	}


}
