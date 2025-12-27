/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Producto;

public interface ProductoDAO {

    List<Producto> listar();
    boolean agregar(Producto p);
    Producto buscar(int id);
    boolean actualizar(Producto p);
    boolean eliminar(int id);
}

