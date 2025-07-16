package gdf.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaPedido> lineasPedido = new ArrayList<>(); // Inicializa la lista para evitar NullPointerExceptions
    private double costoTotal;

    // METODOS
    public boolean agregarLinea(Producto producto, int cantidad) {
        if (producto.getStock() >= cantidad) {
            LineaPedido nuevaLinea = new LineaPedido(producto, cantidad);
            nuevaLinea.setPedido(this); // referencia bidireccional desde la línea hacia este pedido
            this.lineasPedido.add(nuevaLinea);
            producto.setStock(producto.getStock() - cantidad); // Actualiza el stock del producto
            recalcularCostoTotal(); // Recalcula el costo total del pedido
            return true;
        } else {
            System.out.println("No hay suficiente stock de " + producto.getNombre() + ". Stock actual: " + producto.getStock());
            return false;
        }
    }

    private void recalcularCostoTotal() {
        this.costoTotal = 0.0;
        for (LineaPedido linea : lineasPedido) {
            this.costoTotal += linea.getSubtotal();
        }
    }

    public void listar() {
        System.out.println("╔═══════════════════════════════════════");
        System.out.printf( "║ Pedido ID: %d\n", this.id);
        System.out.println("║ Productos:");
        if (lineasPedido.isEmpty()) {
            System.out.println("║   (Pedido vacío)");
        } else {
            for (LineaPedido linea : lineasPedido) linea.listar();
        }
        System.out.printf( "║ Costo Total: %.2f\n", this.costoTotal);
        System.out.println("╚═══════════════════════════════════════");
    }
}
