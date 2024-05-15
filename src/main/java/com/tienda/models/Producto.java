package com.tienda.models;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int precio;
    private int stock;

    public Producto(int idProducto, String nombre, String descripcion, int precio, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String nombre, String descripcion ,int precio,int stock) {
        this.stock = stock;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Producto() {}

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(idProducto);
        String nombreRecortado =  nombre.length() > 12 ? nombre.substring(0, 12) + "..." : nombre;
        sb.append(", ").append(nombreRecortado);
        String textoAcortado =  descripcion.length() > 12 ? descripcion.substring(0, 12) + "..." : descripcion;
        sb.append(", ").append(textoAcortado);
        sb.append(", $").append(precio);
        sb.append(", ").append(stock);
        return sb.toString();
    }
}
