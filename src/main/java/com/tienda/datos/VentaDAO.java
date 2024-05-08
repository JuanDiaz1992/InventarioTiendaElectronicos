package com.tienda.datos;

import com.tienda.models.Producto;
import com.tienda.models.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tienda.datos.conections.SqlLite.close;
import static com.tienda.datos.conections.SqlLite.getConection;

public class VentaDAO implements CRUD<Venta>{
    private static final String SQL_SELECT = "SELECT id_venta, productos, total_venta, fecha FROM venta";
    private static final String SQL_INSERT = "INSERT INTO venta (productos, total_venta, fecha) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE venta SET nombre=?, descripcion=?, rol=?, stock =? WHERE ?";
    private static final String SQL_DELETE = "DELETE FROM venta WHERE id_venta = ?";
    private static final String SQL_SELECTFROMID = "SELECT id_venta, productos, total_venta, fecha FROM venta WHERE id_venta = ?";

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
                String productos = rs.getString("productos");
                double totalVenta = rs.getDouble("total_venta");
                Date fecha = rs.getDate("fecha");
                List<Integer> listaProductos = Arrays.stream(productos.split(",\\s*"))
                                                                    .map(Integer::parseInt)
                                                                    .collect(Collectors.toList());
                List<Producto> productosLista = new ArrayList<>();
                ProductoDAO productoDAO = new ProductoDAO();
                for (Integer idProductos: listaProductos){
                    Producto producto = productoDAO.get(idProductos);
                    if (producto.getIdProducto() != 0){
                        productosLista.add(producto);
                    }
                }
                venta = new Venta(idVenta,productosLista,totalVenta,fecha);
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
            List<String> idProductos = new ArrayList<>();
            for (Producto producto: venta.getProductos()){
                idProductos.add(Integer.toString(producto.getIdProducto()));
            }
            String listaComoString = String.join(", ", idProductos);

            stmt.setString(1,listaComoString);
            stmt.setDouble(2,venta.getTotalVenta());
            stmt.setDate(3, (java.sql.Date) venta.getFecha());
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
            List<String> idProductos = new ArrayList<>();
            for (Producto producto: venta.getProductos()){
                idProductos.add(Integer.toString(producto.getIdProducto()));
            }
            String listaComoString = String.join(", ", idProductos);

            stmt.setString(1,listaComoString);
            stmt.setDouble(2,venta.getTotalVenta());
            stmt.setDate(3, (java.sql.Date) venta.getFecha());
            stmt.setInt(3, venta.getIdVenta());
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
            stmt = conn.prepareStatement(SQL_SELECTFROMID);
            stmt.setInt(1, idVenta);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_venta");
                String productos = rs.getString("productos");
                double totalVenta = rs.getDouble("total_venta");
                Date fecha = rs.getDate("fecha");
                List<Integer> listaProductos = Arrays.stream(productos.split(",\\s*"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                List<Producto> productosLista = new ArrayList<>();
                ProductoDAO productoDAO = new ProductoDAO();
                for (Integer idProductos: listaProductos){
                    Producto producto = productoDAO.get(idProductos);
                    if (producto != null){
                        productosLista.add(producto);
                    }
                }
                venta = new Venta(id,productosLista,totalVenta,fecha);
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
        return venta;
    }
}
