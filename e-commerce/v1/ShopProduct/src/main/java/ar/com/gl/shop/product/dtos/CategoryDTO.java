package ar.com.gl.shop.product.dtos;

import ar.com.gl.shop.product.model.Category;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Category", description = "Object that represent a category")
public class CategoryDTO {
	private Long id;
	private String name;
	private String description;

	public static CategoryDTO createFromEntity(Category category) {
		return CategoryDTO.builder().id(category.getId()).name(category.getName())
				.description(category.getDescription()).build();
	}

}
