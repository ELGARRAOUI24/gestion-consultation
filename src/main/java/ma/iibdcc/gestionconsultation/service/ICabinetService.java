package ma.iibdcc.gestionconsultation.service;

import ma.iibdcc.gestionconsultation.entities.Consultation;
import ma.iibdcc.gestionconsultation.entities.Patient;

import java.util.List;

public interface ICabinetService {
    void createPatient(Patient patient);
    void deletePatient(Patient patient);
    void updatePatient(Patient patient);
    List<Patient> getPatients();
    Patient getPatient(Long id);
    List<Patient> searchPatientsByQuery(String query);

    void createConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation);
    List<Consultation> getConsultations();
    Consultation getConsultation(Long id);

}
