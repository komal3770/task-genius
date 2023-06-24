package com.taskgenius.services;

import com.taskgenius.entities.Category;
import com.taskgenius.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

  public Category getByName(String name) {
    Category category = categoryRepository.findByName(name)
        .orElse(new Category(null, name));

    if (category.getId() == null) {
      category = categoryRepository.save(category);
    }
    return category;
  }

  public List<Category> getAllCategories(){
    return categoryRepository.findAll();
  }
}
