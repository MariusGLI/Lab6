package Problema1;
import java.time.LocalDate;

public class Angajat {
    private String nume;
    private String postul;
    private LocalDate data_angajarii;
    private float salariul;

    // Constructori
    public Angajat(String nume, String postul, LocalDate data_angajarii, float salariul) {
        this.nume = nume;
        this.postul = postul;
        this.data_angajarii = data_angajarii;
        this.salariul = salariul;
    }

    public Angajat() {}

    // Getteri și Setteri
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPostul() {
        return postul;
    }

    public void setPostul(String postul) {
        this.postul = postul;
    }

    public LocalDate getData_angajarii() {
        return data_angajarii;
    }

    public void setData_angajarii(LocalDate data_angajarii) {
        this.data_angajarii = data_angajarii;
    }

    public float getSalariul() {
        return salariul;
    }

    public void setSalariul(float salariul) {
        this.salariul = salariul;
    }

    // Metoda toString pentru a afișa datele angajatului
    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", postul='" + postul + '\'' +
                ", data_angajarii=" + data_angajarii +
                ", salariul=" + salariul +
                '}';
    }
}

