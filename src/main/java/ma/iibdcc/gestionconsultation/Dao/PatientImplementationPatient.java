package ma.iibdcc.gestionconsultation.Dao;

import ma.iibdcc.gestionconsultation.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientImplementationPatient implements IPatientDao {
    @Override
    public void create(Patient patient) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Patient(Nom,Prenom,Telephone) VALUES (?,?,?)");
        ps.setString(1, patient.getNom());
        ps.setString(2, patient.getPrenom());
        ps.setString(3, patient.getTelephone());
        ps.executeUpdate();
    }

    @Override
    public void update(Patient patient) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE Patient SET Nom=?,Prenom=?,Telephone=? WHERE Id=?");
        ps.setString(1,patient.getNom());
        ps.setString(2,patient.getPrenom());
        ps.setString(3,patient.getTelephone());
        ps.setLong(4,patient.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Patient patient) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Patient WHERE Id = ?");
        ps.setLong(1, patient.getId());
        ps.executeUpdate();
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Patient");
        ResultSet rs = ps.executeQuery();
        List<Patient> patients = new ArrayList<Patient>();
        while (rs.next()) {
            Patient p = new Patient();
            p.setId(rs.getLong("Id"));
            p.setNom(rs.getString("Nom"));
            p.setPrenom(rs.getString("Prenom"));
            p.setTelephone(rs.getString("Telephone"));
            patients.add(p);
        }
        return patients;
    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Patient WHERE Id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Patient patient = new Patient();
        while (rs.next()) {
            patient.setId(rs.getLong("Id"));
            patient.setNom(rs.getString("Nom"));
            patient.setPrenom(rs.getString("Prenom"));
            patient.setTelephone(rs.getString("Telephone"));
        }
        return patient;
    }

    @Override
    public List<Patient> searshByQuery(String query) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Patient WHERE nom LIKE ? OR prenom LIKE ?");
        ps.setString(1, "%"+ query + "%");
        ps.setString(2, "%"+ query + "%");
        ResultSet rs = ps.executeQuery();
        List<Patient> patients = new ArrayList<Patient>();
        while (rs.next()) {
            Patient p = new Patient();
            p.setId(rs.getLong("Id"));
            p.setNom(rs.getString("Nom"));
            p.setPrenom(rs.getString("Prenom"));
            p.setTelephone(rs.getString("Telephone"));
            patients.add(p);
        }
        return patients;
    }
}
