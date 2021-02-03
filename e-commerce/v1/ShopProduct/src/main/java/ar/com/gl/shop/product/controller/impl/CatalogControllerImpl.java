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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shop.product.controller.CatalogController;
import ar.com.gl.shop.product.dtos.CategoryDTO;
import ar.com.gl.shop.product.dtos.CategoryDescriptionDTO;
import ar.com.gl.shop.product.exceptions.WrongValueException;
import ar.com.gl.shop.product.services.CategoryService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/catalogs")
@Api(tags = { "Catalogs" })
public class CatalogControllerImpl implements CatalogController {

	@Autowired
	private CategoryService service;

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> getAll(@RequestParam(name = "name", required = false) String name) {
		List<CategoryDTO> listCatalog = null;
		if (Objects.isNull(name)) {

			listCatalog = service.getAllCategories();

		} else {
			listCatalog = service.getAllCategoriesByName(name);
		}

		return new ResponseEntity<>(listCatalog, HttpStatus.OK);
	}

	@Override
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> getById(@PathVariable(name = "id") Long id) {

		if (id <= 0l)
			throw new WrongValueException();

		return new ResponseEntity<>(service.getCategoryById(id), HttpStatus.OK);
	}

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
		return new ResponseEntity<>(service.createCategory(categoryDTO), HttpStatus.CREATED);
	}

	@Override
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> patchCategory(@RequestBody CategoryDescriptionDTO categoryDescription,
			@PathVariable Long id) {
		if (id <= 0l)
			throw new WrongValueException();

		return new ResponseEntity<>(service.patchCategory(categoryDescription, id), HttpStatus.OK);

	}

	@Override
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		if (id <= 0l)
			throw new WrongValueException();
		service.deleteCategoryById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
