package ar.com.gl.shop.product.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.shop.product.model.Product;

@Repository
public interface ProductRepository extends Serializable, JpaRepository<Product, Long> {
	
	List <Product> findByName(String name);
	List <Product> findByCategoryId(Long category);
	List <Product> findByNameAndCategoryId(String name, Long category);
	
}
