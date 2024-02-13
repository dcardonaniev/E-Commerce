package it.bootcamp.proyectoecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemRequest {
    private Long productId;
    private int quantity;
}
