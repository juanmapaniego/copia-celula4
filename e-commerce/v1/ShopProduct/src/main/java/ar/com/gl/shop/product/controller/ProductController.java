package ar.com.gl.shop.product.controller;

import org.springframework.http.ResponseEntity;

import ar.com.gl.shop.product.dtos.ProductDTO;
import ar.com.gl.shop.product.dtos.ProductDescriptionDTO;

public interface ProductController {

	public ResponseEntity<ProductDTO> findById(Long productId);

	public ResponseEntity<Iterable<ProductDTO>> findAll(String name, Long categoryId);

	public ResponseEntity<ProductDTO> create(ProductDTO productDTO);

	public ResponseEntity<Void> delete(Long productId);

	public ResponseEntity<ProductDTO> update(Long productId, ProductDTO updateProductDTO);

	public ResponseEntity<ProductDTO> updateDescription(Long productId, ProductDescriptionDTO productDescription);

}
