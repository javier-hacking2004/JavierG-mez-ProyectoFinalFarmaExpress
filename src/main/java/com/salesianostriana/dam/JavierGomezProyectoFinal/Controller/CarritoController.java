package com.salesianostriana.dam.JavierGomezProyectoFinal.Controller;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.CarritoService;
import com.salesianostriana.dam.JavierGomezProyectoFinal.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;
    private final ProductoService productoService;

    @GetMapping("/add/{id}")
    public String addProducto(@PathVariable Long id) {
        Producto producto = productoService.findById(id).orElse(null);
        if (producto != null) {
            carritoService.addProducto(producto);
        }
        return "redirect:/carrito/ver";
    }

    @GetMapping("/ver")
    public String verCarrito(Model model) {
        model.addAttribute("carrito", carritoService.obtenerCarrito());
        model.addAttribute("total", carritoService.calcularTotal());
        model.addAttribute("envio", carritoService.calcularEnvio());
        model.addAttribute("totalConEnvio", carritoService.calcularTotalConEnvio());
        model.addAttribute("ultimaActualizacion", carritoService.getUltimaActualizacion());
        return "carrito";
    }

    @GetMapping("/vaciar")
    public String vaciarCarrito() {
        carritoService.vaciarCarrito();
        return "redirect:/carrito/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        carritoService.eliminarProducto(id);
        return "redirect:/carrito/ver";
    }
}

