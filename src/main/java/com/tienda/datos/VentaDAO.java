package com.tienda.datos;
import com.tienda.models.Venta;
import com.tienda.utils.DateManipulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static com.tienda.datos.conections.SqlLite.close;
import static com.tienda.datos.conections.SqlLite.getConection;

public class VentaDAO implements ICRUD<Venta> {
    private static final String SQL_SELECT = "SELECT id_venta,total_venta, date,  usuario, num_factura FROM venta";
    private static final String SQL_INSERT = "INSERT INTO venta (total_venta, date,  usuario, num_factura) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE venta SET total_venta=?, date=?, usuario=?, num_factura =? WHERE id_venta = ?";
    private static final String SQL_DELETE = "DELETE FROM venta WHERE id_venta = ?";
    private static final String SQL_SELECTFORID = "SELECT id_venta,total_venta, date,  usuario, num_factura FROM venta WHERE id_venta = ?";
    private static final String SQL_SELECTFORFACT = "SELECT id_venta,total_venta, date,  usuario, num_factura FROM venta WHERE num_factura = ?";
    private static final String SQL_SELECTFORDATE = "SELECT id_venta,total_venta, date,  usuario, num_factura FROM venta WHERE date LIKE ?";


    private Connection conexionTransaccional;
    public VentaDAO(){}
    public VentaDAO(Connection connection){
        this.conexionTransaccional = connection;
    }

    @Override
    public List<Venta> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idVenta = rs.getInt("id_venta");
                String date = rs.getString("date");
                int totalVenta = rs.getInt("total_venta");
                int vendedor = rs.getInt("usuario");
                String numFactura = rs.getString("num_factura");
                venta = new Venta(idVenta,totalVenta, date, vendedor, numFactura);
                ventas.add(venta);
            }
        }finally {
            try {
                close(rs);
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return ventas;
    }

    @Override
    public int insert(Venta venta) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1,venta.getTotalVenta());
            stmt.setString(2,venta.getFecha());
            stmt.setInt(3, venta.getIdUsuario());
            stmt.setString(4, venta.getNumFactura());
            registros = stmt.executeUpdate();
        }finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int update(Venta venta) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1,venta.getTotalVenta());
            stmt.setString(2,venta.getFecha());
            stmt.setInt(3, venta.getIdUsuario());
            stmt.setString(4, venta.getNumFactura());
            stmt.setInt(5, venta.getIdVenta());
            registros = stmt.executeUpdate();
        }finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int delete(Venta venta) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, venta.getIdVenta());
            registros = stmt.executeUpdate();
        }finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public Venta get(int idVenta) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Venta venta = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECTFORID);
            stmt.setInt(1, idVenta);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String date = rs.getString("date");
                int totalVenta = rs.getInt("total_venta");
                int vendedor = rs.getInt("usuario");
                String numFactura = rs.getString("num_factura");
                venta = new Venta(idVenta,totalVenta, date, vendedor, numFactura);
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
        return venta;
    }

    public Venta get(String factura) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Venta venta = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECTFORFACT);
            stmt.setString(1, factura);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idVenta = rs.getInt("id_venta");
                String date = rs.getString("date");
                int totalVenta = rs.getInt("total_venta");
                int vendedor = rs.getInt("usuario");
                String numFactura = rs.getString("num_factura");
                venta = new Venta(idVenta,totalVenta, date, vendedor, numFactura );
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
        return venta;
    }

    public List<Venta> getForDate(String dateSearch) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECTFORDATE);
            stmt.setString(1, dateSearch);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idVenta = rs.getInt("id_venta");
                String date = rs.getString("date");
                int totalVenta = rs.getInt("total_venta");
                int vendedor = rs.getInt("usuario");
                String numFactura = rs.getString("num_factura");
                venta = new Venta(idVenta,totalVenta, date, vendedor, numFactura );
                String dateFormat = DateManipulator.reformat(venta.getFecha());
                venta.setFecha(dateFormat);
                ventas.add(venta);
            }
        }finally {
            try {
                if (rs != null) {
                    close(rs);
                    close(stmt);
                }
                if (this.conexionTransaccional == null){
                    if (rs != null) {
                        close(conn);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return ventas;
    }

}
