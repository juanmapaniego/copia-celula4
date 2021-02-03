package ar.com.gl.shop.product.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import ar.com.gl.shop.product.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "PR_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PR_NAME")
    private String name;

    @Column(name = "PR_DESCRIPTION")
    private String description;
    @Column(name = "PR_PRECIO")
    private Double price;
    @Column(name = "PR_ESTADO")
    private String active;
    @Temporal(TemporalType.DATE)
    @Column(name = "PR_CREATION_DATE")
    @CreatedDate
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "PR_MODIFICATION_DATE")
    @LastModifiedDate
    private Date modifiedAt;
    @ManyToOne
    @JoinColumn(name = "ST_ID")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "CA_ID")
    private Category category;

    public static Product createFromDTO(ProductDTO productDTO) {
	return Product.builder().id(productDTO.getId()).name(productDTO.getName())
		.description(productDTO.getDescription()).price(productDTO.getPrice())
		.createdAt(productDTO.getCreatedAt()).modifiedAt(productDTO.getModifiedAt())
		.stock(Stock.createEntityFromDTO(productDTO.getStock()))
		.category(Category.createFromDTO(productDTO.getCategory())).build();
    }

}
