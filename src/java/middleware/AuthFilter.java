/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package middleware;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author christianruiz
 */
@WebFilter(urlPatterns = {"/producto.jsp", "/ProductoServlet", "/ProductoDeleteServlet", "/ProductoEditServlet"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        //1. Obtener el request y responde para HTTP
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //2. Obtener la sesion del usuario ( sin crear una nueva)
        HttpSession session = request.getSession(false);
       //System.out.println("Session: " + session.getId());
        //3. Validar que el usuario tenga el atributo usuario
        boolean loggedIn = (session != null && session.getAttribute("usuario") != null);

        String loginURL = request.getContextPath() + "/login.jsp";
        //4. Valida si tiene sesion activa.
        if (loggedIn) {
            chain.doFilter(request, response);
        } else {

            response.sendRedirect(loginURL);
        }

    }

}
