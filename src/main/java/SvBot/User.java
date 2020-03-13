package SvBot;

public class User {

    private int idUser;
    private String Name ;
    private String lastName;
    private String age ;
    private String city;
    public String gender;
    public String tg_username;
    public String tg_id;

    public User(int idUser, String Name, String lastName, String age, String city, String gender) {
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

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int stage;
    public User(String id, String chat_id, String username)
    {
        stage = 1;
        tg_id = id;
        tg_username = username;
    }

   /* public void (String, int) User(String id)
    {
        if (stage == 1)
            return ("Введите отображаемое имя:", stage);
        if (stage == 2)
            return ("Введите отображаемую фамилию:", stage);
        if (stage == 3)
            return ("Введите возраст:", stage);
        if (stage == 4)
            return ("Введите Ваш город:", stage);
        if (stage == 5)
            return ("Введите Ваш пол:", stage);
          //  else
        //return ("Отправьте боту Ваше фото:", stage);
    }


    public Boolean SetParam(String param)
    {
        if (stage == 1)
            Name = param;
        if (stage == 2)
            lastName = param;
        if (stage == 3)
            age = param;
        if (stage == 4)
            city = param;
        if (stage == 5)
            gender = param;
        //if (stage == 6)
         //   photo = param;
        stage++;
        return true;
    }*/

}

