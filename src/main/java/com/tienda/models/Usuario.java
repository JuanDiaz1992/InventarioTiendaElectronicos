package com.tienda.models;
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", rol=").append(rol);
        sb.append('}');
        return sb.toString();
    }
}
