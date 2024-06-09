public class Admin {
    private String username;
    private String password;

    private boolean isActive;

    public Admin() {
        setUsername("manager");
        setPassword("man123");
        setActive(false);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
