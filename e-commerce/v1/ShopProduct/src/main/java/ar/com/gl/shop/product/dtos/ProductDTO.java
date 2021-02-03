package ar.com.gl.shop.product.dtos;

import java.util.Date;

import ar.com.gl.shop.product.model.Product;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Product", description = "Object that represent a product")
public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Date createdAt;
	private Date modifiedAt;
	private StockDTO stock;
	private CategoryDTO category;

	public static ProductDTO createfromEntity(Product product) {
		return ProductDTO.builder().id(product.getId()).name(product.getName()).description(product.getDescription())
				.price(product.getPrice()).createdAt(product.getCreatedAt()).modifiedAt(product.getModifiedAt())
				.stock(StockDTO.createDTOfromEntity(product.getStock()))
				.category(CategoryDTO.createFromEntity(product.getCategory())).build();
	}

}
