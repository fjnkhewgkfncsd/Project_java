public class themain {
    public static void main(String[] args) {
        User user = new User();
        User newUser = user.signup("Heng11", "password", "1234567890", 'M', "1990-01-01", "cQm3H@example.com", new Address("123 Main St", "Anytown", "CA", "12345","abc",""));
        System.out.println(newUser.toString());
        System.out.println("✅ User created successfully: " + User.totalUsers);
        
    }
}
