package com.tienda.models;

public class ProductosVendidos {
    private int id;
    private int idVenta;
    private int idProducto;
    private int cantidadProducto;

    public ProductosVendidos(int id, int idVenta, int idProducto, int cantidadProducto) {
        this.id = id;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public ProductosVendidos(int idVenta, int idProducto, int cantidadProducto) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public ProductosVendidos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
