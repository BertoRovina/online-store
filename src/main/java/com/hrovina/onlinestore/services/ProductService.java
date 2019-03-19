package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.Product;
import com.hrovina.onlinestore.repositories.CategoryRepository;
import com.hrovina.onlinestore.repositories.ProductRepository;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product search(Integer id) {
        Optional<Product> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

            List<Category> categories = categoryRepository.findAllById(ids);
            return repo.findDistinctByNameContainingAndCategoryListIn(name, categories, pageRequest);
    }
}
