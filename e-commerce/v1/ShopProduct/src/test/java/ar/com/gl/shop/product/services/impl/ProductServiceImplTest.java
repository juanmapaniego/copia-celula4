package ar.com.gl.shop.product.services.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.gl.shop.product.dtos.CategoryDTO;
import ar.com.gl.shop.product.dtos.ProductDTO;
import ar.com.gl.shop.product.dtos.ProductDescriptionDTO;
import ar.com.gl.shop.product.dtos.StockDTO;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private static final String PR_DESC = "Agua de manantiales";

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("Create new product")
    public void createProductTest() {

	productServiceImpl.createProduct(
		ProductDTO.builder().stock(StockDTO.builder().build()).category(CategoryDTO.builder().build()).build());

	Mockito.verify(productRepository).save(Mockito.any());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when create product")
    public void createProductShouldThrowExceptionTest() {
	Mockito.when(productRepository.save(Mockito.any(Product.class))).thenThrow(IllegalArgumentException.class);

	assertThrows(IllegalArgumentException.class, () -> productServiceImpl.createProduct(ProductDTO.builder()
		.stock(StockDTO.builder().build()).category(CategoryDTO.builder().build()).build()));
    }

    @Test
    @DisplayName("Should throw NullPointerException when create product")
    public void createProductShouldThrowNPExceptionTest() {
	Mockito.when(productRepository.save(Mockito.any(Product.class))).thenThrow(NullPointerException.class);

	assertDoesNotThrow(() -> productServiceImpl.createProduct(ProductDTO.builder().stock(StockDTO.builder().build())
		.category(CategoryDTO.builder().build()).build()));
    }

    @Test
    @DisplayName("Should throw NullPointerException when create product")
    public void createProductShouldThrowNullExceptionTest() {
	assertThrows(NullPointerException.class, () -> productServiceImpl.createProduct(ProductDTO.builder().build()));
    }

//    @Test
//    @DisplayName("Get one product")
//    public void getProductByIdTest() {
//	Mockito.when(productRepository.getOne(Mockito.anyLong())).thenReturn(
//		Product.builder().stock(Stock.builder().build()).category(Category.builder().build()).build());
//
//	assertNotNull(productServiceImpl.getProduct(1L));
//    }

    @Test
    @DisplayName("Get one product throw NullPointerException")
    public void getProductByIdShouldThrowNPExceptionTest() {
	Mockito.when(productRepository.getOne(Mockito.anyLong())).thenReturn(Product.builder().build());

	assertThrows(NullPointerException.class, () -> productServiceImpl.getProduct(1L));
    }

    @Test
    @DisplayName("Get all products")
    public void getAllCategoriesTest() {
	Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<>());

	assertTrue(productServiceImpl.getAllProducts().isEmpty());

    }

//    @Test
//    @DisplayName("Update product")
//    public void updateProductTest() {
//	ProductDTO productDTO = ProductDTO.builder().id(10l).description(PR_DESC).stock(StockDTO.builder().build())
//		.category(CategoryDTO.builder().build()).build();
//
//	doNothing().when(productRepository.findById(10l));
//
//	productServiceImpl.updateProduct(productDTO);
//
//	Mockito.verify(productRepository).findById(10l);
//    }

//    @Test
//    @DisplayName("Should throw NullPointerException when create product")
//    public void updateProductShouldThrowNPExceptionTest() {
//	assertThrows(NullPointerException.class, () -> productServiceImpl.createProduct(ProductDTO.builder().build()));
//    }

//    @Test
//    @DisplayName("Should throw IllegalArgumentException when create product")
//    public void createProductShouldThrowIAExceptionTest() {
//
//	assertThrows(IllegalArgumentException.class, () -> productServiceImpl.updateProduct(ProductDTO.builder()
//		.stock(StockDTO.builder().build()).category(CategoryDTO.builder().build()).build()));
//    }

    @Test
    @DisplayName("Physical Delete product")
    public void physicalDeleteProductTest() {
	productServiceImpl.physicalDeleteProduct(
		ProductDTO.builder().stock(StockDTO.builder().build()).category(CategoryDTO.builder().build()).build());

	Mockito.verify(productRepository).delete(Mockito.any());
    }

    @Test
    @DisplayName("Logical Delete product")
    public void logicalDeleteProductTest() {
	Mockito.when(productRepository.findById(Mockito.anyLong())).thenThrow(IllegalArgumentException.class);

	assertThrows(IllegalArgumentException.class,
		() -> productServiceImpl.logicalDeleteProduct(ProductDTO.builder().id(1L).build()));
    }

    @Test
    @DisplayName("Delete product should throw NullPointerException")
    public void physicalDeleteProductShoudThrowNPExceptionTest() {
	assertThrows(NullPointerException.class,
		() -> productServiceImpl.physicalDeleteProduct(ProductDTO.builder().build()));
    }

    @Test
    @DisplayName("Get all by product name")
    public void getAllByNameCategoriesTest() {
	List<Product> products = new ArrayList<>();
	products.add(Product.builder().stock(Stock.builder().build()).category(Category.builder().build()).build());
	Mockito.when(productRepository.findByName(Mockito.anyString())).thenReturn(products);

	assertFalse(productServiceImpl.findByName("").isEmpty());
    }

    @Test
    @DisplayName("Get all by category id")
    public void getAllByCategoryTest() {
	Mockito.when(productRepository.findByCategoryId(Mockito.anyLong())).thenReturn(new ArrayList<>());
	assertNotNull(productServiceImpl.findByCategoryId(1L));

    }

    @Test
    @DisplayName("Get all by product name and category")
    public void getAllByNameAndCategoryTest() {
	List<Product> products = new ArrayList<>();
	products.add(
		Product.builder().id(1L).stock(Stock.builder().build()).category(Category.builder().build()).build());
	Mockito.when(productRepository.findByNameAndCategoryId(Mockito.anyString(), Mockito.anyLong()))
		.thenReturn(products);

	assertEquals(1L, productServiceImpl.findByNameAndCategoryId("", 1L).get(0).getId());
    }

    @Test
    @DisplayName("Logical delete returns ok")
    public void logicalDeleteTest() {
	Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional
		.of(Product.builder().stock(Stock.builder().build()).category(Category.builder().build()).build()));

	productServiceImpl.logicalDeleteProduct(ProductDTO.builder().id(1L).build());

	Mockito.verify(productRepository).save(Mockito.any());
    }

    @Test
    @DisplayName("Patch description returns ok")
    public void patchDescriptionTest() {
	Product product = Product.builder().id(1L).active("Active").createdAt(new Date()).modifiedAt(new Date())
		.name("Celular").price(1000.0).description("").stock(Stock.builder().build())
		.category(Category.builder().build()).build();

	Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
	Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

	productServiceImpl.updateDescription(ProductDescriptionDTO.builder().description("").build(), 1L);

	Mockito.verify(productRepository).save(Mockito.any());
    }

    @Test
    @DisplayName("Patch description should throw exception")
    public void patchDescriptionShouldThrowExceptionTest() {
	Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

	assertThrows(IllegalArgumentException.class, () -> productServiceImpl
		.updateDescription(ProductDescriptionDTO.builder().description("").build(), 1L));

    }
}