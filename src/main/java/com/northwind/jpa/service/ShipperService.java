/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;
import com.northwind.jpa.entity.Shippers;
import com.northwind.jpa.repository.ShippersRepository;
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
public class ShipperService implements RepoService<Shippers>{
    @PersistenceContext
    private EntityManager em;

    @Resource
    private ShippersRepository repo;

    @Override
    public Shippers create(Shippers obj) {
        Shippers ship = repo.save(obj);
        return ship;
    }
    
    @Override
    public Shippers update(Shippers obj) {
        Shippers ship = repo.findById(obj.getShipperID()).orElse(null);
        if (ship != null) { 
            ship.setCompanyName(obj.getCompanyName());
            ship.setPhone(obj.getPhone());
            repo.flush();
            return obj;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }
    
    @Override
    public Shippers delete(Object id) {
        Shippers ship = repo.findById(id.toString()).orElse(null);
        if (ship != null) {
            repo.delete(ship);
            return ship;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }
    
    @Override
    public Shippers getById(Object id) {
        Shippers ship = repo.findById(id.toString()).orElse(null);
        return ship;
    }

    @Override
    public List<Shippers> getAll() {
        return repo.findAll();
    }
    
    public Page<Shippers> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return repo.findAll(pageable);
    }
}
