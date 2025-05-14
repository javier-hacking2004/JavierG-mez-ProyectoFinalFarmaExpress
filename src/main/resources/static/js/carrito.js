const carrito = {};
const envioFijo = 3.95;
const umbralEnvioGratis = 30.00;

function actualizarContador() {
    let totalCantidad = Object.values(carrito).reduce((acc, item) => acc + item.cantidad, 0);
    const badge = document.querySelector('.bi-cart3 + .badge, .position-relative .badge');
    if (badge) {
        badge.textContent = totalCantidad;
    }
}

function actualizarDropdown() {
    const dropdown = document.querySelector('.dropdown-menu');
    if (!dropdown) return;

    dropdown.innerHTML = '';

    let total = 0;
    for (const id in carrito) {
        const item = carrito[id];
        const subtotal = item.precio * item.cantidad;
        total += subtotal;

        const row = document.createElement('div');
        row.className = 'd-flex justify-content-between align-items-center mb-2';
        row.innerHTML = `
            <span>${item.nombre}</span>
            <div>
                <button class="btn btn-sm btn-outline-secondary" onclick="modificarCantidad(${id}, -1)">-</button>
                <span class="mx-2">${item.cantidad}</span>
                <button class="btn btn-sm btn-outline-secondary" onclick="modificarCantidad(${id}, 1)">+</button>
            </div>`;
        dropdown.appendChild(row);
    }

    dropdown.innerHTML += '<hr>';

    const totalText = document.createElement('div');
    totalText.className = 'fw-bold';
    totalText.innerText = `Total: ${total.toFixed(2)} €`;
    dropdown.appendChild(totalText);

    const envioInfo = document.createElement('div');
    envioInfo.className = 'small mt-1';
    envioInfo.classList.add(total >= umbralEnvioGratis ? 'text-success' : 'text-danger');
    envioInfo.innerText = total >= umbralEnvioGratis
        ? '¡Esta compra supera 30 €. Envío gratis!'
        : `Envío: ${envioFijo.toFixed(2)} €`;
    dropdown.appendChild(envioInfo);
}

function modificarCantidad(id, cambio) {
    if (!carrito[id]) return;
    carrito[id].cantidad += cambio;
    if (carrito[id].cantidad <= 0) {
        delete carrito[id];
    }
    actualizarContador();
    actualizarDropdown();
}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.add-to-cart').forEach(boton => {
        boton.addEventListener('click', () => {
            const id = boton.dataset.id;
            const nombre = boton.dataset.nombre;
            const precio = parseFloat(boton.dataset.precio);

            if (!carrito[id]) {
                carrito[id] = { nombre, precio, cantidad: 1 };
            } else {
                carrito[id].cantidad++;
            }

            actualizarContador();
            actualizarDropdown();
        });
    });
});
