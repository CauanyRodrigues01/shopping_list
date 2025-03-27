package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.*;
import utils.PasswordUtils;

//TODO Lançar uma exceção personalizada (como UserCreationException)
//TODO Validar a complexidade da senha no momento da criação do usuário (como exigência de mínimo de caracteres, mistura de letras maiúsculas, minúsculas, números, etc.)
//TODO Adicionar logging adequado ao invés de apenas imprimir erros na saída padrão, utilizando bibliotecas como java.util.logging ou Log4j para ajudar na monitoração e diagnóstico do sistema.
//TODO Criar testes unitários para a classe User


@ToString(exclude = "password")
@EqualsAndHashCode
public class UserModel {
	
	@Getter @Setter
	private Integer id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String nickName;
	@Getter @Setter
	private String email;
	private String password;
	@Getter @Setter
	private List<WishListModel> wishLists;

	public UserModel(Integer id, String name, String nickName, String email, String inputPassword) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.password = new PasswordUtils(inputPassword).getHash();
		this.wishLists = new ArrayList<>();
	}

//	public boolean verifyPassword(String inputPassword) {
//	    try {
//	        return password.validatePassword(inputPassword);
//	    } catch (NoSuchAlgorithmException e) {
//			System.out.println("O algoritmo especificado não está disponível: " + e.getMessage());
//			e.printStackTrace();
//			return false;
//		} catch (InvalidKeySpecException e) {
//			System.out.println("Especificação da chave inválida: " + e.getMessage());
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	// Adiciona uma lista de desejos
	public void addWishList(WishListModel wishList) {
		this.wishLists.add(wishList);
	}
	
	// Remove uma lista de desejos
	public void removedWishList(WishListModel wishList) {
		this.wishLists.remove(wishList);
	}
	
	// Retorna para visualização as listas de desejos não modificável
	public List<WishListModel> getUserWishListsView() {
	    return Collections.unmodifiableList(wishLists);
	}

	// Retorna os quantidades de listas que o usuário possui
	public int getWishSize() {
		return this.wishLists.size();
	}

	// Esvazia as listas do usuário
	public void clearUserWishLists() {
		this.wishLists.clear();
	}

	// Verifica se tem alguma listas de desejo
	public boolean hasWishList(WishListModel wishList) {
		return wishLists.contains(wishList);
	}
}
