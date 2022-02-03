package test;
import static org.junit.Assert.*;

import java.io.Reader;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeClass
  public static void setUp() throws Exception {
    try (Reader reader = Resources.getResourceAsReader("test/mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }


    // prepare in-memory database
    try (SqlSession session = sqlSessionFactory.openSession();
        Connection conn = session.getConnection();
        Reader reader = Resources.getResourceAsReader("test/CreateDB.sql")) {
      ScriptRunner runner = new ScriptRunner(conn);
      runner.setLogWriter(null);
      runner.runScript(reader);
    }
  }

  @Test
  public void shouldGetAUser() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      User firstUser = mapper.getUserById(new UserPrimaryKey("1"));
      assertEquals("John Smith", firstUser.fullName);

      User secondUser = mapper.getUserById(new UserPrimaryKey("2"));
      assertEquals("Jane Doe", secondUser.fullName);

      User thirdUser = mapper.getUserByName("John Smith");
      assertEquals("Sydney", thirdUser.city);

      User fourthUser = mapper.getUserByName("Jane Doe");
      assertEquals("Melbourne", fourthUser.city);
    }
  }

  @Test
  public void shouldGetAnAccount() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      Account firstAccount = mapper.getAccountById("1");
      assertEquals("Company A", firstAccount.companyName);

      Account secondAccount = mapper.getAccountById("2");
      assertEquals("Company B", secondAccount.companyName);
    }
  }

  @Test
  public void shouldGetACompany() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      Company firstCompany = mapper.getCompanyByName("Company A");
      assertEquals(new UserPrimaryKey("1"), firstCompany.mainUser);

      Company secondCompany = mapper.getCompanyByName("Company B");
      assertEquals(new UserPrimaryKey("2"), secondCompany.mainUser);
    }
  }

  @Test
  public void shouldGetUserAdmin() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      boolean isAdmin = mapper.isUserAdmin(new UserPrimaryKey("1"));
      assertFalse(isAdmin);
    }
  }

  @Test
  public void shouldGetUsers() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      List<User> users = mapper.getUsers();
      assertEquals(users.size(), 2);
    }
  }

  @Test
  public void shouldGetTasks() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      List<Status> status = mapper.getStatusFromStatus(Status.QUEUED);
      assertEquals(status.size(), 0);

      status = mapper.getStatusFromStatusName(Status.QUEUED);
      assertEquals(status.size(), 1);

      status = mapper.getStatusFromDisplayName(Status.QUEUED);
      assertEquals(status.size(), 0);

      status = mapper.getStatusFromStatusNameString(Status.QUEUED.name());
      assertEquals(status.size(), 1);
    }
  }

  @Test
  public void shouldGetAllCompanies() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      List<Company> companies = mapper.getCompaniesByName(Set.of("Company A", "Company B"));
      assertEquals(companies.size(), 2);

      companies = mapper.getCompaniesByName(Set.of());
      assertEquals(companies.size(), 0);
    }
  }
}
