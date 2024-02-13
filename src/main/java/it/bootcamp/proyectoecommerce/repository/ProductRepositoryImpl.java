package it.bootcamp.proyectoecommerce.repository;

import it.bootcamp.proyectoecommerce.entity.Product;
import it.bootcamp.proyectoecommerce.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements IRepository<Product, Long> {
    private final List<Product> products = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Product create(Product entity) {
        entity.setId(id++);
        this.products.add(entity);
        Optional<Product> newProduct = this.findById(entity.getId());
        return newProduct.orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Product update(Product entity) {
        Optional<Product> found = this.findById(entity.getId());
        if(found.isEmpty()){
            throw new NotFoundException("Product not found");
        }

        products.set(products.indexOf(found.get()), entity);
        return this.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Product found = this.findById(id).orElse(null);
        if(found == null){
            throw new NotFoundException("Product not found");
        }
        products.remove(found);
    }
}
