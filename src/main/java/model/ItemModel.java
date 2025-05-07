package model;

import lombok.*;

@Data @NoArgsConstructor @EqualsAndHashCode
public class ItemModel {
	
	private Integer id;
	private String name;
	private Integer quantity;
	private Boolean isCompleted;
	private Integer user_id;
	
}
