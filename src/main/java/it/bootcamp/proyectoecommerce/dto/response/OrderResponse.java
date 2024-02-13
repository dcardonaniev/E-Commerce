package it.bootcamp.proyectoecommerce.dto.response;

import it.bootcamp.proyectoecommerce.entity.User;
import it.bootcamp.proyectoecommerce.entity.ProductItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private long id;
    private User user;
    private List<ProductItem> items;
}
