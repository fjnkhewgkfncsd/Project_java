package models;
import exceptions.*;
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

    public boolean login(String email, String password){
        try{
            String[] inputStrings = {email,password};
            new CheckEmptyStringException(inputStrings);
        }catch(CheckEmptyStringException e){
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("✅ Login successful for: " + username);
        return true;
    }
    public User signup(String name,String password,String phonenumber,char sex,String dob,String email,Address address) {
        return null;
    }
    //toString 
    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Admin a = (Admin) obj;
        return a.email == this.email;
    }
}
