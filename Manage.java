public interface Manage {
    boolean login(String username, String password);
    boolean signup(String name, String password, String phoneNumber, char sex, String dob, String email);
}