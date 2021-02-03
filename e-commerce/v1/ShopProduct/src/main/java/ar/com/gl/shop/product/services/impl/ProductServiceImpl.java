package ar.com.gl.shop.product.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.shop.product.dtos.ProductDTO;
import ar.com.gl.shop.product.dtos.ProductDescriptionDTO;
import ar.com.gl.shop.product.exceptions.ProductNotFoundException;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.ProductRepository;
import ar.com.gl.shop.product.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

	Product product = Product.createFromDTO(productDTO);
	product.setActive("1");
	product.setCreatedAt(new Date());

	try {
	    return ProductDTO.createfromEntity(this.repository.save(product));
	} catch (NullPointerException e) {
	    System.out.println(e.getMessage());
	}
	return null;
    }

    @Override
    public ProductDTO getProduct(Long id) {
	Product product = this.repository.getOne(id);
	if (product.getActive().equals("0"))
	    throw new ProductNotFoundException();
	return ProductDTO.createfromEntity(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {

	return getDTOsFromProductsList(this.repository.findAll());
    }

    @Override
    public ProductDTO updateDescription(ProductDescriptionDTO productDescription, Long productId) {
	Product product = repository.findById(productId)
		.orElseThrow(() -> new IllegalArgumentException("Id producto inexistente "));
	product.setDescription(productDescription.getDescription());
	return ProductDTO.createfromEntity(repository.save(product));
    }

    @Override
    public ProductDTO updateProduct(ProductDTO updatedProductDTO) {
	Product updatedProduct = repository.findById(updatedProductDTO.getId())
		.orElseThrow(() -> new IllegalArgumentException("Id producto inexistente "));

	updatedProduct.setModifiedAt(new Date());
	updatedProduct.setCategory(Category.createFromDTO(updatedProductDTO.getCategory()));
	updatedProduct.setStock(Stock.createEntityFromDTO(updatedProductDTO.getStock()));
	updatedProduct.setDescription(updatedProductDTO.getDescription());
	updatedProduct.setName(updatedProductDTO.getName());
	updatedProduct.setPrice(updatedProductDTO.getPrice());

	return ProductDTO.createfromEntity(repository.save(updatedProduct));
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long categoryId) {

	return getDTOsFromProductsList(repository.findByCategoryId(categoryId));
    }

    @Override
    public List<ProductDTO> findByName(String name) {

	return getDTOsFromProductsList(repository.findByName(name));
    }

    @Override
    public List<ProductDTO> findByNameAndCategoryId(String name, Long categoryId) {
	return getDTOsFromProductsList(repository.findByNameAndCategoryId(name, categoryId));
    }

    @Override
    public void logicalDeleteProduct(ProductDTO productDTO) {

	Product product = this.repository.findById(productDTO.getId()).orElseThrow(IllegalArgumentException::new);

	product.setActive("0");
	product.setModifiedAt(new Date());

	this.repository.save(product);
    }

    @Override
    public void physicalDeleteProduct(ProductDTO productDTO) {
	Product product = Product.createFromDTO(productDTO);
	this.repository.delete(product);
    }

    private List<ProductDTO> getDTOsFromProductsList(List<Product> products) {

	List<ProductDTO> productsDTO = new ArrayList<>();

	for (Product product : products) {

	    ProductDTO dto = ProductDTO.createfromEntity(product);

	    productsDTO.add(dto);
	}

	return productsDTO;
    }
}
