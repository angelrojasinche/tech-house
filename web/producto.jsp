<%-- 
    Document   : producto
    Created on : 16 dic 2025, 23:11:23
    Author     : RS
--%>

<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <title>TECH HOUSE</title>
</head>

<body>
<main class="mx-auto max-w-6xl shadow-xl rounded mt-6 bg-base-100">

    <!-- NAVBAR -->
    <div class="navbar bg-base-100 shadow-sm px-6">
        <div class="flex-1">
            <span class="text-lg">Bienvenido, ${sessionScope.usuario.nombre}</span>
        </div>
        <div class="dropdown dropdown-end">
            <div tabindex="0" class="btn btn-ghost btn-circle avatar">
                <div class="w-10 rounded-full">
                    <img src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp" />
                </div>
            </div>
            <ul tabindex="0" class="menu menu-sm dropdown-content bg-base-100 rounded-box mt-3 w-52 p-2 shadow">
                <li><a href="ServletLogout">Cerrar Sesión</a></li>
            </ul>
        </div>
    </div>

    <!-- HEADER -->
    <header class="px-6 py-6 border-b">
        <div class="flex flex-col md:flex-row md:items-center md:justify-between gap-4">

            <div>
                <h1 class="text-5xl font-black tracking-wide text-primary drop-shadow-sm">
                    TECH HOUSE
                </h1>
                <p class="text-gray-500">
                    Tecnología que se adapta a tu estilo de vida
                </p>
            </div>

            <div class="flex gap-2">
                <input id="buscarProducto"
                       type="text"
                       placeholder="Buscar producto..."
                       class="input input-bordered w-64"/>
                <button class="btn btn-primary" onclick="nuevoProducto()">
                    + Nuevo Producto
                </button>
            </div>
        </div>
    </header>

    <!-- GRID DE PRODUCTOS -->
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 p-6">

        <%
            List<Producto> productos = (List<Producto>) request.getAttribute("ListaProducto");
            if (productos != null) {
                for (Producto p : productos) {
        %>

        <!-- CARD -->
        <div class="producto-card bg-white rounded-xl shadow-md hover:shadow-2xl transition flex flex-col min-h-[520px]">

            <!-- IMAGEN -->
            <% if (p.getUrl_imagen() != null && !p.getUrl_imagen().isEmpty()) { %>
            <div class="h-56 flex items-center justify-center overflow-hidden">
                <img src="<%= p.getUrl_imagen() %>"
                     class="h-full object-contain transition-transform duration-500 hover:scale-110"/>
            </div>
            <% } %>

            <!-- INFO -->
            <div class="p-4 flex flex-col gap-2 flex-grow">

                <h3 class="font-semibold text-sm line-clamp-2 min-h-[40px]">
                    <%= p.getNombre() %>
                </h3>

                <span class="badge w-fit <%= p.getStock().equals("DISPONIBLE") ? "badge-success" : "badge-error" %>">
                    <%= p.getStock() %>
                </span>

                <p class="text-xs text-gray-500 line-clamp-2">
                    <%= p.getDescripcion() %>
                </p>

                <p class="text-xl font-bold mt-2">
                    S/. <%= p.getPrecio() %>
                </p>

                <!-- ACCIONES -->
                <div class="flex gap-2 justify-end mt-auto">

                    <!-- VER DETALLE -->
                    <button class="btn-view btn btn-sm btn-outline"
                            data-nombre="<%= p.getNombre() %>"
                            data-descripcion="<%= p.getDescripcion() %>"
                            data-precio="<%= p.getPrecio() %>"
                            data-stock="<%= p.getStock() %>"
                            data-imagen="<%= p.getUrl_imagen() %>">
                        👁️
                    </button>

                    <!-- EDITAR -->
                    <button class="btn-edit btn btn-sm btn-warning"
                            data-id="<%= p.getIdProducto() %>"
                            data-nombre="<%= p.getNombre() %>"
                            data-descripcion="<%= p.getDescripcion() %>"
                            data-precio="<%= p.getPrecio() %>"
                            data-stock="<%= p.getStock() %>"
                            data-url_imagen="<%= p.getUrl_imagen() %>">
                        ✏️
                    </button>

                    <!-- ELIMINAR -->
                    <a class="btn btn-sm btn-error"
                       href="ProductoDelete?idProducto=<%= p.getIdProducto() %>">
                        🗑️
                    </a>
                </div>
            </div>
        </div>

        <%
                }
            }
        %>

    </div>
