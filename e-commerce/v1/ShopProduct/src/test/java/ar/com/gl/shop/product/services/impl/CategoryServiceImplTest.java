package ar.com.gl.shop.product.services.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import ar.com.gl.shop.product.dtos.CategoryDTO;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

	private static final Long CA_ID = 1L;

	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;

	@Mock
	private CategoryRepository categoryRepository;

	@Test
	@DisplayName("Create new category")
	public void createCategoryTest() {

		categoryServiceImpl.createCategory(CategoryDTO.builder().build());

		Mockito.verify(categoryRepository).save(Mockito.any());
	}

	@Test
	@DisplayName("Should throw IllegalArgumentException when create category")
	public void createCategoryShouldThrowExceptionTest() {
		Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class,
				() -> categoryServiceImpl.createCategory(CategoryDTO.builder().build()));
	}

	@Test
	@DisplayName("Should throw NullPointerException when create category")
	public void createCategoryShouldThrowNullExceptionTest() {
		Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenThrow(NullPointerException.class);

		assertDoesNotThrow(() -> categoryServiceImpl.createCategory(CategoryDTO.builder().build()));
	}

	@Test
	@DisplayName("Get one category")
	public void getCategoryByIdTest() {
		Mockito.when(categoryRepository.getOne(Mockito.anyLong())).thenReturn(Category.builder().id(CA_ID).build());

		assertEquals(CA_ID, categoryServiceImpl.getCategoryById(CA_ID).getId());
	}

	@Test
	@DisplayName("Get one category shoud return null")
	public void getCategoryByIdHouldReturnNullTest() {
		Mockito.when(categoryRepository.getOne(Mockito.anyLong())).thenReturn(null);

		assertThrows(NullPointerException.class, () -> categoryServiceImpl.getCategoryById(CA_ID));
	}

	@Test
	@DisplayName("Get all categories")
	public void getAllCategoriesTest() {
		Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

		assertTrue(categoryServiceImpl.getAllCategories().isEmpty());

	}

	@Test
	@DisplayName("Get some categories")
	public void getSomeCategoriesTest() {
		List<Category> categories = new ArrayList();
		categories.add(Category.builder().build());
		Mockito.when(categoryRepository.findAllByName(Mockito.anyString())).thenReturn(categories);

		assertFalse(categoryServiceImpl.getAllCategoriesByName("").isEmpty());

	}

	@Test
	@DisplayName("Update category")
	public void updateCategoryTest() {

		categoryServiceImpl.updateCategory(CategoryDTO.builder().build());

		Mockito.verify(categoryRepository).save(Mockito.any());

	}

	@Test
	@DisplayName("Update Category throws Illegal pointer exception")
	void updateCategoryException() {

		Mockito.when(categoryRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class,
				() -> categoryServiceImpl.updateCategory(CategoryDTO.builder().build()));

	}

	@Test
	@DisplayName("Should throw NullPointerException when update category")
	public void updateCategoryShouldThrowNullExceptionTest() {
		Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenThrow(NullPointerException.class);

		assertDoesNotThrow(() -> categoryServiceImpl.updateCategory(CategoryDTO.builder().build()));
	}

	@Test
	@DisplayName("Delete category")
	public void deleteCategory() {

		categoryServiceImpl.deleteCategoryById(1l);

		Mockito.verify(categoryRepository).deleteById(Mockito.any());
	}
}
