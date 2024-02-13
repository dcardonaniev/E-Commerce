package it.bootcamp.proyectoecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private User user;
    private List<ProductItem> productItems;
}
