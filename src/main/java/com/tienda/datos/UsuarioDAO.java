package com.tienda.datos;

import com.tienda.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.tienda.datos.MySql.close;
import static com.tienda.datos.MySql.getConection;

public class UsuarioDAO implements CRUD<Usuario>{
    private static final String SQL_SELECT = "SELECT id_usuario, nombre, password, rol FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (nombre, password, rol) VALUES(?,?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET nombre=?, password=?, rol=? WHERE ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    private static final String SQL_SELECTFROMID = "SELECT id_usuario, nombre, password, rol FROM usuario WHERE id_usuario = ?";
    private static final String SQL_SELECTFROMNAME = "SELECT id_usuario, nombre, password, rol FROM usuario WHERE nombre = ?";


    private Connection conexionTransaccional;
    public UsuarioDAO(){}
    public UsuarioDAO(Connection connection){
        this.conexionTransaccional = connection;
    }

    public List<Usuario> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()){
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                int rol = rs.getInt("rol");
                usuario = new Usuario(idUsuario,nombre,password,rol);
                usuarios.add(usuario);
            }
        }finally {
            try {
                close(rs);
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return usuarios;
    }

    @Override
    public int insert(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,usuario.getNombre());
            stmt.setString(2,usuario.getPassword());
            stmt.setInt(3,usuario.getRol());
            registros = stmt.executeUpdate();
        }finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int update(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,usuario.getNombre());
            stmt.setString(2,usuario.getPassword());
            stmt.setInt(3,usuario.getRol());
            stmt.setInt(4,usuario.getIdUsuario());
            registros = stmt.executeUpdate();
        }finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int delete(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement  stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1,usuario.getIdUsuario());
            registros = stmt.executeUpdate();
        }finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public Usuario get(int idUSuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            stmt = conn.prepareStatement(SQL_SELECTFROMID);
            stmt.setInt(1, idUSuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                int rol = rs.getInt("rol");
                usuario = new Usuario(id,nombre,password,rol);
            }
        }finally {
            try {
                close(rs);
                close(stmt);
                if (this.conexionTransaccional == null){
                    close(conn);
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return usuario;
    }

    public Usuario getFromName(String nombreUsuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConection();
            stmt = conn.prepareStatement(SQL_SELECTFROMNAME);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                int rol = rs.getInt("rol");
                usuario = new Usuario(id,nombre,password,rol);
            }
        }finally {
            try {
                if (rs != null) {
                    close(rs);
                    close(stmt);
                }
                if (this.conexionTransaccional == null){
                    if (rs != null) {
                        close(conn);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return usuario;
    }

}
