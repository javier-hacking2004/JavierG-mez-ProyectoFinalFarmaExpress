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

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(String nombreCategoria) {
        return productoRepository.findByCategoriaNombreIgnoreCase(nombreCategoria);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}