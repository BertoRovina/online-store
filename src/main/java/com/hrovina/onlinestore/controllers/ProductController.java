package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.controllers.utils.URL;
import com.hrovina.onlinestore.dto.CategoryDto;
import com.hrovina.onlinestore.dto.ProductDto;
import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.Product;
import com.hrovina.onlinestore.services.OrderService;
import com.hrovina.onlinestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> find(@PathVariable Integer id){
        Product obj = productService.search(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "oderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        String decodedName = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = productService.search(decodedName, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDto> listDto = list.map(obj -> new ProductDto(obj));
        return ResponseEntity.ok().body(listDto);

    }
}


