package it.bootcamp.proyectoecommerce.service;

import it.bootcamp.proyectoecommerce.dto.request.OrderRequest;
import it.bootcamp.proyectoecommerce.dto.request.ProductItemRequest;
import it.bootcamp.proyectoecommerce.dto.response.OrderResponse;
import it.bootcamp.proyectoecommerce.entity.Order;
import it.bootcamp.proyectoecommerce.entity.Product;
import it.bootcamp.proyectoecommerce.entity.ProductItem;
import it.bootcamp.proyectoecommerce.entity.User;
import it.bootcamp.proyectoecommerce.exception.NotFoundException;
import it.bootcamp.proyectoecommerce.repository.IRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IService<OrderRequest, OrderResponse, Long >{
    private final ModelMapper modelMapper;
    private final IRepository<Order, Long> orderRepository;
    private final IRepository<User, Long> userRepository;
    private final IRepository<Product, Long> productRepository;

    public OrderServiceImpl(
            ModelMapper modelMapper,
            IRepository<Order, Long> orderRepository,
            IRepository<User, Long> userRepository,
            IRepository<Product, Long> productRepository
    ) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponse create(OrderRequest entity) {
        Optional<User> user = userRepository.findById(entity.getUserId());
        if(user.isEmpty())
            throw new NotFoundException("User not found");

        List<ProductItem> productItems = entity.getProductItems()
                .stream()
                .map(pi -> {
                    Optional<Product> product = productRepository.findById(pi.getProductId());
                    if(product.isEmpty())
                        throw new NotFoundException(
                                String.format(
                                        "Product with id %d does not exist",
                                        pi.getProductId()
                                )
                        );

                    return new ProductItem(
                            product.get(),
                            pi.getQuantity()
                    );
                })
                .toList();

        Order newOrder = new Order();
        newOrder.setProductItems(productItems);
        newOrder.setUser(user.get());

        return modelMapper.map(orderRepository.create(newOrder), OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderRepository.getAll()
                .stream()
                .map(o -> modelMapper.map(o, OrderResponse.class))
                .toList();
    }

    @Override
    public OrderResponse findById(Long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        if(order.isEmpty()){
            throw new NotFoundException("Order not found");
        }

        return modelMapper.map(order.get(), OrderResponse.class);
    }

    @Override
    public OrderResponse update(OrderRequest entity, Long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        if(order.isEmpty())
            throw new NotFoundException("Order not found");

        Optional<User> user = userRepository.findById(entity.getUserId());
        if(user.isEmpty())
            throw new NotFoundException("User not found");

        for (ProductItemRequest productItem: entity.getProductItems()){
            Optional<Product> product = this.productRepository.findById(productItem.getProductId());
            if(product.isEmpty())
                throw new NotFoundException(String.format("Product with id %d does not exist", productItem.getProductId()));
        }

        List<ProductItem> productItems = entity.getProductItems().stream().map((pi)-> modelMapper.map(pi, ProductItem.class)).toList();

        Order newOrder = new Order();
        newOrder.setProductItems(productItems);
        newOrder.setUser(order.get().getUser());

        return modelMapper.map(orderRepository.update(newOrder), OrderResponse.class);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
