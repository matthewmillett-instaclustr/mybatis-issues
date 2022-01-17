package test;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@MappedTypes(UserPrimaryKey.class)
public class UserPrimaryKeyHandler extends AbstractPrimaryKeyTypeHandler<UserPrimaryKey> {

    private final Constructor<UserPrimaryKey> constructor;
    private final Class<UserPrimaryKey> type = UserPrimaryKey.class;

    public UserPrimaryKeyHandler() {
        super(UserPrimaryKey.class);

        try {
            this.constructor = type.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Id field does not have a constructor matching: (String)", e);
        }
    }

    @Override
    protected UserPrimaryKey deserialize(final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        try {
            return constructor.newInstance(id);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to construct primary key type", e);
        }
    }

    @Override
    protected String serialize(final Object parameter) {
        if (parameter.getClass() == String.class) {
            return (String) parameter;
        }

        return null;
    }
}
