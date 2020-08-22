package pl.labs.Lab4JBL;

import javax.ejb.Singleton;

@Singleton
public class UserManagement {
    public UserManagement()
    {
    }
    private User actuser;
    public void LogIn(String username,String password)
    {
        actuser = new User(username,password,150);
    }
    public boolean UserLogged(){return actuser != null; }
    public User getActUser(){ return actuser; }
}
