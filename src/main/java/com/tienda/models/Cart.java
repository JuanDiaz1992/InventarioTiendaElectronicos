package com.tienda.models;

public class Cart {
    private int idCarrito;
    private int idProducto;
    private String nombreProducto;
    private int cantidadProducto;
    private int precioProducto;
    private int idUsuario;
    private int totalProducto;

    public Cart(int idProducto, String nombreProducto, int cantidadProducto, int precioProducto, int idUsuario, int totalProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
        this.idUsuario = idUsuario;
        this.totalProducto = totalProducto;
    }

    public Cart(int idCarrito, int idProducto, String nombreProducto, int cantidadProducto, int precioProducto, int idUsuario, int totalProducto) {
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
        this.idUsuario = idUsuario;
        this.totalProducto = totalProducto;
    }

    public Cart() {
    }

    public int getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(int totalProducto) {
        this.totalProducto = totalProducto;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(idProducto);
        String nombreRecortado =  nombreProducto.length() > 11 ? nombreProducto.substring(0, 11) + "..." : nombreProducto;
        sb.append(", ").append(nombreRecortado).append('\'');
        sb.append(", ").append(cantidadProducto);
        sb.append(", $").append(precioProducto);
        sb.append(", $").append(totalProducto);
        sb.append(", ").append(idUsuario);
        return sb.toString();
    }
}

