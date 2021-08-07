package ha.hoclaptrinhweb.dao;

import ha.hoclaptrinhweb.mapper.RowMapper;

import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    Long insert(String sql, Object... parameter);
    void update(String sql, Object... parameter);
    int count(String sql, Object... parameters);
}
