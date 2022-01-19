package test;

public class Company {
    public final String companyName;
    public final String companyAccount;
    public final UserPrimaryKey mainUser;

    public Company(String companyName, String companyAccount, UserPrimaryKey mainUser) {
        this.companyName = companyName;
        this.companyAccount = companyAccount;
        this.mainUser = mainUser;
    }
}
