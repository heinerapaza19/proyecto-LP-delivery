package edu.pe.upeu.delivery.mapper;

import edu.pe.upeu.delivery.model.Cliente;
import edu.pe.upeu.delivery.model.Direccion;
import edu.pe.upeu.delivery.DTOs.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente entity) {
        if (entity == null) return null;

        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(entity.getIdCliente());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());

        // ✅ Verificamos que la dirección exista antes de asignarla
        if (entity.getDireccion() != null) {
            dto.setDireccion(entity.getDireccion().getCalle());
        }

        return dto;
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) return null;

        Cliente entity = new Cliente();
        entity.setIdCliente(dto.getIdCliente());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());

        // ✅ Si el DTO tiene dirección, la creamos y asignamos
        if (dto.getDireccion() != null) {
            Direccion direccion = new Direccion();
            direccion.setCalle(dto.getDireccion());
            entity.setDireccion(direccion);
        }

        return entity;
    }
}
