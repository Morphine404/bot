package SvBot;

import org.telegram.abilitybots.api.db.DBContext;

import java.util.HashMap;
import java.util.Map;

public class User<S> {

    public int idUser;
    public String Name ;
    public int age ;
    public String city;
    //public Gender gender;
    //private String hobby;
    public String zodiak;
    public boolean like;
    //public String tg_username;
    //public String tg_id;

    public User() {
        this.idUser = idUser;
        this.Name = Name;
        this.age = age;
        this.city = city;
        this.zodiak = zodiak;
        this.like = like;
    }
    /*public  int getID(Integer idUser, String name, Integer age, boolean like){
        return this.idUser;
    }*/
   /* public static String getUser(String message, User user) throws IOException {
       // URL url =
        return message;
    }*/
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getName(String n) {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
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
    public String getZodiak() {
        return zodiak;
    }
    public void setZodiak(String zodiak) {
        this.zodiak = zodiak;
    }
    public boolean getLike(){
        return like;
    }
    public void setLike(boolean like){
        this.like=like;
    }
    public boolean isLike() {
        return like;
    }
    /*public Map<Boolean,String> likeMap = new HashMap<>();
    void  Like(DBContext db){
        likeMap = db.getMap("Like");
    }
    public Map<Integer, String> UserMap = new HashMap<>();

    User(DBContext db) {
        UserMap = db.getMap("Users");
    }

    public void add(String s) {
        UserMap.put(UserMap.size(), s);
    }

    String[] get() {
        String[] myArray = new String[UserMap.size()];
        ;
        for (int i : UserMap.keySet()) {
            myArray[i] = (UserMap.get(i));
        }
        return myArray;
    }

    public void remove() {
        for (int i : UserMap.keySet()) {
            UserMap.remove(i);
        }
    }*/
}
enum Gender {
    male,
    female
}

