<%-- 
    Document   : registro
    Created on : 22 dic 2025, 23:38:49
    Author     : RS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/daisyui@5" rel="stylesheet" type="text/css" />
        <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="hero bg-base-200 min-h-screen">
            <div class="hero-content flex-col lg:flex-row-reverse">
                <div class="card bg-base-100 w-full max-w-sm shrink-0 shadow-2xl">
                    <form class="card-body" method="POST" action="RegisterServlet">
                        <h2 class="mb-4 text-center text-2xl font-bold">Crear Cuenta</h2>

                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">Nombre</span>
                            </label>

                            <input type="text" name="nombre" id="nombre" placeholder="Ej: Angel" class="input input-bordered" required />
                        </div>

                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">Correo</span>
                            </label>

                            <input type="text" name="correo" id="correo" placeholder="correo@correo.com" class="input input-bordered" required />
                        </div>
                        
                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">Contraseña</span>
                            </label>

                            <input type="password" name="password" id="password" placeholder="******" class="input input-bordered" required />
                        </div>
                        
                        <div class="form-control mt-6">
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </div>
                    </form>
                    <c:if test="${not empty error}">
                        <p style="color:red">${error}</p>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>

