package com.salesianostriana.dam.JavierGomezProyectoFinal.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    // mappedBy = "categoria" indica que la propiedad "categoria" en Producto es la dueña de la relación.
    // cascade = CascadeType.ALL permite que si borramos una Categoria, también se borren sus productos asociados.
    // orphanRemoval = true elimina automáticamente los productos que se queden sin categoría.

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;
}

