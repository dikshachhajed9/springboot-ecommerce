package com.ecommerce.sb_ecom.Service;

import com.ecommerce.sb_ecom.Model.Category;
import com.ecommerce.sb_ecom.exceptions.APIException;
import com.ecommerce.sb_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
     @Autowired
     private CategoryRepository categoryRepository;
     @Autowired
     private ModelMapper modelMapper;


    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize ,String sortCategories,String sortDir) {
        Sort sortBYAndOrder=sortDir.equalsIgnoreCase("asc")
                ?Sort.by(sortCategories).ascending()
                :Sort.by(sortDir).descending();
        Pageable pageDetail= PageRequest.of(pageNumber,pageSize,sortBYAndOrder);
        Page<Category> categoryPage=categoryRepository.findAll(pageDetail);

        List<Category> categories=categoryPage.getContent();

        if(categories.isEmpty()) {
            throw new APIException("No Category created til  now.");
        }
            List<CategoryDTO> categoryDTOs=categories.stream()
                    .map(category -> modelMapper.map(category, CategoryDTO.class))
                    .toList();
            CategoryResponse categoryResponse =new CategoryResponse();
            categoryResponse.setContent(categoryDTOs);
            categoryResponse.setPageNumber(categoryPage.getNumber());
            categoryResponse.setPageSize(categoryPage.getSize());
            categoryResponse.setTotalElements(categoryPage.getTotalElements());
            categoryResponse.setTotalPages(categoryPage.getTotalPages());
            categoryResponse.setLastPage(categoryPage.isLast());
            return categoryResponse;
        }



    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDB != null)
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists !!!");


        Category savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));



        categoryRepository.delete(category);
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new  ResourceNotFoundException("Category","categoryId",categoryId));
        Category category=modelMapper.map(categoryDTO,Category.class);
        category.setCategoryId(categoryId);
        savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);

    }

    }


