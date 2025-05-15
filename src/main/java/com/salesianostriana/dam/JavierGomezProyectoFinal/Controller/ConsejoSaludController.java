package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Categoria;
import com.salesianostriana.dam.JavierGomezProyectoFinal.model.ConsejoSalud;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.CategoriaRepository;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ConsejoSaludService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/consejos")
@RequiredArgsConstructor
public class ConsejoSaludController {

private final ConsejoSaludService consejoService;
private final CategoriaRepository categoriaRepository;

// Mostrar formulario para crear consejo de salud
@GetMapping("/nuevo/{categoriaId}")
public String mostrarFormularioNuevoConsejo(@PathVariable Long categoriaId, Model model) {
    Optional<Categoria> categoriaOpt = categoriaRepository.findById(categoriaId);
    if (categoriaOpt.isPresent()) {
        ConsejoSalud consejo = new ConsejoSalud();
        consejo.setCategoria(categoriaOpt.get());
        model.addAttribute("consejo", consejo);
        return "formulario-consejo";
    } else {
        return "redirect:/"; // Si la categoría no existe
    }
}

// Guardar consejo de salud
@PostMapping("/guardar")
public String guardarConsejo(@ModelAttribute ConsejoSalud consejo) {
    consejoService.guardar(consejo);
    return "redirect:/categorias/" + consejo.getCategoria().getNombre();
}

// Eliminar consejo de salud
@GetMapping("/eliminar/{id}")
public String eliminarConsejo(@PathVariable Long id) {
    Optional<ConsejoSalud> consejoOpt = consejoService.buscarPorId(id);
    if (consejoOpt.isPresent()) {
        String categoriaNombre = consejoOpt.get().getCategoria().getNombre();
        consejoService.eliminar(id);
        return "redirect:/categorias/" + categoriaNombre;
    } else {
        return "redirect:/";
    }
  }
}