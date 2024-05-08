package com.tienda.datos;

import com.tienda.datos.conections.SqlLite;
import com.tienda.models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tienda.datos.conections.SqlLite.close;
import static com.tienda.datos.conections.SqlLite.getConection;

public class ProductoDAO implements CRUD<Producto>{
    private static final String SQL_SELECT = "SELECT id_producto, nombre, descripcion, precio, stock FROM producto";
    private static final String SQL_INSERT = "INSERT INTO producto (nombre, descripcion, precio, stock) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock =? WHERE id_producto = ?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE id_producto = ?";
    private static final String SQL_SELECTFROMID = "SELECT id_producto, nombre, descripcion, precio, stock FROM producto WHERE id_producto = ?";
    private static final String SQL_SELECTFROMNAME = "SELECT id_producto, nombre, descripcion, precio, stock FROM producto WHERE nombre = ?";


    private Connection conexionTransaccional;
    public ProductoDAO(){}
    public ProductoDAO(Connection connection){
        this.conexionTransaccional = connection;
    }

    @Override
    public List<Producto> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                int stock = rs.getInt("stock");
                producto = new Producto(idProducto,nombre,descripcion,precio,stock);
                productos.add(producto);
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
        return productos;
    }

    @Override
    public int insert(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,producto.getNombre());
            stmt.setString(2,producto.getDescripcion());
            stmt.setInt(3,producto.getPrecio());
            stmt.setInt(4,producto.getStock());
            registros = stmt.executeUpdate();
        }finally {
            try {
                if (stmt != null) {
                    SqlLite.close(stmt);
                }
                if (this.conexionTransaccional == null){
                    if (conn != null) {
                        SqlLite.close(conn);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int update(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,producto.getNombre());
            stmt.setString(2,producto.getDescripcion());
            stmt.setDouble(3,producto.getPrecio());
            stmt.setInt(4,producto.getStock());
            stmt.setInt(5,producto.getIdProducto());
            registros = stmt.executeUpdate();
        }finally {
            try {
                if (stmt != null) {
                    SqlLite.close(stmt);
                }
                if (this.conexionTransaccional == null){
                    if (stmt != null) {
                        SqlLite.close(conn);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int delete(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,producto.getIdProducto());
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

    public Producto get(int idProducto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : SqlLite.getConection();
            stmt = conn.prepareStatement(SQL_SELECTFROMID);
            stmt.setInt(1, idProducto);
            rs = stmt.executeQuery();
            if (rs.next()) {

                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                int stock = rs.getInt("stock");
                producto = new Producto(id,nombre,descripcion,precio,stock);
            }
        }finally {
            try {
                if (rs != null) {
                    SqlLite.close(rs);
                    SqlLite.close(stmt);
                }
                if (this.conexionTransaccional == null){
                    if (rs != null) {
                        SqlLite.close(conn);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return producto;
    }

    public Producto getFromName(String nombreProducto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : SqlLite.getConection();
            stmt = conn.prepareStatement(SQL_SELECTFROMNAME);
            stmt.setString(1, nombreProducto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                int stock = rs.getInt("stock");
                producto = new Producto(id,nombre,descripcion,precio,stock);
            }
        }finally {
            try {
                if (rs != null) {
                    SqlLite.close(rs);
                    SqlLite.close(stmt);
                }
                if (this.conexionTransaccional == null){
                    if (rs != null) {
                        SqlLite.close(conn);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return producto;
    }

}
