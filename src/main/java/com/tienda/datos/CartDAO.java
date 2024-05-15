package com.tienda.datos;

import com.tienda.datos.conections.SqlLite;
import com.tienda.models.Cart;
import com.tienda.models.Producto;
import com.tienda.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tienda.datos.conections.SqlLite.close;
import static com.tienda.datos.conections.SqlLite.getConection;

public class CartDAO implements ICRUD<Cart> {
    private static final String SQL_SELECT = "SELECT id_carrito, id_producto, nombre_producto, cantidad_producto, precio_producto, id_usuario, total_valor_producto FROM carrito_compras";
    private static final String SQL_INSERT = "INSERT INTO carrito_compras (id_producto, nombre_producto, cantidad_producto, precio_producto, id_usuario, total_valor_producto) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE carrito_compras SET id_producto=?, nombre_producto=?, cantidad_producto=?, precio_producto=?, id_usuario=?, total_valor_producto =? WHERE id_carrito = ?";
    private static final String SQL_DELETE = "DELETE FROM carrito_compras WHERE id_producto = ?";
    private static final String SQL_DELETEFORUSER = "DELETE FROM carrito_compras WHERE id_usuario = ?";
    private static final String SQL_SELECTFROMID = "SELECT id_carrito, id_producto, nombre_producto, cantidad_producto, precio_producto, id_usuario, total_valor_producto FROM carrito_compras WHERE id_carrito = ?";
    private static final String SQL_SELECTFORIDPRODUCT = "SELECT id_carrito, id_producto, nombre_producto, cantidad_producto, precio_producto, id_usuario, total_valor_producto FROM carrito_compras WHERE id_producto = ?";
    private static final String SQL_SELECTFROMIDUSER = "SELECT id_carrito, id_producto, nombre_producto, cantidad_producto, precio_producto, id_usuario, total_valor_producto FROM carrito_compras WHERE id_usuario = ?";

    private Connection conexionTransaccional;
    public CartDAO(){}
    public CartDAO(Connection connection){
        this.conexionTransaccional = connection;
    }

    @Override
    public List<Cart> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cart cart = null;
        List<Cart> carts = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idCarrito = rs.getInt("id_carrito");
                int idProducto = rs.getInt("id_producto");
                String nombreProducto = rs.getString("nombre_producto");
                int cantidaProductos = rs.getInt("cantidad_producto");
                int precioProducto = rs.getInt("precio_producto");
                int idUsuario = rs.getInt("id_usuario");
                int totalProducto = rs.getInt("total_valor_producto");
                cart = new Cart(idCarrito,idProducto, nombreProducto, cantidaProductos,precioProducto,idUsuario,totalProducto);
                carts.add(cart);
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
        return carts;
    }

    @Override
    public int insert(Cart cart) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1,cart.getIdProducto());
            stmt.setString(2,cart.getNombreProducto());
            stmt.setInt(3,cart.getCantidadProducto());
            stmt.setInt(4,cart.getPrecioProducto());
            stmt.setInt(5,cart.getIdUsuario());
            stmt.setInt(6,cart.getTotalProducto());
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
    public int update(Cart cart) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1,cart.getIdProducto());
            stmt.setString(2,cart.getNombreProducto());
            stmt.setInt(3,cart.getCantidadProducto());
            stmt.setInt(4,cart.getPrecioProducto());
            stmt.setInt(5,cart.getIdUsuario());
            stmt.setInt(6,cart.getTotalProducto());
            stmt.setInt(7,cart.getIdCarrito());
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
    public int delete(Cart cart) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,cart.getIdProducto());
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


    public int delete(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_DELETEFORUSER);
            stmt.setInt(1,usuario.getIdUsuario());
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

    public Cart get(int idCart) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cart cart = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : SqlLite.getConection();
            stmt = conn.prepareStatement(SQL_SELECTFROMID);
            stmt.setInt(1, idCart);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idCarrito = rs.getInt("id_carrito");
                int idProducto = rs.getInt("id_producto");
                String nombreProducto = rs.getString("nombre_producto");
                int cantidaProductos = rs.getInt("cantidad_producto");
                int precioProducto = rs.getInt("precio_producto");
                int idUsuario = rs.getInt("id_usuario");
                int totalProducto = rs.getInt("total_valor_producto");
                cart = new Cart(idCarrito,idProducto,nombreProducto,cantidaProductos,precioProducto,idUsuario,totalProducto);
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
        return cart;
    }
    public Cart get(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cart cart = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : SqlLite.getConection();
            stmt = conn.prepareStatement(SQL_SELECTFORIDPRODUCT);
            stmt.setInt(1, producto.getIdProducto());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idCarrito = rs.getInt("id_carrito");
                int idProducto = rs.getInt("id_producto");
                String nombreProducto = rs.getString("nombre_producto");
                int cantidaProductos = rs.getInt("cantidad_producto");
                int precioProducto = rs.getInt("precio_producto");
                int idUsuario = rs.getInt("id_usuario");
                int totalProducto = rs.getInt("total_valor_producto");
                cart = new Cart(idCarrito,idProducto,nombreProducto,cantidaProductos,precioProducto,idUsuario,totalProducto);
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
        return cart;
    }
    public List<Cart> get(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cart cart = null;
        List<Cart> carts = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECTFROMIDUSER);
            stmt.setInt(1, usuario.getIdUsuario());
            rs = stmt.executeQuery();
            while (rs.next()){
                int idCarrito = rs.getInt("id_carrito");
                int idProducto = rs.getInt("id_producto");
                String nombreProducto = rs.getString("nombre_producto");
                int cantidaProductos = rs.getInt("cantidad_producto");
                int precioProducto = rs.getInt("precio_producto");
                int idUsuario = rs.getInt("id_usuario");
                int totalProducto = rs.getInt("total_valor_producto");
                cart = new Cart(idCarrito,idProducto,nombreProducto,cantidaProductos,precioProducto,idUsuario,totalProducto);
                carts.add(cart);
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
        return carts;
    }


}
