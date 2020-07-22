/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.service;
import com.northwind.jpa.entity.Users;
import com.northwind.jpa.repository.UsersRepository;
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
public class UserService implements RepoService<Users>{
    @PersistenceContext
    private EntityManager em;

    @Resource
    private UsersRepository repo;

    @Override
    public Users create(Users obj) {
        Users use = repo.save(obj);
        return use;
    }
    
    @Override
    public Users update(Users obj) {
        Users use = repo.findById(obj.getUserid()).orElse(null);
        if (use != null) { 
            use.setName(obj.getName());
            use.setTitle(obj.getTitle());
            use.setBirthdate(obj.getBirthdate());
            use.setHiredate(obj.getHiredate());
            use.setPosition(obj.getPosition());
            use.setEmail(obj.getEmail());
            use.setPassword(obj.getPassword());
            repo.flush();
            return obj;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }
    
    @Override
    public Users delete(Object id) {
        Users use = repo.findById(id.toString()).orElse(null);
        if (use != null) {
            repo.delete(use);
            return use;
        } else {
            throw new NoSuchElementException("Data not found!");
        }
    }
    
    @Override
    public Users getById(Object id) {
        Users use = repo.findById(id.toString()).orElse(null);
        return use;
    }

    @Override
    public List<Users> getAll() {
        return repo.findAll();
    }
    
    public Page<Users> getByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return repo.findAll(pageable);
    }
}
