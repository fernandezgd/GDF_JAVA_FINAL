package gdf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineaPedidoDTO {
    private Long productoId;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;
    private double subtotal;
}