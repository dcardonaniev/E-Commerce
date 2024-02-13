package it.bootcamp.proyectoecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private int stock;
}
