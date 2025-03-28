package controller;

import java.sql.SQLException;

import dao.UserDao;
import exceptions.ConnectionException;
import utils.PasswordUtils;

public class UserController {
	
	public void registerUserController(String name, String nickName, String email, String password) {
		
			String passwordHash = new PasswordUtils(password).getHash();
			UserDao userDao = new UserDao();
			
			try {
				if (userDao.isEmailAlreadyUsed(email)) {
					throw new IllegalArgumentException("O e-mail já está em uso.");
				}
//		        if (userDao.isNickNameAlreadyUsed(nickName)) {
//		            throw new IllegalArgumentException("O nickname já está em uso.");
//		        }
				
				userDao.insertUser(name, nickName, email, passwordHash);
				
			} catch (SQLException e) {
				System.out.println("Erro ao executar o SQL: " + e.getMessage());
			} catch (ConnectionException e) {
				System.out.println("Erro ao conectar-se ao banco de dados: " + e.getMessage());
			} catch (IllegalArgumentException e) {
		        System.out.println(e.getMessage());
		    }
	}
}
