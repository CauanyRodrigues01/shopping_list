package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.WishExtantException;
import exceptions.WishInUseException;
import exceptions.WishNotFoundException;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WishList {
	
	private static int cont = 1;
	private int id;
	private String nome;
	private Boolean isCompleted;
	private User user;
	private Set<Wish> wishes;
	
	public WishList(String nome, User user) {
		this.id = cont++;
		this.nome = nome;
		this.isCompleted = false;
		this.user = user;
		this.wishes = new HashSet<>();
	}
	
	public void addWish(Wish wish) throws WishExtantException, WishInUseException  {
		if (!wishes.contains(wish)) {
			this.wishes.add(wish);
		} else {
			throw new WishExtantException("O desejo já existe nesta lista.");
		}
	}
	
	public void removeWish(Wish wish) throws WishNotFoundException {
		if (wishes.contains(wish)) {
			this.wishes.remove(wish);
		} else {
			throw new WishNotFoundException("O desejo não existe nesta lista.");
		}
	}
	
	// Retorna os quantidades de desejos que a lista possui
	public int getWishListSize() {
		return this.wishes.size();
	}
	
	// Esvazia as listas de desejo
	public void clearWishList() {
		this.wishes.clear();
	}

	
	// Verifica se tem algum desejo
	public boolean hasWish(Wish wish) {
		return wishes.contains(wish);
	}
	
	// Mudar o status da lista de desejo como concluída
	public void markAsCompleted() {
		this.isCompleted = true;
	}

}
