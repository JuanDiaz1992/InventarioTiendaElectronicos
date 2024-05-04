package com.tienda.services;

import com.tienda.datos.UsuarioDAO;
import com.tienda.models.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

import static com.tienda.datos.MySql.getConection;

public class UserServices {

    static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public static Usuario loginService(String nombre, String password){
        Connection conexion = null;
        Usuario usuarioResult = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            Usuario usuario = usuarioDAO.getFromName(nombre);
            if (usuario != null){
                if (EncryptPassword.validatePassword(password, usuario.getPassword())){
                    return usuarioResult = usuario;
                }
            }
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return usuarioResult;
    }

    public static int createUserService (Usuario usuario){
        Connection conexion = null;
        int result = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            if (usuarioDAO.getFromName(usuario.getNombre()) == null){
                usuario.setPassword(EncryptPassword.hashPassword(usuario.getPassword()));
                result = usuarioDAO.insert(usuario);
            }
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return result;
    }

    public static int updateUserService(Usuario usuario){
        Connection conexion = null;
        int result = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            if (usuarioDAO.getFromName(usuario.getNombre()) != null){
                result = usuarioDAO.update(usuario);
            }
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return result;
    }

}
