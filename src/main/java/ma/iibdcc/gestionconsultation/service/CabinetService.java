package ma.iibdcc.gestionconsultation.service;

import ma.iibdcc.gestionconsultation.Dao.IConsultationDao;
import ma.iibdcc.gestionconsultation.Dao.IPatientDao;
import ma.iibdcc.gestionconsultation.entities.Consultation;
import ma.iibdcc.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public class CabinetService implements ICabinetService {

    private IPatientDao patientDao;
    private IConsultationDao consultationDao;

    public CabinetService(IPatientDao patientDao, IConsultationDao consultationDao) {
        this.patientDao = patientDao;
        this.consultationDao = consultationDao;
    }

    @Override
    public void createPatient(Patient patient) {
        try {
            patientDao.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        try {
            patientDao.delete(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        try {
            patientDao.update(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = null;
        try {
            patients = patientDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient getPatient(Long id) {
        Patient patient = null;
        try {
            patient = patientDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public List<Patient> searchPatientsByQuery(String query) {
        List<Patient> patients = null;
        try {
            patients = patientDao.searshByQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public void createConsultation(Consultation consultation) {
        try {
            consultationDao.create(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try {
            consultationDao.delete(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try {
            consultationDao.update(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> getConsultations() {
        List<Consultation> consultations = null;
        try {
            consultations = consultationDao.findAll();
            consultations.forEach(consultation -> {
                try {
                    consultation.setPatient(patientDao.findById(consultation.getPatientId()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultations;
    }

    @Override
    public Consultation getConsultation(Long id) {
        Consultation consultation = null;
        try {
            consultation = consultationDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultation;
    }
}
