package edu.pe.upeu.delivery.controller;


import edu.pe.upeu.delivery.DTOs.UsuarioDTO;
import edu.pe.upeu.delivery.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public UsuarioDTO guardar(@RequestBody UsuarioDTO dto) {
        return service.guardarUsuario(dto);
    }

    @PutMapping("/{id}")
    public UsuarioDTO actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        return service.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarUsuario(id);
    }

    @GetMapping("/buscar/{username}")
    public UsuarioDTO buscarPorUsername(@PathVariable String username) {
        return service.buscarPorUsername(username);
    }
}
