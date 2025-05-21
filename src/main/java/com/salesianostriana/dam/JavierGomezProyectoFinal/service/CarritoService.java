// Este servicio es un carrito con tiempo limitado debido que el h2 corre en memoria.
// No uso Repository porque no estoy guardando el carrito en la base de datos.

package com.salesianostriana.dam.JavierGomezProyectoFinal.service;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.CarritoItem;
import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService {

    private final List<CarritoItem> carrito = new ArrayList<>();
    private LocalDateTime ultimaActualizacion = LocalDateTime.now();

    public void addProducto(Producto producto) {
        for (CarritoItem item : carrito) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + 1);
                actualizarFecha();
                return;
            }
        }
        
        carrito.add(new CarritoItem(producto, 1));
        actualizarFecha();
    }

    public void eliminarProducto(Long productoId) {
        carrito.removeIf(item -> item.getProducto().getId().equals(productoId));
        actualizarFecha();
    }

    public List<CarritoItem> obtenerCarrito() {
        return new ArrayList<>(carrito);
    }

    public double calcularTotalConIVA() {
    return carrito.stream()
            .mapToDouble(CarritoItem::getSubtotalConIVA)
            .sum();
}

    public double calcularEnvio() {
        return calcularTotalConIVA() >= 30 ? 0.0 : 3.95;
    }

    public double calcularTotalConEnvio() {
        return calcularTotalConIVA() + calcularEnvio();
    }

    public void vaciarCarrito() {
        carrito.clear();
        actualizarFecha();
    }

    private void actualizarFecha() {
        ultimaActualizacion = LocalDateTime.now();
    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }
}

