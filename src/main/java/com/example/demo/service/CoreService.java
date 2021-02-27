package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CoreService<Rep extends JpaRepository<T, ID>, T, ID> {
    protected Rep rep;

    public CoreService(Rep rep) {
        this.rep = rep;
    }

    public T save(T t) {
        return this.rep.save(t);
    }

    /*public Set<T> getList(){
        return new HashSet<>(this.rep.findAll());
    }*/

    public List<T> getList() {
        return this.rep.findAll();
    }
}
