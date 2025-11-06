package edu.pe.upeu.delivery.controller;


import edu.pe.upeu.delivery.DTOs.PedidoDTO;
import edu.pe.upeu.delivery.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoDTO> listar() {
        return service.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping("/crear/{idCliente}")
    public PedidoDTO crearPedido(@PathVariable Long idCliente) {
        return service.crearPedidoDesdeCarrito(idCliente);
    }

    @PutMapping("/{id}/estado/{nuevoEstado}")
    public PedidoDTO actualizarEstado(@PathVariable Long id, @PathVariable String nuevoEstado) {
        return service.actualizarEstado(id, nuevoEstado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarPedido(id);
    }
}
