package com.tienda.services;

import com.tienda.datos.CartDAO;
import com.tienda.datos.UsuarioDAO;
import com.tienda.models.Cart;
import com.tienda.models.Producto;
import com.tienda.models.Usuario;
import com.tienda.utils.EncryptPassword;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.tienda.datos.conections.SqlLite.getConection;

public class CartServices {

    static CartDAO cartDAO = new CartDAO();

    public static int insertToCart (Cart cart){
        Connection conexion = null;
        int result = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            cartDAO.insert(cart);
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

    public static List<Cart> getCart(Usuario usuario){
        Connection conexion = null;
        int result = 0;
        List<Cart> carts = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            carts = cartDAO.get(usuario);
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
        return carts;
    }

    public static Cart getCart(Producto producto){
        Connection conexion = null;
        Cart cart = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            cart = cartDAO.get(producto);
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
        return cart;
    }

    public static Cart update(Cart cart){
        Connection conexion = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            cartDAO.update(cart);
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
        return cart;
    }

    public static void delete(Cart cart){
        Connection conexion = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            cartDAO.delete(cart);
            conexion.commit();
            System.out.println("Elemento eliminado correctamente");
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

    public static void delteCart(Usuario usuario){
        Connection conexion = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            cartDAO.delete(usuario);
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
    }


}
