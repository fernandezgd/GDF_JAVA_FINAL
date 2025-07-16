package gdf.controller;

import gdf.dto.ProductoCreationDTO;
import gdf.dto.ProductoDTO;
import gdf.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marca esta clase como un controlador REST de Spring
@RequestMapping("/api/productos") // Define la ruta base para todos los endpoints de este controlador
public class ProductoController {
    private final ProductoService productoService;

    @Autowired // Inyección de dependencia del servicio
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Endpoint para crear un nuevo producto.
     * POST /api/productos
     * @param productoDto DTO con los datos del producto a crear (JSON en el cuerpo de la petición).
     * @return ResponseEntity con el ProductoDTO creado y estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoCreationDTO productoDto) {
        ProductoDTO createdProducto = productoService.createProducto(productoDto);
        return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener un producto por su ID.
     * GET /api/productos/{id}
     * @param id El ID del producto.
     * @return ResponseEntity con el ProductoDTO encontrado y estado HTTP 200 (OK).
     * Retorna 404 (Not Found) si el producto no existe (manejado por ResourceNotFoundException).
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
        ProductoDTO producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    /**
     * Endpoint para obtener todos los productos.
     * GET /api/productos
     * @return ResponseEntity con una lista de ProductoDTOs y estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        List<ProductoDTO> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    /**
     * Endpoint para actualizar un producto existente.
     * PUT /api/productos/{id}
     * @param id El ID del producto a actualizar.
     * @param productoDto DTO con los datos actualizados del producto.
     * @return ResponseEntity con el ProductoDTO actualizado y estado HTTP 200 (OK).
     * Retorna 404 (Not Found) si el producto no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoCreationDTO productoDto) {
        ProductoDTO updatedProducto = productoService.updateProducto(id, productoDto);
        return ResponseEntity.ok(updatedProducto);
    }

    /**
     * Endpoint para eliminar un producto por su ID.
     * DELETE /api/productos/{id}
     * @param id El ID del producto a eliminar.
     * @return ResponseEntity sin contenido y estado HTTP 204 (No Content) si se eliminó correctamente.
     * Retorna 404 (Not Found) si el producto no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
