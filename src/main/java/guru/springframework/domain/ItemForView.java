package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ItemForView {
	private Long id;
	private String name;
	private String description;
	private Long category;
	private String photo;
}