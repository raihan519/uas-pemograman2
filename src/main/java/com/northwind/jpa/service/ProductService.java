/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;
import com.northwind.jpa.entity.Products;
import com.northwind.jpa.repository.ProductsRepository;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author raihan
 */

@Service
public class ProductService implements RepoService<Products>{
    @PersistenceContext
    private EntityManager em;

    @Resource
    private ProductsRepository repo;

    @Override
    public Products create(Products obj) {
        Products prod = repo.save(obj);
        return prod;
    }
    
    @Override
    public Products update(Products obj) {
        Products prod = repo.findById(obj.getProductID()).orElse(null);
        if (prod != null) { 
            prod.setProductName(obj.getProductName());
            prod.setSupplierID(obj.getSupplierID());
            prod.setCategoryID(obj.getCategoryID());
            prod.setQuantityPerUnit(obj.getQuantityPerUnit());
            prod.setUnitPrice(obj.getUnitPrice());
            prod.setUnitsInStock(obj.getUnitsInStock());
            prod.setUnitsOnOrder(obj.getUnitsOnOrder());
            prod.setReorderLevel(obj.getReorderLevel());
            prod.setDiscontinued(obj.getDiscontinued());
            repo.flush();
            return obj;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }
    
    @Override
    public Products delete(Object id) {
        Products prod = getById(id);
        if (prod != null) {
            repo.delete(prod);
            return prod;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }
    
    @Override
    public Products getById(Object id) {
        return repo.findById(Integer.valueOf(id.toString())).orElse(null);
    }

    @Override
    public List<Products> getAll() {
        return repo.findAll();
    }
    
    public Page<Products> getByPage(int page, int pageSize, String productName, String sort, boolean asc) {
        Sort sortBy = Sort.by(sort);
        sortBy = asc ? sortBy.ascending() : sortBy.descending();
        Pageable pageable = PageRequest.of(page, pageSize, sortBy);
        return repo.findByProductNameLike(productName,  pageable);
    }
}