/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Producto;
import util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Producto";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getString("stock"));
                p.setUrl_imagen(rs.getString("url_imagen"));
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
public boolean agregar(Producto p) {
    String sql = "INSERT INTO Producto (nombre, descripcion, precio, stock, url_imagen) VALUES (?,?,?,?,?)";

    try (Connection cn = Conexion.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, p.getNombre());
        ps.setString(2, p.getDescripcion());
        ps.setDouble(3, p.getPrecio());
        ps.setString(4, p.getStock());
        ps.setString(5, p.getUrl_imagen());

        int filas = ps.executeUpdate();   // 🔴 CAMBIO CLAVE
        System.out.println("Filas insertadas: " + filas);

        return filas > 0;

    } catch (Exception e) {
        System.out.println("Error agregar: " + e.getMessage());
        return false;
    }
}


    @Override
    public Producto buscar(int id) {
        Producto p = null;
        String sql = "SELECT * FROM Producto WHERE idProducto=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getString("stock"));
                p.setUrl_imagen(rs.getString("url_imagen"));
            }

        } catch (Exception e) {
            System.out.println("Error buscar: " + e.getMessage());
        }
        return p;
    }

    @Override
    public boolean actualizar(Producto p) {
        String sql = "UPDATE Producto SET nombre=?, descripcion=?, precio=?, stock=?, url_imagen=? WHERE idProducto=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getStock());         
            ps.setString(5, p.getUrl_imagen());
            ps.setInt(6, p.getIdProducto());
            
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Producto WHERE idProducto=?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }
}

