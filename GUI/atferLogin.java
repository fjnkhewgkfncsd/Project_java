package GUI;
import models.User;

public class atferLogin {
    public static void atferLoginFormGUI(User user){
        if(user.getRole().equals("Student")){
            new Student_main_GUI();
        }
    }
}
