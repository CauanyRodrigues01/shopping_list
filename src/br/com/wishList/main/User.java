package br.com.wishList.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {
	
	private String name;
	private String nickName;
	private String email;
	private String password;
	private List<Wish> wishList = new ArrayList<>();
	
	/**
	 * @param name
	 * @param nickName
	 * @param email
	 * @param password
	 * @param wishList
	 */
	public User(String name, String nickName, String email, String password) {
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.password = password;
	}

	/**
	 * Adicionar novos wishs ao usuário
	 */
	public void addWish(Wish wish) throws WishExtantException, WishInUseException  {
		if (!wishList.contains(wish)) {
			if (wish.getUser().equals(this)) {
				this.wishList.add(wish);
			} else {
				throw new WishInUseException();
			}
		} else {
			throw new WishExtantException();
		}
	}
	
	/**
	 * Remover wishs do usuário
	 */
	public void removeWish(Wish wish) throws WishInUseException {
	    if (wish.getUser().equals(this)) {
	    	this.wishList.remove(wish);
	    } else {
	    	throw new WishInUseException();
	    }
	}
	
	/**
	 * Obter uma lista de desejos em formato de leitura, sem expor a lista original
	 */
	public List<Wish> getWishListView() {
	    return Collections.unmodifiableList(wishList);
	}

	/**
	 * Obter o número de itens na lista de desejos
	 */
	public int getWishListSize() {
		return this.wishList.size();
	}
	
	/**
	 * Limpar a lista de desejos
	 */
	public void clearWishList() {
		this.wishList.clear();
	}
	
	/**
	 * Verificar se um WISH está na lista de desejos
	 */
	public boolean hasWish(Wish wish) {
		return wishList.contains(wish);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna uma representação textual do objeto
	 */
	@Override
	public String toString() {
		return "User [name=" + name + ", nickName=" + nickName + ", email=" + email + "]";
	}

	/**
	 * Retorna um valor de código hash para o objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, name, password);
	}

	/**
	 * Compara este objeto com o objeto especificado para determinar se eles são "iguais"
	 */
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
