// Este servicio maneja un carrito de compra temporal con tiempo limitado.
// No usamos Repository porque no estamos guardando el carrito en la base de datos.

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

    public double calcularTotal() {
        return carrito.stream().mapToDouble(CarritoItem::getSubtotal).sum();
    }

    public double calcularEnvio() {
        return calcularTotal() >= 30 ? 0.0 : 3.95;
    }

    public double calcularTotalConEnvio() {
        return calcularTotal() + calcularEnvio();
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

