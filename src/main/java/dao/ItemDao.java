package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.ConnectionException;
import model.ItemModel;

public class ItemDao {

	public void addItemDao(String name, Integer quantity, Integer user_id) throws SQLException, ConnectionException {

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

		} catch (SQLException e) {
			throw new SQLException("Erro ao inserir o item no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dado: ", e1);
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

		ItemModel item = null;
		ArrayList<ItemModel> itens = null;

		try {

			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (rs != null) {
				itens = new ArrayList<>();
				while (rs.next()) {
					item = new ItemModel();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setQuantity(rs.getInt("quantity"));
					item.setIsCompleted(rs.getBoolean("is_completed"));
					item.setUser_id(rs.getInt("user_id"));
					itens.add(item);
				}
			}

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

		return itens;

	}

	public boolean removeItemDao(Integer item_id, Integer user_id) throws SQLException, ConnectionException {

		if (itemBelongsToTheUserDao(item_id, user_id)) {
			String sql = "DELETE FROM item WHERE id = ?";

			Connection connection = null;
			PreparedStatement ps = null;

			try {
				connection = ConnectionDBSingleton.getConnection();
				ps = connection.prepareStatement(sql);

				ps.setInt(1, item_id);

				int rowsAffected = ps.executeUpdate();
				return rowsAffected > 0;

			} catch (SQLException e) {
				throw new SQLException("Erro ao remover o item do banco de dados: ", e);
			} catch (ConnectionException e1) {
				throw new ConnectionException("Erro ao conectar-se ao banco de dado: ", e1);
			} finally {
				if (ps != null)
					ps.close();
				if (connection != null)
					ConnectionDBSingleton.closeConnection();
			}

		}
		return false;

	}

	// TODO fazer uma exception para quando usuário nao possui aquele item
	public boolean itemBelongsToTheUserDao(Integer item_id, Integer user_id) throws SQLException, ConnectionException {

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
			if (rs.getInt(1) > 0) {
				return true;
			} else {
				return false;
				// lançar exceção
			}

		} catch (SQLException e) {
			throw new SQLException("Erro ao inserir o item no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dado: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}

	}

	// TODO fazer uma exception para quando o item nao existe
	public boolean isItemPresent(Integer item_id) throws SQLException, ConnectionException {

		String sql = "SELECT COUNT(*) FROM item WHERE id = ?";

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = ConnectionDBSingleton.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, item_id);
			rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0) {
				return true;
			} else {
				return false;
				// lançar exceção
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao verificar o item no banco de dados: ", e);
		} catch (ConnectionException e1) {
			throw new ConnectionException("Erro ao conectar-se ao banco de dados: ", e1);
		} finally {
			if (ps != null)
				ps.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
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
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (connection != null)
				ConnectionDBSingleton.closeConnection();
		}
	}

}
