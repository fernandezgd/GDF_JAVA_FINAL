package gdf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;

    private String nombre;
    private double precio;
    private int stock;
}

// Este DTO se usa para enviar datos del producto desde la API (incluye el ID).
