package ar.com.gl.shop.product.dtos;

import ar.com.gl.shop.product.model.Stock;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Stock", description = "Object that represent a stock")
public class StockDTO {
	private Long id;
	private Integer quantity;
	private String locationCode;

	public static StockDTO createDTOfromEntity(Stock stock) {
		return StockDTO.builder().id(stock.getId()).quantity(stock.getQuantity()).locationCode(stock.getLocationCode())
				.build();
	}

}
