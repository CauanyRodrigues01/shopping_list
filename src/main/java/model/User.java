package model;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import exceptions.WishExtantException;
import exceptions.WishInUseException;
import utils.PasswordUtils;

public class User {
	
	private String name;
	private String nickName;
	private String email;
	private PasswordUtils password;
	private List<Wish> wishList = new ArrayList<>();

	public User(String name, String nickName, String email, String inputPassword) {
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.password = new PasswordUtils(inputPassword);
		//TODO Lançar uma exceção personalizada (como UserCreationException)
		//TODO Validar a complexidade da senha no momento da criação do usuário (como exigência de mínimo de caracteres, mistura de letras maiúsculas, minúsculas, números, etc.)
		//TODO Adicionar logging adequado ao invés de apenas imprimir erros na saída padrão, utilizando bibliotecas como java.util.logging ou Log4j para ajudar na monitoração e diagnóstico do sistema.
		//TODO Criar testes unitários para a classe User
	}

	public void addWish(Wish wish) throws WishExtantException, WishInUseException  {
		if (!wishList.contains(wish)) {
			if (wish.getUser().equals(this)) {
				this.wishList.add(wish);
			} else {
				throw new WishInUseException("O desejo já está sendo usado por outro usuário.");
			}
		} else {
			throw new WishExtantException("O desejo já existe.");
		}
	}
	
	public void removeWish(Wish wish) throws WishInUseException {
	    if (wish.getUser().equals(this)) {
	    	this.wishList.remove(wish);
	    } else {
	    	throw new WishInUseException("O desejo já está sendo usado por outro usuário.");
	    }
	}

	public boolean verifyPassword(String inputPassword) {
	    try {
	        return password.validatePassword(inputPassword);
	    } catch (NoSuchAlgorithmException e) {
			System.out.println("O algoritmo especificado não está disponível: " + e.getMessage());
			e.printStackTrace();
			return false;
		} catch (InvalidKeySpecException e) {
			System.out.println("Especificação da chave inválida: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public List<Wish> getWishListView() {
	    return Collections.unmodifiableList(wishList);
	}

	public int getWishSize() {
		return this.wishList.size();
	}

	public void clearUserWish() {
		this.wishList.clear();
	}

	public boolean hasWish(Wish wish) {
		return wishList.contains(wish);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", nickName=" + nickName + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(nickName, other.nickName) && Objects.equals(password, other.password);
	}
}
