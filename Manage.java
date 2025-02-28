public interface Manage {
    boolean login(String username, String password) throws LoginFailedException;;
    boolean signup(String name, String password, String phoneNumber, char sex, String dob, String email);
}