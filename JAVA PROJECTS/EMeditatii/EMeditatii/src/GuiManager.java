public class GuiManager {
    private Authentification auth = new Authentification();
    private User currentUser;
    
    public GuiManager() {
        new LoginWindow(auth,this);
        
    }
    
    public void openMain()
    {
        new MainWindow(this,auth.getRegisteredUsers());

    }
    public void openAuth()
    {
        auth= new Authentification();
        new LoginWindow(auth,this);;
    }
    public void setCurrentUser(User user)
    {
        currentUser=user;
    }
    public User getCurrentUser()
    {
        return currentUser;
    }
   
}

