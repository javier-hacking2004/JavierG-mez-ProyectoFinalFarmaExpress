package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.CategoriaService;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "formulario-producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.save(producto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.findAll());
        return "formulario-producto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return "redirect:/";
    }
}
