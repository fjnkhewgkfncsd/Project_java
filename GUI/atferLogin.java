package GUI;
import models.*;

import database.FetchData;

public class atferLogin {
    public static void atferLoginFormGUI(User user){
        if(user.getRole().equals("Student")){
            if(user instanceof Student);
            Student student = (Student) user;
            System.err.println(student.getId());
            FetchData.fetchCourse(FetchData.fetchCourse_id(student.getId()),student);

            new Student_main_GUI(student);
        }
        if(user.getRole().equals("Staff")){
            if(user instanceof Staff)
            new StaffForm((Staff) user);
        }
    }
}
