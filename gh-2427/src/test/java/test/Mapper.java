package test;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Mapper {
  @Select(
          "SELECT id, full_name, phone_number, city, is_admin FROM users WHERE id = #{user.name}"
  )
  @ConstructorArgs({
          @Arg(column = "id", javaType = String.class),
          @Arg(column = "full_name", javaType = String.class),
          @Arg(column = "phone_number", javaType = String.class),
          @Arg(column = "city", javaType = String.class),
          @Arg(column = "is_admin", javaType = boolean.class)
  })
  User getUserById(@Param("user") final UserPrimaryKey user);

  @Select(
          "SELECT id, full_name, phone_number, city, is_admin FROM users WHERE full_name = #{user.name}"
  )
  @ConstructorArgs({
          @Arg(column = "id", javaType = String.class),
          @Arg(column = "full_name", javaType = String.class),
          @Arg(column = "phone_number", javaType = String.class),
          @Arg(column = "city", javaType = String.class),
          @Arg(column = "is_admin", javaType = boolean.class)
  })
  User getUserByName(@Param("user") final String fullName);

}
