package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.CarritoItem;
import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.CategoriaService;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.CarritoService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final CarritoService carritoService;

    public MainController(ProductoService productoService, CategoriaService categoriaService, CarritoService carritoService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.carritoService = carritoService;
    }

    public void cargarDatosCarrito(Model model) {
        List<CarritoItem> carrito = carritoService.obtenerCarrito();
        int totalCantidad = carrito.stream().mapToInt(CarritoItem::getCantidad).sum();
        model.addAttribute("carrito", carrito);
        model.addAttribute("carritoCantidadTotal", totalCantidad);
    }

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("listaProductos", productoService.obtenerMasVendidos());
        cargarDatosCarrito(model);
        return "PaginaPrincipal";
    }

    @GetMapping("/categoria/{categoria}")
    public String productosPorCategoria(@PathVariable String categoria, Model model) {
        model.addAttribute("listaProductos", productoService.buscarPorCategoria(categoria));
        cargarDatosCarrito(model);

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
        cargarDatosCarrito(model);
        return "resultadosBusqueda";
    }

    @GetMapping("/quienes-somos")
    public String mostrarQuienesSomos(Model model) {
        cargarDatosCarrito(model);
        return "quienes-somos";
    }

    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(Model model) {
        model.addAttribute("estadisticas", categoriaService.listarCategoriasConConteoDeProductos());
        cargarDatosCarrito(model);
        return "estadisticas";
    }
}