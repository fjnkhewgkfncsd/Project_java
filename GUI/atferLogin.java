package GUI;
import models.*;

import database.FetchData;

public class atferLogin {
    public static void atferLoginFormGUI(User user){
        if(user.getRole().equals("Student")){
            if(user instanceof Student);
            Student student = (Student) user;
            FetchData.fetchCourse(FetchData.fetchCourse_id(student.getId()),student);

            new Student_main_GUI(student);
        }else if(user.getRole().equals("Staff")){
            if(user instanceof Staff)
            new StaffForm((Staff) user);
        }else if (user.getRole().equals("Lecturer")){
            if(user instanceof Lecturer);
            Lecturer lecturer = (Lecturer) user;
            System.err.println(lecturer.getId());
            new LecturerForm(FetchData.fetchLecturer(((Lecturer) user).getId()));

        }
    }
}
