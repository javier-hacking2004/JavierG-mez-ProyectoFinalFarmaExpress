package com.salesianostriana.dam.JavierGomezProyectoFinal.repository;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Productorepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByCategoriaNombreIgnoreCase(String nombre);

    List<Producto> findByMasVendidoTrue();
}
