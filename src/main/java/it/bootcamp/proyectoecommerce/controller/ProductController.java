package it.bootcamp.proyectoecommerce.controller;

import it.bootcamp.proyectoecommerce.dto.request.ProductRequest;
import it.bootcamp.proyectoecommerce.dto.response.ProductResponse;
import it.bootcamp.proyectoecommerce.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/product")
public class ProductController {

    private IService<ProductRequest, ProductResponse, Long> productService;

    public ProductController(IService<ProductRequest, ProductResponse, Long> productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return new ResponseEntity<>(this.productService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable long id){
        return new ResponseEntity<>( this.productService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product){
        return new ResponseEntity<>(this.productService.create(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id){
        this.productService.delete(id);
        return new ResponseEntity<>("Todo ok!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long id, @RequestBody ProductRequest product){
        return new ResponseEntity<>(this.productService.update(product, id), HttpStatus.OK);
    }

}
