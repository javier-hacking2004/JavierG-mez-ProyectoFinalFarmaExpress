package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Categoria;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepo;

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formulario-categoria";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaRepo.save(categoria);
        return "redirect:/";
    }
}

