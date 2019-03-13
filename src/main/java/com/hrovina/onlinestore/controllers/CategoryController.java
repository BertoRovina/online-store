package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.dto.CategoryDto;
import com.hrovina.onlinestore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrovina.onlinestore.entities.Category;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> find(@PathVariable Integer id){
        Category obj = categoryService.search(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDto categoryDto){
        Category category = categoryService.fromDTO(categoryDto);
        category = categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        Category category = categoryService.fromDTO(categoryDto);
        category.setId(id);
        category = categoryService.update(category);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> update(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        List<Category> list = categoryService.findAll();
        List<CategoryDto> listDto = list.stream().map(obj -> new CategoryDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoryDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "oderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        Page<Category> list = categoryService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDto> listDto = list.map(obj -> new CategoryDto(obj));
        return ResponseEntity.ok().body(listDto);

    }
}


