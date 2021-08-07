package ha.hoclaptrinhweb.dao.Impl;

import ha.hoclaptrinhweb.dao.GenericDAO;
import ha.hoclaptrinhweb.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {
    public Connection getConnection() {
        try {
            String DRIVER = "com.mysql.jdbc.Driver";
            String URL = "jdbc:mysql://localhost:3306/petfoodonline";
            String USERNAME = "root";
            String PASSWORD = "nguyenvanha1108";
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    ;

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameter) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<T> result = new ArrayList<>();
        connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameter);
            rs = statement.executeQuery();
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }
            return result;
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e2) {
                return null;
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameter) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameter);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(String sql, Object... parameter) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameter);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }

    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return 0;
            }
        }
    }

    private void setParameter(PreparedStatement statement, Object... parameter) {
        try {
            for (int i = 0; i < parameter.length; i++) {
                int index = i + 1;
                statement.setObject(index, parameter[i]);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
