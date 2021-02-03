package ar.com.gl.shop.product.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "CategoryDescription")
public class CategoryDescriptionDTO {
	@ApiModelProperty("New description")
	private String description;
}
