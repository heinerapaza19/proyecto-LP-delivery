package edu.pe.upeu.delivery.service.impl;


import edu.pe.upeu.delivery.DTOs.PedidoDTO;
import edu.pe.upeu.delivery.mapper.PedidoMapper;
import edu.pe.upeu.delivery.model.*;
import edu.pe.upeu.delivery.repository.*;
import edu.pe.upeu.delivery.service.CarritoService;
import edu.pe.upeu.delivery.service.PedidoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ClienteRepository clienteRepo;
    private final ProductoRepository productoRepo;
    private final DetallePedidoRepository detalleRepo;
    private final CarritoService carritoService;
    private final PedidoMapper mapper;

    public PedidoServiceImpl(PedidoRepository pedidoRepo, ClienteRepository clienteRepo,
                             ProductoRepository productoRepo, DetallePedidoRepository detalleRepo,
                             CarritoService carritoService, PedidoMapper mapper) {
        this.pedidoRepo = pedidoRepo;
        this.clienteRepo = clienteRepo;
        this.productoRepo = productoRepo;
        this.detalleRepo = detalleRepo;
        this.carritoService = carritoService;
        this.mapper = mapper;
    }

    @Override
    public List<PedidoDTO> listarPedidos() {
        return pedidoRepo.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public PedidoDTO obtenerPorId(Long id) {
        return pedidoRepo.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
    }

    @Override
    public PedidoDTO crearPedidoDesdeCarrito(Long idCliente) {
        Cliente cliente = clienteRepo.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE"); // 🔹 Estado inicial
        pedido.setTotal(carritoService.calcularTotal());

        Pedido saved = pedidoRepo.save(pedido);

        // 🔹 Guardar detalles desde el carrito
        for (edu.pe.upeu.delivery.dto.CarritoItemDTO item : carritoService.obtenerCarrito()) {
            Producto producto = productoRepo.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(saved);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setSubtotal(item.getSubtotal());

            detalleRepo.save(detalle);
        }

        carritoService.vaciarCarrito(); // limpia el carrito después de crear el pedido
        return mapper.toDTO(saved);
    }

    @Override
    public PedidoDTO actualizarEstado(Long idPedido, String nuevoEstado) {
        Pedido pedido = pedidoRepo.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + idPedido));
        pedido.setEstado(nuevoEstado);
        return mapper.toDTO(pedidoRepo.save(pedido));
    }

    @Override
    public void eliminarPedido(Long idPedido) {
        pedidoRepo.deleteById(idPedido);
    }
}
