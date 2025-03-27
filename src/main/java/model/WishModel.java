package model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class WishModel {
	
	private Integer id;
	private String name;
	private String description;
	private String category;
	private String link;
	private Double price;
	private String image;
	private Boolean isCompleted;
	private WishListModel wishList;
	
	// Mudar o status do desejo como concluído
	public void markAsCompleted() {
		this.isCompleted = true;
	}
	
}
