package com.salesianostriana.dam.JavierGomezProyectoFinal.service;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.Productorepository;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductoService extends BaseServiceImpl<Producto, Long, Productorepository> {

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
}