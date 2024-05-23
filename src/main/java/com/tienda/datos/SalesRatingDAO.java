package com.tienda.datos;

import com.tienda.models.SalesRating;
import com.tienda.models.Usuario;
import com.tienda.models.Venta;
import com.tienda.services.UserServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tienda.datos.conections.SqlLite.close;
import static com.tienda.datos.conections.SqlLite.getConection;

public class SalesRatingDAO {

    private static final String SQL_BESTUSERMONTH ="SELECT usuario, SUM(total_venta) as total_monto, COUNT(*) as total_ventas " +
                                                    "FROM venta " +
                                                    "WHERE date LIKE ? " +
                                                    "GROUP BY usuario " +
                                                    "ORDER BY total_venta DESC ";


    private Connection conexionTransaccional;
    public SalesRatingDAO(){}
    public SalesRatingDAO(Connection connection){
        this.conexionTransaccional = connection;
    }

    public List<SalesRating> get(String mounth) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SalesRating salesRating = null;
        List<SalesRating> salesRatingList = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_BESTUSERMONTH);
            stmt.setString(1, mounth);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("usuario");
                int totalMonto = rs.getInt("total_monto");
                int totalVenta = rs.getInt("total_ventas");
                Usuario usuario = UserServices.getUser(idUsuario);
                salesRating = new SalesRating(usuario, idUsuario, totalVenta, totalMonto);
                salesRatingList.add(salesRating);
            }
        }finally {
            try {
                if (rs != null) {
                    close(rs);
                    close(stmt);
                }
                if (this.conexionTransaccional == null){
                    close(conn);
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return salesRatingList;
    }
}
