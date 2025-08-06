public class User {
    private int id;
    private String username;
    private String password;
    private String bio;
    private Menfess[] menfess;
    private int i=0;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.bio = "";
        this.menfess = new Menfess[100];
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public boolean ValidateLogin(String username, String password){
        if (username.equals(this.username)  && password.equals(this.password)){
            return true;
        }
        return false;  
    }


    public void setBio(String bio) {
        this.bio = bio;
    }

    public void addMenfess(Menfess newMenfess) {
        menfess[i] = newMenfess;
        i++;
    }

    public Menfess[] getMenfess() {
        return menfess;
    }
}