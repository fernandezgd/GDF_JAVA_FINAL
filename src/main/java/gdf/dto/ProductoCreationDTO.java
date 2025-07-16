package gdf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreationDTO {
    private String nombre;
    private double precio;
    private int stock;
}

// Este DTO se usa para recibir datos cuando se crea o actualiza un producto (no incluye el ID, ya que la base de datos lo genera).
