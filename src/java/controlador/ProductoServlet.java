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

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        ProductoDAO dao = new ProductoDAOImpl();
        req.setAttribute("ListaProducto", dao.listar());
        req.getRequestDispatcher("producto.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Producto p = new Producto(
                req.getParameter("nombre"),
                req.getParameter("descripcion"),
                Double.parseDouble(req.getParameter("precio")),
                req.getParameter("stock"),
                req.getParameter("url_imagen")
        );

        new ProductoDAOImpl().agregar(p);
        res.sendRedirect("ProductoServlet");
    }
}



