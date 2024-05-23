package com.tienda.models;

public class DetalleVenta {
    private int IdDetalleVenta;
    private String factura;
    private int idProducto;
    private int cantidadProducto;
    private int precio;
    private int importe;

    public DetalleVenta(int IdDetalleVenta, String factura, int idProducto, int cantidadProducto, int precio, int importe) {
        this.IdDetalleVenta = IdDetalleVenta;
        this.factura = factura;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
        this.precio = precio;
        this.importe = importe;
    }

    public DetalleVenta(String factura, int idProducto, int cantidadProducto, int precio, int importe) {
        this.factura = factura;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
        this.precio = precio;
        this.importe = importe;
    }

    public DetalleVenta() {
    }

    public int getIdDetalleVenta() {
        return IdDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.IdDetalleVenta = idDetalleVenta;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(IdDetalleVenta);
        sb.append(", ").append(idProducto);
        sb.append(", ").append(cantidadProducto);
        sb.append(", ").append(precio);
        sb.append(", ").append(importe);
        sb.append(", ").append(factura);
        return sb.toString();
    }
}
