package com.tienda.models;

import java.util.Objects;

public class Usuario{
    private int idUsuario;
    private String nombre;
    private String password;
    private int rol;

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(int idUsuario, String nombre, String password, int rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String nombre, String password, int rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(){}

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Usuario usuario)) return false;
        return getIdUsuario() == usuario.getIdUsuario() && getRol() == usuario.getRol() && Objects.equals(getNombre(), usuario.getNombre()) && Objects.equals(getPassword(), usuario.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsuario(), getNombre(), getPassword(), getRol());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(idUsuario);
        sb.append(", ").append(nombre);
        sb.append(", ").append(rol);
        return sb.toString();
    }
}
