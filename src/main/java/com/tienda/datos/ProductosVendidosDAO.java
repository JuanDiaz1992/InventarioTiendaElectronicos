package com.tienda.datos;

import java.sql.SQLException;
import java.util.List;

public class ProductosVendidosDAO implements ICRUD{
    private static final String SQL_SELECT = "SELECT id, id_venta, id_producto, cantidad_producto FROM productos_vendidos";
    private static final String SQL_INSERT = "INSERT INTO productos_vendidos (id_producto, cantidad_producto) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE productos_vendidos SET id_producto=?, cantidad_producto=? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM productos_vendidos WHERE id = ?";
    private static final String SQL_SELECTFROMID = "SELECT id, id_venta, id_producto, cantidad_producto FROM productos_vendidos WHERE id = ?";
    private static final String SQL_SELECTFROMIDSALE = "SELECT id, id_venta, id_producto, cantidad_producto FROM productos_vendidos WHERE id_venta = ?";

    @Override
    public List select() throws SQLException {
        return List.of();
    }

    @Override
    public int insert(Object object) throws SQLException {
        return 0;
    }

    @Override
    public int update(Object object) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Object object) throws SQLException {
        return 0;
    }

    @Override
    public Object get(int id) throws SQLException {
        return null;
    }
}
