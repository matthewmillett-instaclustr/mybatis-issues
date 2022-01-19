package test;

import org.apache.ibatis.annotations.*;

public interface Mapper {
  @Select(
          "SELECT id, full_name, phone_number, city, is_admin FROM users WHERE id = #{user.id}"
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
          "SELECT id, full_name, phone_number, city, is_admin FROM users WHERE full_name = #{user}"
  )
  @ConstructorArgs({
          @Arg(column = "id", javaType = String.class),
          @Arg(column = "full_name", javaType = String.class),
          @Arg(column = "phone_number", javaType = String.class),
          @Arg(column = "city", javaType = String.class),
          @Arg(column = "is_admin", javaType = boolean.class)
  })
  User getUserByName(@Param("user") final String fullName);

  @Select(
          "SELECT id, account_name, company_name, city FROM accounts WHERE id = #{accountId}"
  )
  @ConstructorArgs({
          @Arg(column = "id", javaType = String.class),
          @Arg(column = "account_name", javaType = String.class),
          @Arg(column = "company_name", javaType = String.class),
          @Arg(column = "city", javaType = String.class),
  })
  Account getAccountById(@Param("accountId") final String accountId);

  @Select(
          "SELECT company_name, company_account, main_user FROM companies WHERE company_name = #{companyName}"
  )
  @ConstructorArgs({
          @Arg(column = "company_name", javaType = String.class),
          @Arg(column = "company_account", javaType = String.class),
          @Arg(column = "main_user", javaType = UserPrimaryKey.class)
  })
  Company getCompanyByName(@Param("companyName") final String companyName);
}
