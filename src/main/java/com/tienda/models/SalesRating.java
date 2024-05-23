package com.tienda.models;

public class SalesRating {
    public Usuario user;
    public int idUser;
    public int cantidadVentas;
    public int valorTotalVentas;

    public SalesRating(Usuario user, int idUser, int cantidadVentas, int valorTotalVentas) {
        this.user = user;
        this.idUser = idUser;
        this.cantidadVentas = cantidadVentas;
        this.valorTotalVentas = valorTotalVentas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(user.getNombre());
        sb.append(", ").append(user.getIdUsuario());
        sb.append(", ").append(cantidadVentas);
        sb.append(", ").append(valorTotalVentas);
        return sb.toString();
    }
}
