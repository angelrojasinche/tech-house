/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import modelo.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductoEditServlet", urlPatterns = {"/ProductoEditServlet"})
public class ProductoEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No se usa (misma lógica que CINE)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        System.out.println("Se recibe el ID: " + idProducto);

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String stock = request.getParameter("stock");
        String url_imagen=request.getParameter("url_imagen");

        System.out.println("===== DATOS RECIBIDOS DEL EDIT =====");
        System.out.println("ID: " + idProducto);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("===================================");

        Producto actualizar = new Producto(
                idProducto,
                nombre,
                descripcion,
                precio,
                stock,
                url_imagen
        );

        ProductoDAO dao = new ProductoDAOImpl();
        dao.actualizar(actualizar);

        response.sendRedirect("ProductoServlet");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para editar producto";
    }
}

