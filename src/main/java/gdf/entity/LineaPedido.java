package gdf.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LineaPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación ManyToOne con Producto:
    // Muchas líneas de pedido pueden apuntar al mismo producto.
    // @JoinColumn define la columna de clave foránea en la tabla LineaPedido.
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private String nombreProducto; // Almaceno el nombre del producto al momento del pedido
    private double precioUnitario; // Almaceno el precio del producto al momento del pedido
    private int cantidad;
    private double subtotal;

    public LineaPedido(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo al crear una línea de pedido.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de la línea de pedido debe ser positiva.");
        }
        this.producto = producto;
        this.nombreProducto = producto.getNombre(); // Asigna el nombre del producto aquí
        this.precioUnitario = producto.getPrecio(); // Asigna el precio del producto aquí
        this.cantidad = cantidad;
        this.subtotal = this.precioUnitario * this.cantidad;
    }


    // METODOS

    public void listar() {
        System.out.printf("║  - %s x %d uds. (Subt: %.2f) \n",
                producto.getNombre(), cantidad, getSubtotal());
    }

    // Relación ManyToOne con Pedido:
    // Esta es la parte "propietaria" de la relación bidireccional con Pedido, para que JPA pueda mapear correctamente la relación.
    @ManyToOne
    @JoinColumn(name = "pedido_id") // Columna FK en la tabla linea_pedido que apunta a pedido
    private Pedido pedido; // Referencia al pedido al que pertenece esta línea
}
