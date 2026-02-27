package com.ecommerce.sb_ecom.controller;


import com.ecommerce.sb_ecom.Service.CategoryService;
import com.ecommerce.sb_ecom.config.AppConstants;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name="message") String message){
        return new ResponseEntity<>("Echoed message :"+message,HttpStatus.OK);
    }


    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getCategory(
        @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
        @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
        @RequestParam(name = "sortBy" ,defaultValue = AppConstants.SORT_CATEGORIES,required = false)String sortCategories,
        @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
        ){
            CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortCategories,sortDir);
            return new ResponseEntity<>(categoryResponse, HttpStatus.OK);

    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO=categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/api/public/categories/{CategoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long CategoryId) {
            CategoryDTO deleteCategory = categoryService.deleteCategory(CategoryId);
            return new ResponseEntity<>(deleteCategory,HttpStatus.OK);

        }


    @PutMapping("/api/public/categories/{CategoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable Long CategoryId){
        CategoryDTO savedCategoryDTO =categoryService.updateCategory(categoryDTO,CategoryId);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);
    }
}