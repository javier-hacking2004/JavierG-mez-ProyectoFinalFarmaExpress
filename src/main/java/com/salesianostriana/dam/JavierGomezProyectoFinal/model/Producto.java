package com.salesianostriana.dam.JavierGomezProyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagenUrl;
    private double precio;
    private String categoria;
    private boolean masVendido;
}



