package com.tienda.services;

import com.tienda.datos.SalesRatingDAO;
import com.tienda.datos.VentaDAO;
import com.tienda.models.SalesRating;
import com.tienda.models.Venta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tienda.datos.conections.SqlLite.getConection;

public class InformesServices {
    static VentaDAO ventaDAO = new VentaDAO();
    static SalesRatingDAO salesRatingDAO = new SalesRatingDAO();


    public List<Venta> viewSales(String date){
        Connection conexion = null;
        List<Venta> ventas = new ArrayList<>();
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            ventas = ventaDAO.getForDate(date);

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
        return ventas;
    }

    public  List<SalesRating> viewRating(String mounth){
        Connection conexion = null;
        List<SalesRating> salesRatingList = new ArrayList<>();
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            salesRatingList = salesRatingDAO.get(mounth);

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
        return salesRatingList;
    }
}
