package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaginaController {

    @Autowired
    private ProductosService productoService;

    @GetMapping("/")
    public String mostrarPaginaPrincipal(Model model) {
        model.addAttribute("listaProductos", productoService.obtenerMasVendidos());
        return "PaginaPrincipal";
    }

    @GetMapping("/categoria/{nombre}")
    public String mostrarCategoria(@PathVariable("nombre") String nombre, Model model) {
        model.addAttribute("categoria", nombre);
        model.addAttribute("listaProductos", productoService.obtenerPorCategoria(nombre));
        return "categoria"; // crea categoria.html como plantilla genérica
    }
}

