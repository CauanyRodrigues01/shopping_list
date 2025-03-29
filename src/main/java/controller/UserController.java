package controller;

import java.sql.SQLException;

import dao.UserDao;
import exceptions.ConnectionException;
import utils.PasswordUtils;

public class UserController {
	
	private UserDao userDao;
	
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String registerUserController(String name, String nickName, String email, String password) {
		
			String passwordHash = new PasswordUtils(password).getHash();
			
			try {
				if (userDao.isEmailAlreadyUsed(email)) {
					return "O e-mail já está em uso.";
				}
		        if (userDao.isNickNameAlreadyUsed(nickName)) {
		        	return "O nickname já está em uso.";
		        }
				
				this.userDao.insertUser(name, nickName, email, passwordHash);
				return "Sucesso!";
				
			} catch (SQLException e) {
				System.out.println("Erro ao executar o SQL: " + e.getMessage());
			} catch (ConnectionException e) {
				System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
			} catch (IllegalArgumentException e) {
		        System.out.println(e.getMessage());
		    }
			return "Algo de errado ocorreu no sistema, tente novamente!";
			
	}
}
