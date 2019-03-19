package com.hrovina.onlinestore.repositories;

import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


//    Spring data makes the use of the method name to create the query
    @Transactional(readOnly=true)
    Page<Product> findDistinctByNameContainingAndCategoryListIn(@Param("name") String name, @Param("categoryList") List<Category> categories,
                                                              Pageable pageRequest);
}
