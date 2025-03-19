package model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Wish {
	
	private static int cont = 1;
	private int id;
	private String name;
	private String description;
	private String category;
	private String link;
	private Double price;
	private String image;
	private Boolean isCompleted;
	private WishList wishList;

	public Wish(String name, String description, String category, String link, Double price, String image, WishList wishList) {
		this.id = cont++;
		this.name = name;
		this.description = description;
		this.category = category;
		this.link = link;
		this.price = price;
		this.image = image;
		this.isCompleted = false;
		this.wishList = wishList;
	}
	
	// Mudar o status do desejo como concluído
	public void markAsCompleted() {
		this.isCompleted = true;
	}
	
}
