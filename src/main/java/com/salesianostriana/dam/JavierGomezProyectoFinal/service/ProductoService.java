package com.salesianostriana.dam.JavierGomezProyectoFinal.service;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.Productorepository;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService extends BaseServiceImpl<Producto, Long, Productorepository> {

    public List<Producto> obtenerMasVendidos() {
        return repository.findByMasVendidoTrue();
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(String nombreCategoria) {
        return repository.findByCategoriaNombreIgnoreCase(nombreCategoria);
    }
}