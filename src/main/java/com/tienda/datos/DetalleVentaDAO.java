package com.tienda.datos;

import com.tienda.models.DetalleVenta;
import com.tienda.models.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tienda.datos.conections.SqlLite.close;
import static com.tienda.datos.conections.SqlLite.getConection;

public class DetalleVentaDAO implements ICRUD<DetalleVenta>{
    private static final String SQL_SELECT = "SELECT id_detalle_venta,factura, id_producto,  cantidad_producto, precio, importe FROM detalle_venta";
    private static final String SQL_INSERT = "INSERT INTO detalle_venta (factura, id_producto,  cantidad_producto, precio, importe) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE detalle_venta SET factura=?, id_producto=?, cantidad_producto=?, precio =?, importe =? WHERE id_venta = ?";
    private static final String SQL_DELETE = "DELETE FROM detalle_venta WHERE id_venta = ?";
    private static final String SQL_SELECTFORID = "SELECT id_detalle_venta,factura, id_producto,  cantidad_producto, precio, importe FROM detalle_venta WHERE id_venta = ?";
    private static final String SQL_SELECTFORFACT = "SELECT id_detalle_venta,factura, id_producto,  cantidad_producto, precio, importe FROM detalle_venta WHERE num_factura = ?";

    private Connection conexionTransaccional;
    public DetalleVentaDAO(){}
    public DetalleVentaDAO(Connection connection){
        this.conexionTransaccional = connection;
    }

    @Override
    public List<DetalleVenta> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVenta detalleVenta = null;
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idDetalleVenta = rs.getInt("id_detalle_venta");
                String factura = rs.getString("factura");
                int idProducto = rs.getInt("id_producto");
                int cantidadProducto = rs.getInt("cantidad_producto");
                int precio = rs.getInt("precio");
                int importe = rs.getInt("importe");
                detalleVenta = new DetalleVenta(idDetalleVenta,factura,idProducto,cantidadProducto,precio,importe);
                detalleVentas.add(detalleVenta);
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
        return detalleVentas;
    }

    @Override
    public int insert(DetalleVenta detalleVenta) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,detalleVenta.getFactura());
            stmt.setInt(2,detalleVenta.getIdProducto());
            stmt.setInt(3, detalleVenta.getCantidadProducto());
            stmt.setInt(4, detalleVenta.getPrecio());
            stmt.setInt(5, detalleVenta.getImporte());
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
    public int update(DetalleVenta detalleVenta) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,detalleVenta.getFactura());
            stmt.setInt(2,detalleVenta.getIdProducto());
            stmt.setInt(3, detalleVenta.getCantidadProducto());
            stmt.setInt(4, detalleVenta.getPrecio());
            stmt.setInt(5, detalleVenta.getImporte());
            stmt.setInt(5, detalleVenta.getIdDetalleVenta());
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
    public int delete(DetalleVenta detalleVenta) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, detalleVenta.getIdDetalleVenta());
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
    public DetalleVenta get(int idVenta) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVenta detalleVenta = null;
        try {
            stmt = conn.prepareStatement(SQL_SELECTFORID);
            stmt.setInt(1, idVenta);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idDetalleVenta = rs.getInt("id_detalle_venta");
                String factura = rs.getString("factura");
                int idProducto = rs.getInt("id_producto");
                int cantidadProducto = rs.getInt("cantidad_producto");
                int precio = rs.getInt("precio");
                int importe = rs.getInt("importe");
                detalleVenta = new DetalleVenta(idDetalleVenta,factura,idProducto,cantidadProducto,precio,importe);
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
        return detalleVenta;
    }

    public DetalleVenta get(String facturaVenta) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVenta detalleVenta = null;
        try {
            stmt = conn.prepareStatement(SQL_SELECTFORFACT);
            stmt.setString(1, facturaVenta);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idDetalleVenta = rs.getInt("id_detalle_venta");
                String factura = rs.getString("factura");
                int idProducto = rs.getInt("id_producto");
                int cantidadProducto = rs.getInt("cantidad_producto");
                int precio = rs.getInt("precio");
                int importe = rs.getInt("importe");
                detalleVenta = new DetalleVenta(idDetalleVenta,factura,idProducto,cantidadProducto,precio,importe);
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
        return detalleVenta;
    }
}
