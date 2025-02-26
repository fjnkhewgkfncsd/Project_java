public class User implements Manage {
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected String dob;
    protected char gender;
    protected Address address;
    static int totalUsers = 0;

    protected User(String name, String email, String phoneNumber, String password, String dob, char gender,Address address) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        totalUsers++;
    }
    public boolean signup(String name,String password,String phonenumber,char sex,String dob,String email){
        return true;
    }
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    public String toString(){
        return "name=" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", gender=" + gender;
    }
    
    //getters 
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public String getDob() {
        return dob;
    }
    public char getGender() {
        return gender;
    }
    public Address getAddress() {
        return address;
    }
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
