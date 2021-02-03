package ar.com.gl.shop.product.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.shop.product.model.Stock;

@Repository
public interface StockRepository extends Serializable, JpaRepository<Stock, Long> {

}
