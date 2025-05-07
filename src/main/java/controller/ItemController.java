package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import dao.ItemDao;
import exceptions.ConnectionException;
import exceptions.DuplicateItemNameException;
import exceptions.ItemNotFoundException;
import model.ItemModel;

public class ItemController {

	private ItemDao itemDao;

	public ItemController() {
		this.itemDao = new ItemDao();
	}

	public boolean addItemController(String name, Integer quantity, Integer userId) {
	    try {
	        if (this.itemDao.isNameItemAlreadyUsedDao(name, userId)) {
	            throw new DuplicateItemNameException("Você já possui um item com o nome \"" + name + "\".");
	        }
	        return this.itemDao.addItemDao(name, quantity, userId);

	    } catch (DuplicateItemNameException e) {
	        System.out.println("Erro de validação: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Erro ao executar o SQL: " + e.getMessage());
	    } catch (ConnectionException e) {
	        System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Erro de argumento inválido: " + e.getMessage());
	    }

	    return false;
	}
	public boolean removeItemController(Integer itemId, Integer userId) {

		try {

			if (!this.itemDao.isItemPresentInUserDao(itemId, userId)) {
				throw new ItemNotFoundException("Item com ID " + itemId + " não encontrado.");
			}
			
			return this.itemDao.removeItemDao(itemId, userId);
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o SQL: " + e.getMessage());
		} catch (ConnectionException e) {
			System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
		}
		return false;

	}

	public ArrayList<ItemModel> listAllItemsOfUserController(Integer userId) {

		try {
			return this.itemDao.listAllItemsOfUserDao(userId);
		} catch (SQLException e) {
			System.out.println("Erro ao executar o SQL: " + e.getMessage());
		} catch (ConnectionException e) {
			System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
		}

		return null;

	}
	
	public ItemModel getItemByIdController(Integer itemId, Integer userId) {
		try {
			return this.itemDao.getItemByIdDao(itemId, userId);
		} catch (SQLException e) {
			System.out.println("Erro ao executar o SQL: " + e.getMessage());
		} catch (ConnectionException e) {
			System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
		}
		return null;
	}
	
	public boolean editItemController(ItemModel item) {
		
		try {
			return this.itemDao.editItemDao(item);
		} catch (SQLException e) {
			System.out.println("Erro ao executar o SQL: " + e.getMessage());
		} catch (ConnectionException e) {
			System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
		}
		return false;
		
	}

}
