package pl.labs.Lab4JBL;

public class User {
    public User(String name,String password,float accountMoney)
    {
        this.name = name; this.accountMoney = accountMoney; this.password = password;
    }
    public String name;
    public float accountMoney;

    private String password;

    public boolean CheckPassword(String pass)
    {
        return pass.equals(password);
    }
}
