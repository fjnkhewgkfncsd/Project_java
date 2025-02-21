public class Admin implements Manage{
    String username;
    String email;
    int phoneNumber;
    private String password;
    // For register
    public Admin(String username, String email, int phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    //For Login
    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean login(String email, String password) {
        if(this.email.equals(email) && this.password.equals(password)){
            return true;
        }
        return false;
    }
    public boolean signup(String name,String password,String phonenumber,char sex,String dob,String email){
        return true;
    }
}
