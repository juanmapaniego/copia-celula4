package ar.com.gl.shop.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.gl.shop.product.dtos.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @Column(name = "CA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "CA_NAME")
    private String name;
    @Column(name = "CA_DESCRIPTION")
    private String description;
    @Column(name = "CA_ESTADO")
    private String active;

    public static Category createFromDTO(CategoryDTO categoryDTO) {
	return Category.builder().id(categoryDTO.getId()).name(categoryDTO.getName())
		.description(categoryDTO.getDescription()).build();

    }
}
