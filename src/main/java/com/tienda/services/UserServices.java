package com.tienda.services;

import com.tienda.datos.UsuarioDAO;
import com.tienda.models.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.tienda.datos.SqlLite.getConection;

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
            System.out.println("Usuario creado correctamente");
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
            result = usuarioDAO.update(usuario);
            conexion.commit();
            System.out.println("Usuario modificado correctamente");
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

    public static Usuario getUser(String nombre){
        Connection conexion = null;
        Usuario usuarioResult = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            usuarioResult = usuarioDAO.getFromName(nombre);
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

    public static Usuario getUser(int id){
        Connection conexion = null;
        Usuario usuarioResult = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            usuarioResult = usuarioDAO.get(id);
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

    public static List<Usuario> getAllUsers(){
        Connection conexion = null;
        List <Usuario> usuarios = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            usuarios = usuarioDAO.select();
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
        return usuarios;
    }

    public static void delteteUser(Usuario usuarioDelte){
        Connection conexion = null;
        int usuario = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            usuario = usuarioDAO.delete(usuarioDelte);
            conexion.commit();
            System.out.println("Usuario eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

}
