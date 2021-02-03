package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.dtos.CategoryDTO;
import ar.com.gl.shop.product.dtos.CategoryDescriptionDTO;

public interface CategoryService {

	// CRUD methods
	public CategoryDTO createCategory(CategoryDTO categoryDTO);

	public CategoryDTO getCategoryById(Long id);

	public List<CategoryDTO> getAllCategories();

	public List<CategoryDTO> getAllCategoriesByName(String name);

	public CategoryDTO updateCategory(CategoryDTO categoryDTO);

	public void deleteCategoryById(Long categoryId);

	public CategoryDTO patchCategory(CategoryDescriptionDTO categoryDescription, Long id);

}
