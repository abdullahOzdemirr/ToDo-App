package com.SpringUygulama.ToDo.mapper;

import com.SpringUygulama.ToDo.dto.request.CategoryRequestDTO;
import com.SpringUygulama.ToDo.dto.response.CategoryResponseDTO;
import com.SpringUygulama.ToDo.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Category mapCategoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO) {
        return modelMapper.map(categoryRequestDTO,Category.class);
    }

    public CategoryResponseDTO mapCategoryToCategoryResponseDTO(Category category) {
        return modelMapper.map(category, CategoryResponseDTO.class);
    }

    public List<CategoryResponseDTO> mapCategoriesToCategoriDTOs(List<Category> categories) {
        List<CategoryResponseDTO> categoryResponseDTOS =new ArrayList<CategoryResponseDTO>();
        for(Category category:categories){
            CategoryResponseDTO x =modelMapper.map(category, CategoryResponseDTO.class);
            categoryResponseDTOS.add(x);
        }
        return categoryResponseDTOS;
    }
}
