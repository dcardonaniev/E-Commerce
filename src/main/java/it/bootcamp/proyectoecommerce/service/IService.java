package it.bootcamp.proyectoecommerce.service;

import java.util.List;
import java.util.Optional;

public interface IService <T, V ,ID> {
    public V create(T entity);
    public List<V> getAll();
    public V findById(ID id);
    public V update(T entity, ID id);
    public void delete(ID id);
}
