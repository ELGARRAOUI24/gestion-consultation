package ma.iibdcc.gestionconsultation.Dao;

import ma.iibdcc.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IPatientDao extends IDao<Patient, Long> {
    List<Patient> searshByQuery(String query) throws SQLException;
}
