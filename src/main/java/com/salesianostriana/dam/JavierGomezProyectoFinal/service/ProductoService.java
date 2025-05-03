package com.salesianostriana.dam.JavierGomezProyectoFinal.service;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.Productorepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

private final Productorepository productoRepository;

public List<Producto> obtenerMasVendidos() {
    return productoRepository.findByMasVendidoTrue();
}

public List<Producto> obtenerPorCategoria(String categoria) {
    return productoRepository.findByCategoriaIgnoreCase(categoria);
}

public List<Producto> obtenerTodos() {
    return productoRepository.findAll();
}

public void eliminarPorId(Long id) {
    productoRepository.deleteById(id);
}

public void guardarProducto(Producto p) {
    productoRepository.save(p);
}
}