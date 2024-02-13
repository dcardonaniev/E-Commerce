package it.bootcamp.proyectoecommerce.controller;

import it.bootcamp.proyectoecommerce.dto.request.OrderRequest;
import it.bootcamp.proyectoecommerce.dto.response.OrderResponse;
import it.bootcamp.proyectoecommerce.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/order")
public class OrderController {

    private IService<OrderRequest, OrderResponse, Long> orderService;

    public OrderController(IService<OrderRequest, OrderResponse, Long> orderService){
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> getOrders(){
        return new ResponseEntity<>(this.orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable long id){
        return new ResponseEntity<>( this.orderService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest order){
        return new ResponseEntity<>(this.orderService.create(order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable long id){
        this.orderService.delete(id);
        return new ResponseEntity<>("Todo ok!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable long id, @RequestBody OrderRequest order){
        return new ResponseEntity<>(this.orderService.update(order, id), HttpStatus.OK);
    }
}
