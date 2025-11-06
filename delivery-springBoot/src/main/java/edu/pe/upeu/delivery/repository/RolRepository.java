package edu.pe.upeu.delivery.repository;

import edu.pe.upeu.delivery.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Optional<Rol> findByNombre(String nombre);
}
