package com.tienda.datos;

import java.sql.SQLException;
import java.util.List;

public interface ICRUD<O> {
    public List<O> select() throws SQLException;
    public int insert(O object) throws SQLException;
    public int update(O object) throws SQLException;
    public int delete(O object) throws SQLException;
    public O get(int id) throws SQLException;
}
