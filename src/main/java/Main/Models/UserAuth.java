package Main.Models;

public class UserAuth {
    private int id;
    private String password;

    public UserAuth(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserAuth() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
