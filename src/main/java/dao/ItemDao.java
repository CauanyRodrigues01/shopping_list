package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import exceptions.ConnectionException;
import model.ItemModel;

public class ItemDao {

	public boolean addItemDao(String name, Integer quantity, Integer user_id) throws SQLException, ConnectionException {

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
			return true;

		} catch (SQLException e) {
			throw new SQLException("Erro ao inserir o item no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}

	}

	public ArrayList<ItemModel> listAllItemsOfUserDao(Integer userId) throws SQLException, ConnectionException {

		String sql = "SELECT * FROM item WHERE user_id = ?";

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<ItemModel> itens = new ArrayList<>();

		try {

			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					ItemModel item = new ItemModel();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setQuantity(rs.getInt("quantity"));
					item.setIsCompleted(rs.getBoolean("is_completed"));
					item.setUser_id(rs.getInt("user_id"));
					itens.add(item);
				}
			}

		} catch (SQLException e) {
			throw new SQLException("Erro ao listar os itens no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}

		return itens;

	}

	public boolean removeItemDao(Integer itemId, Integer userId) throws SQLException, ConnectionException {

		String sql = "DELETE FROM item WHERE id = ? AND user_id = ?";

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);

			ps.setInt(1, itemId);
			ps.setInt(2, userId);

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			throw new SQLException("Erro ao remover o item do banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}

	}

	public boolean isItemPresentInUserDao(Integer item_id, Integer user_id) throws SQLException, ConnectionException {

		String sql = "SELECT COUNT(*) FROM item WHERE id = ? AND user_id = ?";

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, item_id);
			ps.setInt(2, user_id);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		} catch (SQLException e) {
			throw new SQLException("Erro ao verificar se o item pertence ao usuário no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}

	}

	public boolean isNameItemAlreadyUsedDao(String name, Integer userId) throws SQLException, ConnectionException {
		String sql = "SELECT COUNT(*) FROM item WHERE name = ? AND user_id = ?";

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, userId);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1) > 0;
		} catch (SQLException e) {
			throw new SQLException("Erro ao verificar se o usuário já tem um item com o mesmo nome no banco de dados: ",
					e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}
	}
	
	public ItemModel getItemByIdDao(Integer itemId, Integer userId) throws SQLException, ConnectionException  {
		String sql = "SELECT * FROM item WHERE id = ? AND user_id = ?";

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, itemId);
			ps.setInt(2, userId);
			rs = ps.executeQuery();
			if(rs.next()) {
				ItemModel item = new ItemModel();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setQuantity(rs.getInt("quantity"));
				item.setIsCompleted(rs.getBoolean("is_completed"));
				item.setUser_id(rs.getInt("user_id"));
				return item;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao procurar item no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}
	}
	
	public boolean editItemDao(ItemModel item) throws SQLException, ConnectionException {
		
		String sql = "UPDATE item SET name = ?, quantity = ?, is_completed = ? WHERE id = ? AND user_id = ?";

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getQuantity());
			ps.setBoolean(3, item.getIsCompleted());
			ps.setInt(4, item.getId());
			ps.setInt(5, item.getUser_id());
			
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao editar item no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}
		
	}

}
