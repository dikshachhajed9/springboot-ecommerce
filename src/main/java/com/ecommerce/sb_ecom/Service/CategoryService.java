package com.ecommerce.sb_ecom.Service;

import com.ecommerce.sb_ecom.Model.Category;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {


    CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortCategories,String sortDir);

    CategoryDTO createCategory(CategoryDTO categoryDTO) ;



    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
