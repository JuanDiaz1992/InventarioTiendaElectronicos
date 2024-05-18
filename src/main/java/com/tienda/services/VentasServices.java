package com.tienda.services;

import com.tienda.datos.VentaDAO;
import com.tienda.models.Venta;

import java.sql.Connection;
import java.sql.SQLException;


import static com.tienda.datos.conections.SqlLite.getConection;

public class VentasServices {
    static VentaDAO ventaDAO = new VentaDAO();

    public static int createSale (Venta ventas){
        Connection conexion = null;
        int result = 0;
        try {
            conexion = getConection();
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            ventaDAO.insert(ventas);

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
