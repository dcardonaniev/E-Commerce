package it.bootcamp.proyectoecommerce.repository;

import it.bootcamp.proyectoecommerce.entity.User;
import it.bootcamp.proyectoecommerce.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IRepository<User, Long> {
    private final List<User> users = new ArrayList<>();
    private Long id = 0L;

    @Override
    public User create(User entity) {
        entity.setId(id++);
        this.users.add(entity);
        Optional<User> newClient = this.findById(entity.getId());
        return newClient.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public User update(User entity) {
        Optional<User> found = this.findById(entity.getId());
        if(found.isEmpty()){ return null; }
        users.set(users.indexOf(found.get()), entity);
        return this.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = this.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        users.remove(user.get());
    }
}
