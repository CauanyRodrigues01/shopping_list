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

	public Wish(String name, String description, String category, String link, Double price, String image, User user) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.link = link;
		this.price = price;
		this.image = image;
		this.user = user;
		try {
			this.user.addWish(this);
		} catch (WishExtantException e) {
			e.printStackTrace();
		} catch (WishInUseException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	@Override
	public String toString() {
		return "Wish [name=" + name + ", description=" + description + ", category=" + category + ", price=" + price
				+ ", user=" + user.getNickName() + ", nameList=" + nameList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, name);
	}

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
