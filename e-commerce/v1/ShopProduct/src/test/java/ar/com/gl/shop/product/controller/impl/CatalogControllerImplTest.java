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

import ar.com.gl.shop.product.dtos.CategoryDTO;
import ar.com.gl.shop.product.dtos.CategoryDescriptionDTO;
import ar.com.gl.shop.product.exceptions.WrongValueException;
import ar.com.gl.shop.product.services.CategoryService;

@ExtendWith(MockitoExtension.class)
class CatalogControllerImplTest {

	private static final String CA_NAME = "Telefonia";
	private static final String CA_DESC = "Smartphones";

	@InjectMocks
	private CatalogControllerImpl catalogController;
	@Mock
	private CategoryService categoryService;

	@Test
	@DisplayName("Get all")
	void testGetAll() {
		Mockito.when(categoryService.getAllCategories()).thenReturn(new ArrayList<>());

		assertTrue(catalogController.getAll(null).getBody().isEmpty());
	}

	@Test
	@DisplayName("Get all by name")
	void testGetAllByName() {
		List<CategoryDTO> categories = new ArrayList<>();
		categories.add(CategoryDTO.builder().build());
		Mockito.when(categoryService.getAllCategoriesByName(Mockito.anyString())).thenReturn(categories);

		assertFalse(catalogController.getAll("").getBody().isEmpty());
	}

	@Test
	@DisplayName("Get by id")
	void testGetById() {
		Mockito.when(categoryService.getCategoryById(Mockito.anyLong())).thenReturn(CategoryDTO.builder().build());

		ResponseEntity<CategoryDTO> response = catalogController.getById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	@DisplayName("Get by id should throw WrongValueException")
	void testGetByIdShoulThrowWrongValueException() {
		assertThrows(WrongValueException.class, () -> catalogController.getById(-1L));
	}

	@Test
	@DisplayName("Create category")
	void testCreateCategory() {
		Mockito.when(categoryService.createCategory(Mockito.any()))
				.thenReturn(CategoryDTO.builder().name(CA_NAME).build());

		ResponseEntity<CategoryDTO> response = catalogController.createCategory(Mockito.any());

		Mockito.verify(categoryService).createCategory(Mockito.any());

		assertEquals(CA_NAME, response.getBody().getName());
	}

	@Test
	@DisplayName("Patch category return ok")
	void testPatchCategoryShouldReturnOk() {
		Mockito.when(categoryService.patchCategory(Mockito.any(), Mockito.anyLong()))
				.thenReturn(CategoryDTO.builder().description(CA_DESC).build());

		assertEquals(CA_DESC, catalogController.patchCategory(CategoryDescriptionDTO.builder().build(), 1L).getBody()
				.getDescription());
	}

	/*
	 * @Test
	 * 
	 * @DisplayName("Patch category return error") void
	 * testPatchCategoryShouldReturn500() {
	 * 
	 * assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
	 * catalogController.patchCategory("", 1L).getStatusCode());
	 * 
	 * }
	 */

	@Test
	@DisplayName("Patch category throw WrongValueException")
	void testPatchCategoryShouldThrowWrongValueException() {

		assertThrows(WrongValueException.class,
				() -> catalogController.patchCategory(CategoryDescriptionDTO.builder().build(), -1L));

	}

	@Test
	@DisplayName("Delete category return OK")
	void testDeleteCategoryShouldReturnOk() {
		assertEquals(HttpStatus.OK, catalogController.deleteCategory(1L).getStatusCode());
	}

	@Test
	@DisplayName("Delete category throws WrongValueException")
	void testDeleteCategoryShouldThrowsWrongValueException() {
		assertThrows(WrongValueException.class, () -> catalogController.deleteCategory(-1L));
	}

}
