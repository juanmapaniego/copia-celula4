package ar.com.gl.shop.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ar.com.gl.shop.api.feign.dto.CategoryDTO;
import ar.com.gl.shop.api.feign.dto.CategoryDescriptionDTO;

public interface CatalogController {
    public ResponseEntity<List<CategoryDTO>> getAll(String name);

    public ResponseEntity<CategoryDTO> getById(Long id);

    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO);

    public ResponseEntity<CategoryDTO> patchCategory(CategoryDescriptionDTO categoryDescription, Long id);

    public ResponseEntity<Void> deleteCategory(Long id);
}
