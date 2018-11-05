package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryForView {
	private String description;
	private Boolean checked;
}
