package test;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// NOTE: This uses <T> instead of <T extends PrimaryKey> because it also needs to accept String and UUID for backwards
//       compatibility so that usages like: #{temp_storage.id}::ic.temp_storage_id (explicit) will keep working in
//       addition to the newly supported: #{temp_storage}::ic.temp_storage_id (implicit). Full functionality is
//       currently blocked until we can upgrade MyBatis to v3.5.2 for https://github.com/mybatis/mybatis-3/pull/859.

public abstract class AbstractPrimaryKeyTypeHandler<T> extends BaseTypeHandler<T> {

    private final Class<T> type;

    AbstractPrimaryKeyTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        this.type = type;
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final T parameter, final JdbcType jdbcType)
            throws SQLException {

        final String explicitValue = serialize(parameter);

        if (explicitValue != null) {
            ps.setString(i, explicitValue);
            return;
        }

        final Field idField;
        try {
            idField = type.getField("id");
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("Id field does not exist", e);
        }

        final Object idValue;
        try {
            idValue = idField.get(parameter);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Id field could not be read", e);
        }

        final String implicitValue = serialize(idValue);
        ps.setString(i, implicitValue);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return deserialize(rs.getString(columnName));
    }

    @Override
    public T getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return deserialize(rs.getString(columnIndex));
    }

    @Override
    public T getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return deserialize(cs.getString(columnIndex));
    }

    protected abstract T deserialize(final String id);

    protected abstract String serialize(final Object parameter);
}
