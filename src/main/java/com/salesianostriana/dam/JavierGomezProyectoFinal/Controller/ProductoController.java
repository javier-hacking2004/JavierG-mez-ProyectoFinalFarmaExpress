package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.CategoriaService;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @Autowired
    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "FormularioNuevoProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            producto.setCategoria(categoriaService.findById(producto.getCategoria().getId()).orElse(null));
        }
        productoService.save(producto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.findAll());
        return "FormularioEditarProducto";
    }

    @PostMapping("/editar")
    public String guardarCambios(@ModelAttribute Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            producto.setCategoria(categoriaService.findById(producto.getCategoria().getId()).orElse(null));
        }

        productoService.save(producto);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/contar")
    @ResponseBody
    public String contarProductosPorCategoria(@RequestParam String categoria) {
        long total = productoService.contarPorCategoria(categoria);
        return "Total de productos en la categoría '" + categoria + "': " + total;
    }
}