package br.com.wishList.main;

import java.util.ArrayList;
import java.util.Objects;

public class User {
	
	private String name;
	private String nickName;
	private String email;
	private String password;
	private ArrayList<Wish> wishList = new ArrayList<Wish>();
	
	
	
	/**
	 * @param name
	 * @param nickName
	 * @param email
	 * @param password
	 * @param wishList
	 */
	public User(String name, String nickName, String email, String password, ArrayList<Wish> wishList) {
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		this.wishList = wishList;
	}
	
	/**
	 * Construtor de usuário que ainda não tem wishList
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
	public void addWish(Wish wish) {
		if (verifyWish(wish)) {
			this.wishList.add(wish);
		}
		
	}
	
	/**
	 * Verifica se o Wish pode ser adicionado à lista de desejos.
	 * 
	 * @param wish O Wish a ser verificado.
	 * @return true se o desejo puder ser adicionado, false caso contrário.
	 */
	public boolean verifyWish(Wish wish) {
	    // Verifica se o usuário do Wish é o mesmo usuário atual
	    if (!wish.getUser().equals(this)) {
	        return false;
	    }

	    // Verifica se a lista de desejos está vazia ou se o desejo não está na lista
	    return wishList.isEmpty() || !wishList.contains(wish);
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the wishList
	 */
	public ArrayList<Wish> getWishList() {
		return wishList;
	}
	/**
	 * @param wishList the wishList to set
	 */
	public void setWishList(Wish wishList) {
		this.wishList.add(wishList);
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
		return Objects.hash(email, name, nickName, password);
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
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(nickName, other.nickName) && Objects.equals(password, other.password);
	}
	
	

}
