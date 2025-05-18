package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Categoria;
import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.CategoriaRepository;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepo;
    private final ProductoService productoService;

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

    @GetMapping("/{nombre}")
    public String mostrarCategoria(@PathVariable String nombre, Model model) {
        List<Producto> productos = productoService.buscarPorCategoria(nombre); 

        model.addAttribute("listaProductos", productos);
        model.addAttribute("categoriaNombre", nombre);

        return nombre.toLowerCase(); // debe coincidir con el HTML
    }
        
}