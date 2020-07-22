/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.controller;

import com.northwind.jpa.entity.Customers;
import com.northwind.jpa.entity.response.ApiResponse;
import com.northwind.jpa.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ahza0
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    
    @GetMapping("")
    public ApiResponse getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Page<Customers> result = service.getByPage(page - 1, pageSize);
        return ApiResponse.ok(result);
    }
    
    @GetMapping("/{id}")
    public ApiResponse getByID(@PathVariable("id") String id) {
        Customers cus = service.getById(id);
        if (cus == null) {
            return ApiResponse.notFound();
        }
        return ApiResponse.ok(cus);
    }
    
    @PostMapping("")
    public ApiResponse create(@RequestBody Customers cus) {
        if (service.getById(cus.getCustomerID()) != null) {
            return ApiResponse.conflict("Data conflict!");
        }
        service.create(cus);
        return ApiResponse.created("Create data success!");
    }

    @PutMapping("")
    public ApiResponse update(@RequestBody Customers cus) {
        if (service.getById(cus.getCustomerID()) == null) {
            return ApiResponse.notFound("Data not found");
        }
        service.update(cus);
        return ApiResponse.ok("Update data success!");
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable("id") String id) {
        if (service.getById(id) == null) {
            return ApiResponse.notFound("Data not found");
        }
        service.delete(id);
        return ApiResponse.ok("Delete data success!");
    }
}
