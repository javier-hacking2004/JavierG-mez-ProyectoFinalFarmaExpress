package com.salesianostriana.dam.JavierGomezProyectoFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItem {

    private Producto producto;
    private int cantidad;

    public double getSubtotalConIVA() {
        return Math.round(producto.getPrecioConIVA(21) * cantidad * 100.0) / 100.0;
    }
}
