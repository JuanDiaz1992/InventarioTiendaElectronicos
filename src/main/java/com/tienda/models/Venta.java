package com.tienda.models;

import java.util.Date;
import java.util.List;

public class Venta {
    private int idVenta;
    private double totalVenta;
    private String fecha;

    public Venta(int idVenta, double totalVenta, String fecha) {
        this.idVenta = idVenta;
        this.totalVenta = totalVenta;
        this.fecha = fecha;
    }

    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Venta(String fecha, double totalVenta) {
        this.fecha = fecha;
        this.totalVenta = totalVenta;
    }

    public Venta() {}

    public int getIdVenta() {
        return idVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Venta{");
        sb.append("idVenta=").append(idVenta);
        sb.append(", totalVenta=").append(totalVenta);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }
}
