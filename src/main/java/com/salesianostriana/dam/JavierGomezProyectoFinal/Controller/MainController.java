package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductoService productoService;

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("listaProductos", productoService.obtenerMasVendidos());
        return "PaginaPrincipal";
    }

    // Cada categoría tiene su propio HTML
    @GetMapping("/categoria/{categoria}")
    public String productosPorCategoria(@PathVariable String categoria, Model model) {
        model.addAttribute("listaProductos", productoService.buscarPorCategoria(categoria));

        switch (categoria) {
        case "Solares":
            return "ProductosSolar";
        case "CuidadosFaciales":
            return "ProductosFaciales";
        case "Corporales":
            return "ProductosCorporales";
        case "HigieneDental":
            return "ProductosHigieneDental";
        default:
            return "error/404";
      }
  }

  @GetMapping("/buscar")
    public String buscarProductoPorNombre(@RequestParam("nombre") String nombre, Model model) {
    List<Producto> resultados = productoService.buscarPorNombre(nombre);
    model.addAttribute("listaProductos", resultados);
    model.addAttribute("busqueda", nombre); 
    return "resultadosBusqueda";
  }

  @GetMapping("/quienes-somos")
    public String mostrarQuienesSomos() {
        return "quienes-somos";
    }
}

