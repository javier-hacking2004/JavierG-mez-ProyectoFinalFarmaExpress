function ordenarProductos(tipo) {
    const contenedor = document.getElementById('contenedor-productos');
    const productos = Array.from(contenedor.querySelectorAll('.product-card'));

    const ordenado = productos.sort((a, b) => {
        const nombreA = a.dataset.nombre.toLowerCase();
        const nombreB = b.dataset.nombre.toLowerCase();
        const precioA = parseFloat(a.dataset.precio);
        const precioB = parseFloat(b.dataset.precio);

        if (tipo === 'nombre') {
            return nombreA.localeCompare(nombreB);
        } else if (tipo === 'precioAsc') {
            return precioA - precioB;
        } else if (tipo === 'precioDesc') {
            return precioB - precioA;
        }
    });

    // Volver a meter los productos ordenados desde el principio (como estaba antes)
    const columnas = contenedor.querySelectorAll('.col-12');
    columnas.forEach(col => col.remove());

    ordenado.forEach(producto => {
        contenedor.appendChild(producto.closest('.col-12'));
    });
}
