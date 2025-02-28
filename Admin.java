public class Admin implements Manage{
    String username;
    String email;
    int phoneNumber;
    private String password;
    // For signup
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

    public boolean login(String email, String password) throws LoginFailedException {
        if (email == null || password == null) {
            throw new LoginFailedException("❌ Email or password cannot be null!");
        }
        if (!this.email.equalsIgnoreCase(email) || !this.password.equals(password)) {
            throw new LoginFailedException("❌ Invalid email or password!");
        }
        System.out.println("✅ Login successful for: " + name);
        return true;
    }
    public boolean signup(String name,String password,String phonenumber,char sex,String dob,String email){
        return true;
    }
}
