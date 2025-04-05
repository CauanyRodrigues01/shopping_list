package model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ItemModel {
	
	private Integer id;
	private String name;
	private Integer quantity;
	private Boolean isCompleted;
	private Integer user_id;
	
	// Mudar o status do desejo como concluído
	public void markAsCompleted() {
		this.isCompleted = true;
	}
	
}
