package test;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

@MappedTypes(User.class)
public class UserTypeHandler extends BaseTypeHandler<User> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, User user, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public User getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return new User(
                resultSet.getString("id"),
                resultSet.getString("full_name"),
                resultSet.getString("phone_number"),
                resultSet.getString("city"),
                resultSet.getBoolean("is_admin")
        );
    }

    @Override
    public User getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return new User(
                resultSet.getString("id"),
                resultSet.getString("full_name"),
                resultSet.getString("phone_number"),
                resultSet.getString("city"),
                resultSet.getBoolean("is_admin")
        );
    }

    @Override
    public User getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new User(
                callableStatement.getString("id"),
                callableStatement.getString("full_name"),
                callableStatement.getString("phone_number"),
                callableStatement.getString("city"),
                callableStatement.getBoolean("is_admin")
        );
    }
}