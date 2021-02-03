package ar.com.gl.shop.product.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.shop.product.dtos.CategoryDTO;
import ar.com.gl.shop.product.dtos.CategoryDescriptionDTO;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repository.CategoryRepository;
import ar.com.gl.shop.product.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	// CRUD methods
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {

		Category category = Category.createFromDTO(categoryDTO);
		category.setActive("1");

		try {
			return CategoryDTO.createFromEntity(this.repository.save(category));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public CategoryDTO getCategoryById(Long id) {
		return CategoryDTO.createFromEntity(this.repository.getOne(id));
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return getDTOsFromCategoriesList(this.repository.findAll());
	}

	@Override
	public List<CategoryDTO> getAllCategoriesByName(String name) {

		return getDTOsFromCategoriesList(this.repository.findAllByName(name));
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO updatedCategoryDTO) {
		Category updatedCategory = Category.createFromDTO(updatedCategoryDTO);

		try {
			return CategoryDTO.createFromEntity(repository.save(updatedCategory));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void deleteCategoryById(Long categoryId) {
		repository.deleteById(categoryId);
	}

	private List<CategoryDTO> getDTOsFromCategoriesList(List<Category> categories) {

		List<CategoryDTO> catalogsDTOs = new ArrayList<>();

		for (Category category : categories) {

			CategoryDTO dto = CategoryDTO.createFromEntity(category);

			catalogsDTOs.add(dto);
		}

		return catalogsDTOs;
	}

	public CategoryDTO patchCategory(CategoryDescriptionDTO categoryDescription, Long id) {
		Category category = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id catgoría inexistente "));
		category.setDescription(categoryDescription.getDescription());
		return CategoryDTO.createFromEntity(this.repository.save(category));
	}
}
