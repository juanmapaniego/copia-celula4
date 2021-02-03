package ar.com.gl.shop.product.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.shop.product.model.Category;

@Repository
public interface CategoryRepository extends Serializable, JpaRepository<Category, Long> {

  List<Category> findAllByName(String name);
}
