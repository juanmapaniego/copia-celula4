package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.dtos.ProductDTO;
import ar.com.gl.shop.product.dtos.ProductDescriptionDTO;

public interface ProductService {

	// CRUD methods
	public ProductDTO createProduct(ProductDTO productDTO);

	public ProductDTO getProduct(Long id);

	public List<ProductDTO> getAllProducts();

	public List<ProductDTO> findByName(String name);

	public List<ProductDTO> findByCategoryId(Long category);

	public List<ProductDTO> findByNameAndCategoryId(String name, Long category);

	public ProductDTO updateProduct(ProductDTO updatedProductDTO);

	public void logicalDeleteProduct(ProductDTO productDTO);

	public void physicalDeleteProduct(ProductDTO productDTO);

	public ProductDTO updateDescription(ProductDescriptionDTO productDescription, Long productId);

}
