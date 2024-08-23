package br.com.wishList.main;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {
	
	private String name;
	private String nickName;
	private String email;
	private PasswordUtils password;
	private List<Wish> wishList = new ArrayList<>();

	/**
	 * @param name
	 * @param nickName
	 * @param email
	 * @param password
	 * @param wishList
	 */
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

	/**
	 * Adicionar novos wishs ao usuário
	 */
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
	
	/**
	 * Remover wishs do usuário
	 * 
	 * @param wish O desejo que se deseja remover.
	 * @throws WishInUseException Se o desejo não pertencer ao usário, ele não pode ser removido.
	 */
	public void removeWish(Wish wish) throws WishInUseException {
	    if (wish.getUser().equals(this)) {
	    	this.wishList.remove(wish);
	    } else {
	    	throw new WishInUseException("O desejo já está sendo usado por outro usuário.");
	    }
	}
	/**
	 * Verifica se a senha fornecida corresponde ao hash da senha do usuário.
	 * 
	 * @param inputPassword A senha fornecida em texto claro.
	 * @return true se a senha gerar a mesma hash, false caso contráro.
	 */
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
	/**
	 * Obter uma lista de desejos em formato de leitura, sem expor a lista original.
	 * 
	 * @return Uma Lista que armazena uma cópia da lista de desejos do usuário.
	 */
	public List<Wish> getWishListView() {
	    return Collections.unmodifiableList(wishList);
	}

	/**
	 * Obter o número de desejos do usuário.
	 * 
	 * @return Um inteiro da quantidade de desejos que usuário tem.
	 */
	public int getWishSize() {
		return this.wishList.size();
	}
	
	/**
	 * Limpar os desejos do usuário
	 */
	public void clearUserWish() {
		this.wishList.clear();
	}
	
	/**
	 * Verificar se um desejo pertence ao usuário.
	 * 
	 * @param wish O desejo que se deseja encontrar
	 * @return true se o desejo for encontrado, false caso contrário.
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
