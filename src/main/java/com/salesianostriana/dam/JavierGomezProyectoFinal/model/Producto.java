package com.salesianostriana.dam.JavierGomezProyectoFinal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCTO")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    private double precio;
    private String categoria;

    @Column(name = "MAS_VENDIDO")
    private boolean masVendido;
}




