package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.*;

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
	@Getter
	private String password_hash;
	@Getter @Setter
	private List<ItemModel> itemList;

	public UserModel(Integer id, String name, String nickName, String email, String password) {
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.password_hash = password;
		this.itemList = new ArrayList<>();
	}
	
	public UserModel(String name, String nickName, String email, String password) {
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.password_hash = password;
		this.itemList = new ArrayList<>();
	}

	public void addItem(ItemModel item) {
		this.itemList.add(item);
	}
	
	public void removeItem(ItemModel item) {
		this.itemList.remove(item);
	}
	

	public void clearUserItemList() {
		this.itemList.clear();
	}
	
	// Retorna para visualização a lista de compra não modificável
	public List<ItemModel> getUserItemListView() {
	    return Collections.unmodifiableList(itemList);
	}

}
