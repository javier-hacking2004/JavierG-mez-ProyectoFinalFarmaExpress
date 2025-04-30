package com.salesianostriana.dam.JavierGomezProyectoFinal.service;

import com.salesianostriana.dam.JavierGomezProyectoFinal.model.Producto;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductosService {

    private final List<Producto> productos = new ArrayList<>();
    private Long autoId = 1L;

    public ProductosService() {
        // Productos iniciales (sacados del HTML)
        agregarProducto(new Producto(null, "Avene Solar SPF 50", "Protector solar anti-imperfecciones",
                "/img/avene-solar-cleanance-anti-imperfecciones-spf-50-50-ml.jpg", 14.99, "Verano", true));

        agregarProducto(new Producto(null, "Eucerin Pack PH5", "Loción enriquecida + Gel de baño",
                "/img/eucerin-pack-ahorro-ph5-locion-enriquecida-1-l-gel-bano-200-ml.jpg", 22.99, "Corporal", true));

        agregarProducto(new Producto(null, "Oraldine Clásico", "Enjuague bucal clásico",
                "/img/oraldine-clasico.jpg", 9.99, "Bucal", true));

        agregarProducto(new Producto(null, "Endocare Tensage", "Protocolo reafirmante crema nutritiva + ampollas",
                "/img/endocare-protocolo-reafirmante-tensage-crema-nutritiva-50-ml-tensage-10-ampollas.jpg", 28.99, "Facial", true));
    }

    public void agregarProducto(Producto producto) {
        producto.setId(autoId++);
        productos.add(producto);
    }

    public void eliminarProducto(Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

    public List<Producto> obtenerTodos() {
        return productos;
    }

    public List<Producto> obtenerMasVendidos() {
        return productos.stream()
                .filter(Producto::isMasVendido)
                .collect(Collectors.toList());
    }

    public List<Producto> obtenerPorCategoria(String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}



