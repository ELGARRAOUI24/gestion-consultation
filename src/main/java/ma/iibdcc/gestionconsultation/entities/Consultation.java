package ma.iibdcc.gestionconsultation.entities;

import java.sql.Date;

public class Consultation {
    private Long id;
    private Date dateConsultation;
    private String description;
    private Long patientId;
    private Patient patient;

    public Consultation() {
    }

    public Consultation(Patient patient, String description, Date dateConsultation) {
        this.patient = patient;
        this.description = description;
        this.dateConsultation = dateConsultation;
    }

    public Long getId() {
        return id;
    }

    public Date getDateConsultation() { return dateConsultation; }

    public String getDescription() {
        return description;
    }

    public Long getPatientId() { return patientId; }

    public Patient getPatient() {
        return patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPatientId(Long setPatientId) { this.patientId = setPatientId; }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", dateConsultation=" + dateConsultation +
                ", description='" + description + '\'' +
                ", patient=" + patient +
                '}';
    }
}
