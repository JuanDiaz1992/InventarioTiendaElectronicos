package com.tienda.models;

import java.util.Date;
import java.util.List;

public class Venta {
    private int idVenta;
    private List<Producto> productos;
    private double totalVenta;
    private Date fecha;

    public Venta(int idVenta, List<Producto> productos, double totalVenta, Date fecha) {
        this.idVenta = idVenta;
        this.productos = productos;
        this.totalVenta = totalVenta;
        this.fecha = fecha;
    }

    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Venta(Date fecha, double totalVenta, List<Producto> productos) {
        this.fecha = fecha;
        this.totalVenta = totalVenta;
        this.productos = productos;
    }

    public Venta() {}

    public int getIdVenta() {
        return idVenta;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Venta{");
        sb.append("idVenta=").append(idVenta);
        sb.append(", productos=").append(productos);
        sb.append(", totalVenta=").append(totalVenta);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }
}
