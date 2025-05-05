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

    // Cada categoría tiene su propio HTML
    @GetMapping("/categoria/{categoria}")
    public String productosPorCategoria(@PathVariable String categoria, Model model) {
        model.addAttribute("listaProductos", productoService.obtenerPorCategoria(categoria));

        // Elegimos plantilla según categoría exacta
        switch (categoria.toLowerCase()) {
            case "solares":
                return "ProductosSolar";
            case "faciales":
                return "ProductosFaciales";
            case "corporales":
                return "ProductosCorporales";
            case "higienedental":
                return "ProductosHigieneDental";
            default:
                return "error/404"; // O redirige a una vista genérica si no existe
        }
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarPorId(id);
        return "redirect:/";
    }
}
