package com.tienda.services;

import com.tienda.datos.DetalleVentaDAO;
import com.tienda.models.DetalleVenta;
import com.tienda.models.Venta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tienda.datos.conections.SqlLite.getConection;

public class DetalleVentaService {
    private static DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();

    public int createSale (List<DetalleVenta> detalleVentas){
        Connection conexion = null;
        int result = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            detalleVentas.forEach(detalleVenta->{
                try {
                    detalleVentaDAO.insert(detalleVenta);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

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

    public List<DetalleVenta> detalleVentasForId(String id){
        Connection conexion = null;
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            detalleVentas = detalleVentaDAO.getListForID(id);

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
        return detalleVentas;

    }

    public List<DetalleVenta> detalleVentasForFactura(String factura){
        Connection conexion = null;
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            detalleVentas = detalleVentaDAO.get(factura);

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
        return detalleVentas;

    }
}
