package test;

public class Account {
    public final String id;
    public final String accountName;
    public final String companyName;
    public final String city;

    public Account(String id, String accountName, String companyName, String city) {
        this.id = id;
        this.accountName = accountName;
        this.companyName = companyName;
        this.city = city;
    }
}
