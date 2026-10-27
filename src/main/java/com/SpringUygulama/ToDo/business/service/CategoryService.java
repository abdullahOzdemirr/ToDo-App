package com.SpringUygulama.ToDo.business.service;

import com.SpringUygulama.ToDo.dto.request.CategoryRequestDTO;
import com.SpringUygulama.ToDo.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO);

    String deleteCategory(Long categoryId);

        CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequesDTO,Long categoryId);
}
