package ar.com.gl.shop.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.gl.shop.product.dtos.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STOCK")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @Column(name = "ST_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "ST_QUANTITY")
    private Integer quantity;
    @Column(name = "ST_LOCATION_CODE")
    private String locationCode;
    @Column(name = "ST_ESTADO")
    private boolean active;

    public static Stock createEntityFromDTO(StockDTO stockDTO) {
	return Stock.builder().id(stockDTO.getId()).quantity(stockDTO.getQuantity())
		.locationCode(stockDTO.getLocationCode()).build();
    }
}
