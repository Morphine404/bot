package SvBot;

public class User {

    public int idUser;
    private String Name ;
    private String lastName;
    private int age ;
    private String city;
    public Gender gender;
    //public String tg_username;
    //public String tg_id;

    public User(int idUser, String Name, String lastName, int age, String city, Gender gender) {
        this.idUser = idUser;
        this.Name = Name;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.gender =  gender;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }


}

