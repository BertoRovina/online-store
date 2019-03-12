package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.repositories.CategoryRepository;
import com.hrovina.onlinestore.services.exceptions.DataIntegrityException;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        search(category.getId());
        return repo.save(category);
    }

    public void delete(Integer id){
        search(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cannot delete object because it's associated with other objects.");
        }
    }
}
