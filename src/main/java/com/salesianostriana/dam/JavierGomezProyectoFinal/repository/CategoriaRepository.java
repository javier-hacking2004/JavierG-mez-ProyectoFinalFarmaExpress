package com.salesianostriana.dam.JavierGomezProyectoFinal.repository;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c.nombre, COUNT(p) FROM Categoria c LEFT JOIN c.productos p GROUP BY c.nombre ORDER BY COUNT(p) DESC")
    List<Object[]> findCategoriasConConteoDeProductos();

}
