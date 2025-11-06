package edu.pe.upeu.delivery.repository;

import edu.pe.upeu.delivery.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Ejemplo: buscar por categoría o nombre
    // List<Producto> findByCategoriaNombre(String nombre);
}
