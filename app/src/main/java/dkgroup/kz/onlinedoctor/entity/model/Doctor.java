package dkgroup.kz.onlinedoctor.entity.model;

public class Doctor {
    public String name;
    public String surname;
    public String email;
    public Doctor(String name, String surname,String email) {
        this.name = name;
        this.email = email;
        this.surname = surname;
    }

    double rating;
    int reviewSize;
    Speciality speciality;
    String photoUrl;
}
