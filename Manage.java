public interface Manage {
    boolean login(String username, String password) throws LoginFailedException;;
    User signup(String name, String password, String phoneNumber, char sex, String dob, String email, Address address);
}