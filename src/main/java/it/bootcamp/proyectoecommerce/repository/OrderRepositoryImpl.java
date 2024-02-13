package it.bootcamp.proyectoecommerce.repository;

import it.bootcamp.proyectoecommerce.entity.Order;
import it.bootcamp.proyectoecommerce.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements IRepository<Order, Long> {
    private final List<Order> orders = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Order create(Order entity) {
        entity.setId(id++);
        this.orders.add(entity);
        Optional<Order> newOrder = this.findById(entity.getId());
        return newOrder.orElse(null);
    }

    @Override
    public List<Order> getAll() {
        System.out.println(this.orders);
        return this.orders;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Order update(Order entity) {
        Optional<Order> found = this.findById(entity.getId());
        if(found.isEmpty()){
            throw new NotFoundException("Order not found");
        }

        orders.set(orders.indexOf(found.get()), entity);
        return this.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Optional<Order> order = this.findById(id);
        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }

        orders.remove(order.get());
    }
}
