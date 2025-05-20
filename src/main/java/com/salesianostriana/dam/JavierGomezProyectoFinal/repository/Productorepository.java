package com.salesianostriana.dam.JavierGomezProyectoFinal.repository;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Productorepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByMasVendidoTrue();

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoriaNombreIgnoreCase(String nombreCategoria);

    // Esta consulta hace que cuenta los productos por nombre de categoría y esta en SQL Nativo
    @Query(value = "SELECT COUNT(*) FROM producto p INNER JOIN categoria c ON p.categoria_id = c.id WHERE LOWER(c.nombre) = LOWER(:categoria)", nativeQuery = true)
    long contarPorCategoria(@Param("categoria") String categoria);
}
