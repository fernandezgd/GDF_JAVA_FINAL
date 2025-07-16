package gdf.repository;

import gdf.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Spring Data JPA automáticamente implementa métodos CRUD básicos (save, findById, findAll, deleteById, etc.)
}

