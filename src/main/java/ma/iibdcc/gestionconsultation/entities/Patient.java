package ma.iibdcc.gestionconsultation.entities;

import java.util.List;

public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private List<Consultation> consultationList;

    public Patient() {
    }

    public Patient(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setConsultationList(List<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    @Override
    public String toString() {
        return nom +" "+ prenom;
    }
}
