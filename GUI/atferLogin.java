package GUI;
import models.*;

public class atferLogin {
    public static void atferLoginFormGUI(User user){
        if(user.getRole().equals("Student")){
            if(user instanceof Student)
            new Student_main_GUI((Student) user);
        }
        if(user.getRole().equals("Staff")){
            if(user instanceof Staff)
            new StaffForm((Staff) user);
        }
    }
}
