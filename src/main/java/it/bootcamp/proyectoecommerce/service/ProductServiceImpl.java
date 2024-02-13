package it.bootcamp.proyectoecommerce.service;

import it.bootcamp.proyectoecommerce.dto.request.ProductRequest;
import it.bootcamp.proyectoecommerce.dto.response.ProductResponse;
import it.bootcamp.proyectoecommerce.entity.Product;
import it.bootcamp.proyectoecommerce.exception.NotFoundException;
import it.bootcamp.proyectoecommerce.repository.IRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IService<ProductRequest, ProductResponse, Long> {
    private final IRepository<Product, Long> productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(
            ModelMapper modelMapper,
            IRepository<Product, Long> productRepository
    ) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse create(ProductRequest entity) {
        Product newProduct = modelMapper.map(entity, Product.class);

        return modelMapper
                .map(productRepository.create(newProduct), ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.getAll()
                .stream()
                .map(p -> modelMapper.map(p, ProductResponse.class))
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new NotFoundException("Product not found");

        return modelMapper.map(product.get(), ProductResponse.class);
    }

    @Override
    public ProductResponse update(ProductRequest entity, Long id) {
        Product product = modelMapper.map(entity, Product.class);
        product.setId(id);

        return modelMapper.map(productRepository.update(product), ProductResponse.class);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
