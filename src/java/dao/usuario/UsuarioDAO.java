/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.usuario;

import modelo.Usuario;

/**
 *
 * @author RS
 */
public interface UsuarioDAO {

    boolean registrar(Usuario u);

    Usuario login(String correo, String password);
}
