package models;

import java.util.List;

public class Admin{
    String email;
    private String password;

    // Default admin credentials
    private static final String DEFAULT_EMAIL = "admin@gmail.com";
    private static final String DEFAULT_PASSWORD = "admin123";


    public Admin() {
        // Default admin credentials
        this.email = DEFAULT_EMAIL;
        this.password = DEFAULT_PASSWORD;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public boolean login(String email, String password) {
        return email.equals(DEFAULT_EMAIL) && password.equals(DEFAULT_PASSWORD);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isDefaultAdmin() {
        return this.email.equals(DEFAULT_EMAIL) && this.password.equals(DEFAULT_PASSWORD);
    }
    public boolean isValidAdmin() {
        return this.email != null && this.password != null && !this.email.isEmpty() && !this.password.isEmpty();
    }
    public boolean isValidEmail() {
        return this.email != null && this.email.contains("@") && this.email.contains(".");
    }
    public boolean isValidPassword() {
        return this.password != null && this.password.length() >= 8;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Admin a = (Admin) obj;
        return a.email.equals(this.email);
    }
}
