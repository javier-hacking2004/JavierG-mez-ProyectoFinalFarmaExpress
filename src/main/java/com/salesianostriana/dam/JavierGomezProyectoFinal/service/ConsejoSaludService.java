package com.salesianostriana.dam.JavierGomezProyectoFinal.service;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.ConsejoSalud;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.ConsejoSaludRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsejoSaludService {

private final ConsejoSaludRepository repo;

public ConsejoSaludService(ConsejoSaludRepository repo) {
    this.repo = repo;
}

public List<ConsejoSalud> findByCategoria(String nombreCategoria) {
    return repo.findByCategoriaNombre(nombreCategoria);
}

public void guardar(ConsejoSalud consejo) {
    repo.save(consejo);
}

public Optional<ConsejoSalud> buscarPorId(Long id) {
    return repo.findById(id);
}

public void eliminar(Long id) {
    repo.deleteById(id);
   }
}