package br.com.wishList.main;

import java.util.Objects;

public class Wish {
	
	private String name;
	private String description;
	private String category;
	private String link;
	private Double price;
	private String image;
	private User user;
	private String nameList;
	
	
	
	/**
	 * @param name
	 * @param description
	 * @param category
	 * @param link
	 * @param price
	 * @param image
	 * @param user
	 * @param nameList
	 */
	public Wish(String name, String description, String category, String link, Double price, String image, User user,
			String nameList) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.link = link;
		this.price = price;
		this.image = image;
		this.user = user;
		this.nameList = nameList;
	}
	
	/**
	 * Construtor de wish sem nome de lista, pois alguns wishs pode estar avulsos
	 * @param name
	 * @param description
	 * @param category
	 * @param link
	 * @param price
	 * @param image
	 * @param user
	 */
	public Wish(String name, String description, String category, String link, Double price, String image, User user) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.link = link;
		this.price = price;
		this.image = image;
		this.user = user;
		this.user.addWish(this);
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the nameList
	 */
	public String getNameList() {
		return nameList;
	}
	/**
	 * @param nameList the nameList to set
	 */
	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	/**
	 * Retorna uma representação textual do objeto
	 */
	@Override
	public String toString() {
		return "Wish [name=" + name + ", description=" + description + ", category=" + category + ", price=" + price
				+ ", user=" + user.getNickName() + ", nameList=" + nameList + "]";
	}

	/**
	 * Retorna um valor de código hash para o objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(description, name);
	}
	
	/**
	 * Compara este objeto com o objeto especificado para determinar se eles são "iguais"
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Wish))
			return false;
		Wish other = (Wish) obj;
		return Objects.equals(description, other.description) && Objects.equals(name, other.name);
	}
	
	
	

}
