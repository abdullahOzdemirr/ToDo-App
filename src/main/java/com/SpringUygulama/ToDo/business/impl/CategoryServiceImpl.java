package com.SpringUygulama.ToDo.business.impl;

import com.SpringUygulama.ToDo.business.service.CategoryService;
import com.SpringUygulama.ToDo.dto.request.CategoryRequestDTO;
import com.SpringUygulama.ToDo.dto.response.CategoryResponseDTO;
import com.SpringUygulama.ToDo.entity.Category;
import com.SpringUygulama.ToDo.exception.categoryException.CategoryNotFoundException;
import com.SpringUygulama.ToDo.exception.categoryException.CategoryNotNullException;
import com.SpringUygulama.ToDo.mapper.CategoryMapper;
import com.SpringUygulama.ToDo.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private static final Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryMapper.mapCategoriesToCategoriDTOs(categoryRepository.findAll());
    }

    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        if(categoryRequestDTO.getName().isBlank()){
            logger.error("Category name is null");
            throw new CategoryNotNullException("Category name must be not null");
        }
        return categoryMapper.mapCategoryToCategoryResponseDTO(categoryRepository.save(categoryMapper.mapCategoryRequestDTOToCategory(categoryRequestDTO)));
    }

    @Override
    public String deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(()->{
            logger.error("Category id is not found");
            return new CategoryNotFoundException("Category id is not found");
        });
        categoryRepository.deleteById(categoryId);
        return "Kategori silindi";
    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO,Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(()->{
            logger.error("Category id is not found");
            return new CategoryNotFoundException("Category id is not found");
        });
        if(categoryRequestDTO.getName().isBlank()){
            logger.error("Category name is null");
            throw new CategoryNotNullException("Category name must be not null");
        }
        Category category=categoryMapper.mapCategoryRequestDTOToCategory(categoryRequestDTO);
        category.setCategoryId(categoryId);
        return categoryMapper.mapCategoryToCategoryResponseDTO(categoryRepository.save(category));
    }
}
