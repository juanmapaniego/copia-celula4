package ar.com.gl.shop.product.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.com.gl.shop.product.dtos.ProductDTO;
import ar.com.gl.shop.product.dtos.ProductDescriptionDTO;
import ar.com.gl.shop.product.exceptions.WrongValueException;
import ar.com.gl.shop.product.services.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerImplTest {

    @InjectMocks
    private ProductControllerImpl productController;
    @Mock
    private ProductService productService;

    @Test
    @DisplayName("Get By id")
    void testFindById() {
	Mockito.when(productService.getProduct(Mockito.anyLong())).thenReturn(ProductDTO.builder().build());
	ResponseEntity<ProductDTO> response = productController.findById(1L);

	assertNotNull(response.getBody());
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Get All")
    void testFindAll() {
	Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<>());
	ResponseEntity<Iterable<ProductDTO>> response = productController.findAll(null, null);

	assertTrue(((List<ProductDTO>) response.getBody()).isEmpty());
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Get All by name")
    void testFindAllByName() {
	List<ProductDTO> products = new ArrayList<>();
	products.add(ProductDTO.builder().build());
	Mockito.when(productService.findByName(Mockito.anyString())).thenReturn(products);
	ResponseEntity<Iterable<ProductDTO>> response = productController.findAll("", null);

	assertFalse(((List<ProductDTO>) response.getBody()).isEmpty());
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Get All by category")
    void testFindAllByCategory() {
	productController.findAll(null, 1L);

	Mockito.verify(productService).findByCategoryId(Mockito.anyLong());
    }

    @Test
    @DisplayName("Get All by name and category")
    void testFindAllByNameAndCategory() {
	List<ProductDTO> products = new ArrayList<>();
	products.add(ProductDTO.builder().build());
	Mockito.when(productService.findByNameAndCategoryId(Mockito.anyString(), Mockito.anyLong()))
		.thenReturn(products);
	ResponseEntity<Iterable<ProductDTO>> response = productController.findAll("", 1L);

	assertNotNull(response.getBody());
	Mockito.verify(productService).findByNameAndCategoryId(Mockito.anyString(), Mockito.anyLong());
	assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Create product")
    void testCreate() {
	ProductDTO dto = ProductDTO.builder().id(1L).build();

	Mockito.when(productService.createProduct(Mockito.any())).thenReturn(dto);

	ResponseEntity<ProductDTO> response = productController.create(dto);

	assertEquals(1L, response.getBody().getId());
	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Delete product return ok")
    void testDeleteReturnOK() {
	assertEquals(HttpStatus.OK, productController.delete(1L).getStatusCode());
    }

    @Test
    @DisplayName("Delete product should thow wrongValueException")
    void testDeleteShouldThrowWrongValueException() {
	assertThrows(WrongValueException.class, () -> productController.delete(-1L));
    }

    @Test
    @DisplayName("Update product return OK")
    void testUpdateReturnAccepted() {
	assertEquals(HttpStatus.OK, productController.update(1L, ProductDTO.builder().build()).getStatusCode());
    }

    @Test
    @DisplayName("Update product throw wrong value exception")
    void testUpdateShouldThrowWrongValueException() {
	assertThrows(WrongValueException.class, () -> productController.update(-1L, ProductDTO.builder().build()));
    }

    @Test
    @DisplayName("Update product by description return OK")
    void testUpdateByDescriptionReturnAccepted() {

	assertNotNull(productController.updateDescription(1L, ProductDescriptionDTO.builder().description("").build()));
    }

    @Test
    @DisplayName("Update product by description throw wrong value exception")
    void testUpdateByDescriptionShouldThrowWrongValueException() {
	assertThrows(WrongValueException.class, () -> productController.updateDescription(-1L,
		ProductDescriptionDTO.builder().description("").build()));
    }

}
