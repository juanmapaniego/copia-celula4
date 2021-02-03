package ar.com.gl.shop.product.controller.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shop.product.controller.ProductController;
import ar.com.gl.shop.product.dtos.ProductDTO;
import ar.com.gl.shop.product.dtos.ProductDescriptionDTO;
import ar.com.gl.shop.product.exceptions.WrongValueException;
import ar.com.gl.shop.product.services.ProductService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/products")
@Api(tags = { "Products" })
public class ProductControllerImpl implements ProductController {

	@Autowired
	private ProductService service;

	@Override
	@GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findById(@PathVariable(name = "productId") Long productId) {

		return new ResponseEntity<>(service.getProduct(productId), HttpStatus.OK);
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<ProductDTO>> findAll(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "category", required = false) Long categoryId) {
		List<ProductDTO> response = null;
		if (!Objects.isNull(name) && !Objects.isNull(categoryId)) {
			response = service.findByNameAndCategoryId(name, categoryId);
		} else if (!Objects.isNull(name)) {
			response = service.findByName(name);
		} else if (!Objects.isNull(categoryId)) {
			response = service.findByCategoryId(categoryId);
		} else {
			response = service.getAllProducts();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<>(service.createProduct(productDTO), HttpStatus.CREATED);
	}

	@Override
	@DeleteMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable(name = "productId") Long productId) {

		if (productId <= 0l)
			throw new WrongValueException();

		service.logicalDeleteProduct(service.getProduct(productId));

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@PutMapping(path = "/{product}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> update(@PathVariable("product") Long productId,
			@RequestBody ProductDTO updateProductDTO) {

		if (productId <= 0l)
			throw new WrongValueException();

		return new ResponseEntity<>(service.updateProduct(updateProductDTO), HttpStatus.OK);
	}

	@Override
	@PatchMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> updateDescription(@PathVariable Long productId,
			@RequestBody ProductDescriptionDTO productDescription) {

		if (productId <= 0l)
			throw new WrongValueException();

		return new ResponseEntity<>(service.updateDescription(productDescription, productId), HttpStatus.OK);
	}
}
