package gdf.service;

import gdf.dto.ProductoCreationDTO;
import gdf.dto.ProductoDTO;
import gdf.entity.Producto;
import gdf.exception.ResourceNotFoundException;
import gdf.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Para manejo transaccional

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired // Inyección de dependencia del repositorio
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Crea un nuevo producto en la base de datos.
     * @param dto El DTO con los datos del nuevo producto.
     * @return El DTO del producto creado con su ID.
     */
    @Transactional // Indica que este metodo debe ejecutarse dentro de una transacción de base de datos
    public ProductoDTO createProducto(ProductoCreationDTO dto) {
        Producto producto = new Producto(dto.getNombre(), dto.getPrecio(), dto.getStock());
        Producto savedProducto = productoRepository.save(producto); // Guarda el producto en la DB
        return new ProductoDTO(savedProducto.getId(), savedProducto.getNombre(), savedProducto.getPrecio(), savedProducto.getStock());
    }

    /**
     * Obtiene un producto por su ID.
     * @param id El ID del producto.
     * @return El DTO del producto encontrado.
     * @throws ResourceNotFoundException si el producto no se encuentra.
     */
    @Transactional(readOnly = true) // Transacción de solo lectura, optimizada para consultas
    public ProductoDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id) // Busca por ID
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "ID", id)); // Lanza excepción si no existe
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

    /**
     * Obtiene todos los productos de la base de datos.
     * @return Una lista de DTOs de productos.
     */
    @Transactional(readOnly = true)
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll().stream() // Obtiene todos los productos
                .map(p -> new ProductoDTO(p.getId(), p.getNombre(), p.getPrecio(), p.getStock())) // Convierte cada entidad a DTO
                .collect(Collectors.toList()); // Recolecta en una lista
    }

    /**
     * Actualiza un producto existente.
     * @param id El ID del producto a actualizar.
     * @param dto El DTO con los nuevos datos del producto.
     * @return El DTO del producto actualizado.
     * @throws ResourceNotFoundException si el producto no se encuentra.
     */
    @Transactional
    public ProductoDTO updateProducto(Long id, ProductoCreationDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "ID", id));

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
    }

    /**
     * Elimina un producto por su ID.
     * @param id El ID del producto a eliminar.
     * @throws ResourceNotFoundException si el producto no se encuentra.
     */
    @Transactional
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto", "ID", id);
        }
        productoRepository.deleteById(id); // Elimina el producto
    }
}
