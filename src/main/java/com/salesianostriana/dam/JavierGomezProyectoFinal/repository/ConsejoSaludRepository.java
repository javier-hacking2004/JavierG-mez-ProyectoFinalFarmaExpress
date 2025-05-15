package com.salesianostriana.dam.JavierGomezProyectoFinal.repository;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.ConsejoSalud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsejoSaludRepository extends JpaRepository<ConsejoSalud, Long> {
    List<ConsejoSalud> findByCategoriaNombre(String nombre);
}