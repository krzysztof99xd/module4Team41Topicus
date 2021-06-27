package lendaryModel;

public class User {
    private String email;
    private  String password;

    public User(){

    }
    /**
     * get users passsword
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * gets users email
     * @return email
     */
    public String getEmail() {
        return email;
    }
   /**
     * sets users email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * sets users password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
