package com.tienda.models;

import java.util.Date;
import java.util.List;

public class Venta {
    private int idVenta;
    private int totalVenta;
    private String fecha;
    private int idUsuario;
    private String numFactura;

    public Venta(int idVenta, int totalVenta, String fecha, int idUsuario, String numFactura) {
        this.idVenta = idVenta;
        this.totalVenta = totalVenta;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.numFactura = numFactura;

    }

    public Venta(int totalVenta, String fecha, int idUsuario, String numFactura) {
        this.totalVenta = totalVenta;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.numFactura = numFactura;

    }

    public Venta() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(int totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(idVenta);
        sb.append(", ").append(totalVenta);
        sb.append(", ").append(fecha);
        sb.append(", ").append(idUsuario);
        sb.append(", ").append(numFactura);
        return sb.toString();
    }
}
