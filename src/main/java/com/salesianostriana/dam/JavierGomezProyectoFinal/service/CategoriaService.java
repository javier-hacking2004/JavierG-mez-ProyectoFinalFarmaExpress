package com.salesianostriana.dam.JavierGomezProyectoFinal.service;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Categoria;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.CategoriaRepository;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.base.BaseServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends BaseServiceImpl<Categoria, Long, CategoriaRepository> {

    public List<Object[]> listarCategoriasConConteoDeProductos() {
        return repository.findCategoriasConConteoDeProductos();
    }

}
