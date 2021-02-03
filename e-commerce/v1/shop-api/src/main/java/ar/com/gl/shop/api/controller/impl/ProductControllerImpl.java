package ar.com.gl.shop.api.controller.impl;

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

import ar.com.gl.shop.api.controller.ProductController;
import ar.com.gl.shop.api.feign.dto.ProductDTO;
import ar.com.gl.shop.api.feign.dto.ProductDescriptionDTO;
import ar.com.gl.shop.api.feign.proxy.ShopProductClient;

@RestController
@RequestMapping(path = "/products")
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ShopProductClient feign;

    @Override
    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> findById(@PathVariable(name = "productId") Long productId) {

	return new ResponseEntity<>(feign.findById(productId), HttpStatus.OK);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ProductDTO>> findAll(@RequestParam(name = "name", required = false) String name,
	    @RequestParam(name = "category", required = false) Long categoryId) {
	Iterable<ProductDTO> response = feign.findAll(name, categoryId);

	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
	return new ResponseEntity<>(feign.create(productDTO), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable(name = "productId") Long productId) {

	feign.delete(productId);

	return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{product}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> update(@PathVariable("product") Long productId,
	    @RequestBody ProductDTO updateProductDTO) {

	return new ResponseEntity<>(feign.update(productId, updateProductDTO), HttpStatus.OK);
    }

    @Override
    @PatchMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> updateDescription(@PathVariable Long productId,
	    @RequestBody ProductDescriptionDTO productDescription) {

	return new ResponseEntity<>(feign.updateDescription(productId, productDescription), HttpStatus.OK);
    }
}
