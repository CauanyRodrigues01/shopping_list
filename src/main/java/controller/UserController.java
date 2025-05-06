package controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import dao.UserDao;
import exceptions.ConnectionException;
import model.UserModel;
import utils.PasswordUtils;

public class UserController {
	
	private UserDao userDao;
	
	public UserController() {
		this.userDao = new UserDao();
	}
	
	public String registerUserController(String name, String nickName, String email, String password) {
		
			String passwordHash = new PasswordUtils(password).getHash();
			
			try {
				if (this.userDao.isEmailAlreadyUsed(email)) {
					return "O e-mail já está em uso.";
				}
		        if (this.userDao.isNickNameAlreadyUsed(nickName)) {
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
			return "Erro com o banco de dados, tente novamente!";
		
	}
	
	public boolean loginUserController(String email, String inputPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		try {
			UserModel user = this.userDao.searchByEmail(email);
			if (user != null) {
				boolean isPasswordCorrect =	new PasswordUtils("").verifyPassword(inputPassword, user.getPassword_hash());
				if (isPasswordCorrect) {
					return true;
				}
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
	
	public Integer getUserIdByEmailControler(String email) throws SQLException, ConnectionException {
		return this.userDao.getUserIdByEmail(email);
	}
}
