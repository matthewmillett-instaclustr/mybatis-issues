package test;

public class User extends UserPrimaryKey {
  public final String fullName;
  public final String phoneNumber;
  public final String city;
  public final boolean isAdmin;

  public User(String id, String fullName, String phoneNumber, String city, boolean isAdmin) {
    super(id);
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.city = city;
    this.isAdmin = isAdmin;
  }
}
