package gdf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Genera el constructor vacío necesario para JPA y Spring Boot
@Entity // Marca esta clase como una entidad persistente de JPA
public class Producto {

    @Id // Marca 'id' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia para generar el ID (autoincremental)
    private Long id;

    private String nombre;
    private double precio;
    private int stock;

    // Constructor para la creación de nuevos productos
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // METODOS
    public void listar(){
        System.out.printf("│  %s │ %s - %s - %s \n", this.id, this.nombre, this.precio, this.stock);
    }

    boolean buscarNombre(String busqueda){
        String nombreEnMinusculas = this.nombre.toLowerCase();
        String busquedaEnMinusculas = busqueda.toLowerCase();
        return nombreEnMinusculas.contains(busquedaEnMinusculas);
    }
}
