package com.techie.microservices.menu.infrastructure.common;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IRepositoryBase<T, K extends Serializable> {
    Optional<T> findById(K id);
    List<T> findAll();
    T save(T entity);
    List<T> saveAll(List<T> entities);
    void update(T entity);
    void updateAll(List<T> entities);
    void delete(T entity);
    void deleteById(K id);
    void deleteAll(List<T> entities);
}
