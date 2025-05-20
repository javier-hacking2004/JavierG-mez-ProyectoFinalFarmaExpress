package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Categoria;
import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.repository.CategoriaRepository;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepo;
    private final ProductoService productoService;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepo, ProductoService productoService) {
        this.categoriaRepo = categoriaRepo;
        this.productoService = productoService;
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "FormularioNuevaCategoria.html";
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
        return nombre.toLowerCase(); 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaRepo.deleteById(id);
        return "redirect:/categorias/listado";
    }

    @GetMapping("/listado")
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaRepo.findAll();
        model.addAttribute("categorias", categorias);
        return "listado-categorias";
    }
}