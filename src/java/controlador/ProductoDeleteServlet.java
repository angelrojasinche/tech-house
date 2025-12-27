/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ProductoDAO;
import dao.ProductoDAOImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet(name = "ProductoDelete", urlPatterns = {"/ProductoDelete"})
public class ProductoDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            System.out.println("Eliminar producto ID: " + idProducto);

            ProductoDAO dao = new ProductoDAOImpl();
            dao.eliminar(idProducto);

        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }

        response.sendRedirect("ProductoServlet");
    }
}

