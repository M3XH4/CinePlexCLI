public class Admin {
    private String username;
    private String password;

    public Admin() {
        setUsername("manager");
        setPassword("man123");
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
