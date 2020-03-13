package SvBot;

public class Discript {
    private int idDiscript;
    private String hobby;
    private String profession;
    private String education;
    private String zodiak;

    public Discript (int idDiscript, String  hobby, String profession, String education, String zodiak) {
        this.idDiscript = idDiscript;
        this.hobby = hobby;
        this.profession = profession;
        this.education = education;
        this.zodiak = zodiak;
    }

    public int getIdDescription() {
            return idDiscript;
        }
    public void setIdDescription(int idDescription) {
        this.idDiscript = idDiscript;
    }

    public String getHobby() {
            return hobby;
        }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProfession() {
            return profession;
        }
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

    public String getZodiak() {
        return zodiak;
    }
    public void setZodiak(String zodiak) {
        this.zodiak = zodiak;
    }
}
