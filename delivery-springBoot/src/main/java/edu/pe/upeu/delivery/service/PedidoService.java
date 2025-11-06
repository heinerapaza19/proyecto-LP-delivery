package edu.pe.upeu.delivery.service;

import edu.pe.upeu.delivery.DTOs.PedidoDTO;

import java.util.List;

public interface PedidoService {
    List<PedidoDTO> listarPedidos();
    PedidoDTO obtenerPorId(Long id);
    PedidoDTO crearPedidoDesdeCarrito(Long idCliente);
    PedidoDTO actualizarEstado(Long idPedido, String nuevoEstado);
    void eliminarPedido(Long idPedido);
}
