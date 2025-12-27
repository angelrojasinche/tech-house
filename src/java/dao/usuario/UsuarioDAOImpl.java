/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.usuario;

import modelo.Usuario;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import util.Conexion;

/**
 *
 * @author RS
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean registrar(Usuario u) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO usuarios (nombre, correo, password, rol) VALUES (?, ?, ?, ?)";

            con = Conexion.getConnection();

            // Encriptar password con BCrypt
            String hash = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());

            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, hash);
            ps.setString(4, u.getRol());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
            return false;

        }
    }

    @Override
    public Usuario login(String correo, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT*FROM usuarios WHERE correo=?";

            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String hash = rs.getString("password");
                if (BCrypt.checkpw(password, hash)) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setRol(rs.getString("rol"));
                    return u;
                }

            }

        } catch (Exception ex) {
            System.out.println("Ocurrió un error al hacer login: " + ex.getMessage());

        }
        return null;
    }
}
