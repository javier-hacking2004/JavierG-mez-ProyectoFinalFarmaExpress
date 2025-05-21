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

    @Column(name = "TEXTO_ALTERNATIVO")
    private String textoAlternativo;

    private double precio;

    @Column(name = "MAS_VENDIDO")
    private boolean masVendido;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // MÉTODOS HELPER el objeto principal en mi caso es producto

    public boolean esCaro() {
        return this.precio > 50.0;
    }

    public String getEtiquetaProducto() {
        return String.format("%s - %.2f € sin IVA", this.nombre, this.precio);
    }   

    public double getPrecioConIVA(double porcentaje) {
        return Math.round(this.precio * (1 + porcentaje / 100.0) * 100.0) / 100.0;
    }

    public boolean esMasVendido() {
        return this.masVendido;
    }
}
