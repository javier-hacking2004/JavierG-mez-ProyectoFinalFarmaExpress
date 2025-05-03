package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PaginaController {

private final ProductoService productoService;

@GetMapping("/")
public String mostrarInicio(Model model) {
    model.addAttribute("listaProductos", productoService.obtenerMasVendidos());
    return "PaginaPrincipal";
}

@GetMapping("/categoria/{categoria}")
public String productosPorCategoria(@PathVariable String categoria, Model model) {
    model.addAttribute("listaProductos", productoService.obtenerPorCategoria(categoria));
    model.addAttribute("categoriaNombre", categoria);
    return "productosCategoria";
}

@GetMapping("/productos/eliminar/{id}")
public String eliminarProducto(@PathVariable Long id) {
    productoService.eliminarPorId(id);
    return "redirect:/";
    }
}
