package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.dto.CategoryDto;
import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.repositories.CategoryRepository;
import com.hrovina.onlinestore.services.exceptions.DataIntegrityException;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category search(Integer id) {
        Optional<Category> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + Category.class.getName()));
    }

    public Category insert(Category category){
        category.setId(null);
        return repo.save(category);
    }

    public Category update(Category category){
        Category newCategory = search(category.getId());
        updateData(newCategory, category);
        return repo.save(newCategory);
    }

    public void delete(Integer id){
        search(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cannot delete object because it's associated with other objects.");
        }
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDto categorydto){
        return new Category(categorydto.getId(), categorydto.getName());
    }

    private void updateData(Category newCategory, Category category){
        newCategory.setName(category.getName());
    }
}
