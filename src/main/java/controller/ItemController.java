package controller;

import java.sql.SQLException;
import dao.ItemDao;
import exceptions.ConnectionException;
import utils.PasswordUtils;

public class ItemController {
	
	
	private ItemDao itemDao;
	
	public ItemController() {
		this.itemDao = new ItemDao();
	}
	
	public boolean addItemController(String name, Integer quantity, Integer user_id) {
			
			try {
				if (!this.itemDao.isNameAlreadyUsed(name)) {
					this.itemDao.addItem(name, quantity, user_id);
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao executar o SQL: " + e.getMessage());
			} catch (ConnectionException e) {
				System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
			} catch (IllegalArgumentException e) {
		        System.out.println(e.getMessage());
		    }
			return false;
		
	}
	
}
