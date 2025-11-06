package edu.pe.upeu.delivery.repository;

import edu.pe.upeu.delivery.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Ejemplo: List<Pedido> findByClienteId(Long clienteId);
}