</main>

<!-- MODAL DETALLE -->
<dialog id="modalDetalle" class="modal">
    <div class="modal-box max-w-3xl">
        <div class="grid md:grid-cols-2 gap-6">
            <img id="detalleImagen" class="max-h-72 object-contain"/>
            <div>
                <h3 id="detalleNombre" class="text-2xl font-bold"></h3>
                <span id="detalleStock" class="badge my-2"></span>
                <p id="detalleDescripcion" class="text-sm text-gray-600"></p>
                <p id="detallePrecio" class="text-2xl font-bold mt-4"></p>
            </div>
        </div>
        <div class="modal-action">
            <button class="btn" onclick="modalDetalle.close()">Cerrar</button>
        </div>
    </div>
</dialog>

<!-- MODAL CRUD -->
<dialog id="my_modal_1" class="modal">
    <div class="modal-box">
        <h2 id="titulomodal" class="text-xl font-bold">Nuevo Producto</h2>

        <form id="formularioProducto" action="ProductoServlet" method="post">
            <input type="hidden" name="idProducto" id="idProducto"/>

            <input class="input input-bordered w-full my-1" name="nombre" id="nombre" placeholder="Nombre"/>
            <textarea class="textarea textarea-bordered w-full my-1" name="descripcion" id="descripcion" placeholder="Descripción"></textarea>
            <input class="input input-bordered w-full my-1" type="number" name="precio" id="precio" placeholder="Precio"/>
            <select class="select select-bordered w-full my-1" name="stock" id="stock">
                <option>DISPONIBLE</option>
                <option>AGOTADO</option>
            </select>
            <input class="input input-bordered w-full my-1" name="url_imagen" id="url_imagen" placeholder="URL imagen"/>

            <div class="mt-4 flex justify-end gap-2">
                <button type="button" class="btn btn-ghost" onclick="my_modal_1.close()">Cancelar</button>
                <button class="btn btn-primary">Guardar</button>
            </div>
        </form>
    </div>
</dialog>

<!-- JS -->
<script>
    // BUSCADOR
    document.getElementById("buscarProducto").addEventListener("keyup", function () {
        const texto = this.value.toLowerCase();
        document.querySelectorAll(".producto-card").forEach(card => {
            card.style.display = card.innerText.toLowerCase().includes(texto) ? "flex" : "none";
        });
    });

    // VER DETALLE
    document.querySelectorAll(".btn-view").forEach(btn => {
        btn.onclick = () => {
            detalleNombre.innerText = btn.dataset.nombre;
            detalleDescripcion.innerText = btn.dataset.descripcion;
            detallePrecio.innerText = "S/. " + btn.dataset.precio;
            detalleStock.innerText = btn.dataset.stock;
            detalleStock.className = "badge " + (btn.dataset.stock === "DISPONIBLE" ? "badge-success" : "badge-error");
            detalleImagen.src = btn.dataset.imagen;
            modalDetalle.showModal();
        };
    });

    // NUEVO
    function nuevoProducto() {
        titulomodal.innerText = "Nuevo Producto";
        formularioProducto.action = "ProductoServlet";
        formularioProducto.reset();
        my_modal_1.showModal();
    }

    // EDITAR
    document.querySelectorAll(".btn-edit").forEach(btn => {
        btn.onclick = () => {
            titulomodal.innerText = "Editar Producto";
            formularioProducto.action = "ProductoEditServlet";
            idProducto.value = btn.dataset.id;
            nombre.value = btn.dataset.nombre;
            descripcion.value = btn.dataset.descripcion;
            precio.value = btn.dataset.precio;
            stock.value = btn.dataset.stock;
            url_imagen.value = btn.dataset.url_imagen;
            my_modal_1.showModal();
        };
    });
</script>

</body>
</html>



