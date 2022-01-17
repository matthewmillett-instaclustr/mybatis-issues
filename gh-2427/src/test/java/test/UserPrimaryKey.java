package test;

import java.util.Objects;

public class UserPrimaryKey implements PrimaryKey {
    public final String id;

    public UserPrimaryKey(final String id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPrimaryKey)) return false;

        final UserPrimaryKey that = (UserPrimaryKey) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
