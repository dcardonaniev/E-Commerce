package it.bootcamp.proyectoecommerce.service;

import it.bootcamp.proyectoecommerce.dto.request.UserRequest;
import it.bootcamp.proyectoecommerce.dto.response.UserResponse;
import it.bootcamp.proyectoecommerce.entity.User;
import it.bootcamp.proyectoecommerce.exception.NotFoundException;
import it.bootcamp.proyectoecommerce.repository.IRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IService<UserRequest, UserResponse, Long> {
    private final IRepository<User, Long> userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(
            ModelMapper modelMapper,
            IRepository<User, Long> userRepository
    ){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserResponse create(UserRequest entity) {
        User newUser = modelMapper.map(entity, User.class);

        return modelMapper
                .map(userRepository.create(newUser), UserResponse.class);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.getAll().stream()
                .map((v)-> modelMapper.map(v, UserResponse.class))
                .toList();
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> usuario =  userRepository.findById(id);
        if(usuario.isEmpty()){
            throw new NotFoundException("User not found");
        }

        return modelMapper.map(usuario.get(), UserResponse.class);
    }

    @Override
    public UserResponse update(UserRequest entity, Long id) {
        User usuario = modelMapper.map(entity, User.class);
        usuario.setId(id);

        User newUsuario = userRepository.update(usuario);
        if(newUsuario == null){
            throw new NotFoundException("User not found");
        }

        return modelMapper.map(newUsuario, UserResponse.class);
    }

    @Override
    public void delete(Long id) {
         userRepository.delete(id);
    }
}
