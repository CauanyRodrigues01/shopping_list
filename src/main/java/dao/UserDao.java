package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exceptions.ConnectionException;
import model.UserModel;

public class UserDao {
	
	public void insertUser(String name, String nickName, String email, String password_hash) throws SQLException, ConnectionException {
		
		String sql = "INSERT INTO user (name, nick_name, email, password_hash) VALUES (?, ?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setString(2, nickName);
			ps.setString(3, email);
			ps.setString(4, password_hash);
			ps.executeUpdate();
			System.out.println("Usuário inserido com sucesso!");
			    
		} catch (SQLException e) {
		    System.out.println("Erro ao executar o SQL: " + e.getMessage()); 
		    System.out.println("Código de erro SQL: " + e.getErrorCode()); // Adicionar código de erro
		    throw new SQLException("Erro ao inserir o usuário no banco de dados.", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados.", e1);
		} finally {
	    	if (ps != null) ps.close();
	    	if (connection != null) ConnectionDBSingleton.closeConnection();
	    }
	}
	
	public UserModel searchByEmail(String email) throws SQLException, ConnectionException {
	    String sql = "SELECT * FROM user WHERE email = ?";
	    
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	connection = ConnectionDBSingleton.getConnection();
	        ps = connection.prepareStatement(sql);
	        ps.setString(1, email);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	return new UserModel(
	        			rs.getInt("id"),
	        			rs.getString("name"),
	        			rs.getString("nick_name"),
	        			rs.getString("email"),
	        			rs.getString("password_hash")
	        	);
	        }
	        
	    } catch (SQLException e) {
	    	throw new SQLException("Erro ao realizar login: " + e.getMessage());
	    } finally {
	    	if (rs != null) rs.close();
	    	if (ps != null) ps.close();
	    	if (connection != null) ConnectionDBSingleton.closeConnection();
	    }

        return null;
	}

	public boolean isEmailAlreadyUsed(String email) throws SQLException, ConnectionException {
	    String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
	    
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	connection = ConnectionDBSingleton.getConnection();
	        ps = connection.prepareStatement(sql);
	        ps.setString(1, email);
	        rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1) > 0;
	    } catch (SQLException e) {
	    	throw new SQLException("Erro ao verificar o email: " + e.getMessage());
	    } finally {
	    	if (rs != null) rs.close();
	    	if (ps != null) ps.close();
	    	if (connection != null) ConnectionDBSingleton.closeConnection();
	    }
	}

	public boolean isNickNameAlreadyUsed(String nickName) throws SQLException, ConnectionException {
	    String sql = "SELECT COUNT(*) FROM user WHERE nick_name = ?";
	    
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	connection = ConnectionDBSingleton.getConnection();
	        ps = connection.prepareStatement(sql);
	        ps.setString(1, nickName);
	        rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1) > 0;
	    } catch (SQLException e) {
	    	throw new SQLException("Erro ao verificar o NickName: " + e.getMessage());
	    } finally {
	    	if (rs != null) rs.close();
	    	if (ps != null) ps.close();
	    	if (connection != null) ConnectionDBSingleton.closeConnection();
	    }
	}
	
	public Integer getUserIdByEmail(String email) throws SQLException, ConnectionException {
	    String sql = "SELECT id FROM user WHERE email = ?";
	    
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	        connection = ConnectionDBSingleton.getConnection();
	        ps = connection.prepareStatement(sql);
	        ps.setString(1, email);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("id");
	        }
	        
	    } catch (SQLException e) {
	        throw new SQLException("Erro ao buscar ID do usuário por email: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (connection != null) ConnectionDBSingleton.closeConnection();
	    }

	    return null; // Caso o e-mail não seja encontrado
	}
}
