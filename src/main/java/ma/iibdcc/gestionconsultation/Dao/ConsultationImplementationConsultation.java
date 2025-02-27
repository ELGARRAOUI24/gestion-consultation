package ma.iibdcc.gestionconsultation.Dao;

import ma.iibdcc.gestionconsultation.entities.Consultation;
import ma.iibdcc.gestionconsultation.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationImplementationConsultation implements IConsultationDao {
    @Override
    public void create(Consultation consultation) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Consultation(dateConsultation,description,patientId) VALUES (?,?,?)");
        ps.setDate(1, consultation.getDateConsultation());
        ps.setString(2, consultation.getDescription());
        ps.setLong(3, consultation.getPatient().getId());
        ps.executeUpdate();
    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE Consultation SET dateConsultation=?,description=?,patientId=? WHERE Id=?");
        ps.setDate(1,consultation.getDateConsultation());
        ps.setString(2,consultation.getDescription());
        ps.setLong(3,consultation.getPatient().getId());
        ps.setLong(4,consultation.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Consultation consultation) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Consultation WHERE Id = ?");
        ps.setLong(1, consultation.getId());
        ps.executeUpdate();
    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Consultation");
        ResultSet rs = ps.executeQuery();
        List<Consultation> consultations = new ArrayList<>();
        while (rs.next()) {
            Consultation c = new Consultation();
            c.setId(rs.getLong("id"));
            c.setDateConsultation(rs.getDate("dateConsultation"));
            c.setDescription(rs.getString("description"));
            c.setPatientId(rs.getLong("patientId"));
            consultations.add(c);
        }
        return consultations;
    }

    @Override
    public Consultation findById(Long id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Consultation WHERE Id = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        Consultation consultation = new Consultation();
        while (rs.next()) {
            consultation.setId(rs.getLong("id"));
            consultation.setDateConsultation(rs.getDate("dateConsultation"));
            consultation.setDescription(rs.getString("description"));
            consultation.setPatientId(rs.getLong("patientId"));
        }
        return consultation;
    }
}
