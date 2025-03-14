package models;

public interface Manage {
    boolean login(String username, String password);
    User signup(String name, String password, String phoneNumber, char sex, String dob, String email, Address address);
}