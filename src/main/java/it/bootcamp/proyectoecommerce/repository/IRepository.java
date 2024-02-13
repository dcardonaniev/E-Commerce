package it.bootcamp.proyectoecommerce.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IRepository <T, ID> {
    public T create(T entity);
    public List<T> getAll();
    public Optional<T> findById(ID id);
    public T update(T entity);
    public void delete(ID id);
}
