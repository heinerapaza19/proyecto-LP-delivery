package edu.pe.upeu.delivery.DTOs;


import lombok.Data;

@Data
public class ClienteDTO {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion; // breve descripción o nombre de la dirección
}

