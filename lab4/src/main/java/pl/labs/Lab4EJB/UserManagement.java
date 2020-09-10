package pl.labs.Lab4EJB;

import javax.ejb.Singleton;

@Singleton
public class UserManagement {
    public UserManagement()
    {
    }
    private User actuser;
    public void LogIn(String username,String password)
    {
        actuser = new User(username,password,50);
    }
    public boolean UserLogged(){return actuser != null; }
    public User getActUser(){ return actuser; }
}
