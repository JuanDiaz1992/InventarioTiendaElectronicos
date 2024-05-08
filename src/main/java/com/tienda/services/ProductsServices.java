package com.tienda.services;

import com.tienda.datos.ProductoDAO;
import com.tienda.models.Producto;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.tienda.datos.SqlLite.getConection;

public class ProductsServices {

    static ProductoDAO productoDAO = new ProductoDAO();

    public static int createProductService (Producto producto){
        Connection conexion = null;
        int result = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            result = productoDAO.insert(producto);
            conexion.commit();
            System.out.println("Producto "+producto.getNombre()+" creado correctamente");
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

    public static int updateProductService(Producto producto){
        Connection conexion = null;
        int result = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            result = productoDAO.update(producto);
            conexion.commit();
            System.out.println("Producto "+producto.getNombre()+" modificado correctamente");
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

    public static Producto getProduct(String nombre){
        Connection conexion = null;
        Producto producto = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            producto = productoDAO.getFromName(nombre);
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
        return producto;
    }

    public static Producto getProduct(int id){
        Connection conexion = null;
        Producto producto = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            producto = productoDAO.get(id);
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
        return producto;
    }

    public static List<Producto> getAllProducts(){
        Connection conexion = null;
        List <Producto> productos = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            productos = productoDAO.select();
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
        return productos;
    }

    public static void delteteProduct(Producto producto){
        Connection conexion = null;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            productoDAO.delete(producto);
            conexion.commit();
            System.out.println("Producto "+producto.getNombre()+" eliminado correctamente");
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
