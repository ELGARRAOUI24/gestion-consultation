package ma.iibdcc.gestionconsultation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.iibdcc.gestionconsultation.Dao.ConsultationImplementationConsultation;
import ma.iibdcc.gestionconsultation.Dao.PatientImplementationPatient;
import ma.iibdcc.gestionconsultation.entities.Consultation;
import ma.iibdcc.gestionconsultation.entities.Patient;
import ma.iibdcc.gestionconsultation.service.CabinetService;
import ma.iibdcc.gestionconsultation.service.ICabinetService;

import java.net.URL;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    @FXML private DatePicker dateConsultation;
    @FXML private ComboBox<Patient> comboPatients;
    @FXML private TextArea textDescription;
    @FXML private TableView<Consultation> tableConsultation;
    @FXML private TableColumn<Consultation, Long> colmunId;
    @FXML private TableColumn<Consultation, Date> colmunDateConsul;
    @FXML private TableColumn<Consultation, Long> colmunPatient;
    @FXML private TableColumn<Consultation, String> colmunDescription;

    private Consultation editConsultation;
    private ICabinetService cabinetService;
    private final ObservableList<Patient> patients = FXCollections.observableArrayList();
    private final ObservableList<Consultation> consultations = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService = new CabinetService(new PatientImplementationPatient(), new ConsultationImplementationConsultation());
        comboPatients.setItems(patients);
        patients.setAll(cabinetService.getPatients());
        colmunId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmunDateConsul.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        colmunDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colmunPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        loadConsultation();
        tableConsultation.setItems(consultations);
        tableConsultation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                dateConsultation.setValue(newValue.getDateConsultation().toLocalDate());
                comboPatients.setValue(newValue.getPatient());
                textDescription.setText(newValue.getDescription());
                editConsultation = newValue;
            }
        });
    }

    public void addConsultation(){
        if(dateConsultation.getValue() == null || comboPatients.getValue() == null || Objects.equals(textDescription.getText(), ""))
            return;
        Consultation consultation = new Consultation();
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPatient(comboPatients.getSelectionModel().getSelectedItem());
        consultation.setDescription(textDescription.getText());
        cabinetService.createConsultation(consultation);
        loadConsultation();
    }

    public void updatePatient() {
        if(dateConsultation.getValue() == null || comboPatients.getValue() == null || Objects.equals(textDescription.getText(), ""))
            return;
        editConsultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        editConsultation.setPatient(comboPatients.getSelectionModel().getSelectedItem());
        editConsultation.setDescription(textDescription.getText());
        cabinetService.updateConsultation(editConsultation);
        loadConsultation();
    }

    public void deleteConsultation(){
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        cabinetService.deleteConsultation(consultation);
        loadConsultation();
    }

    private void loadConsultation() {
        consultations.setAll(cabinetService.getConsultations());
        dateConsultation.setValue(null);
        textDescription.setText("");
        comboPatients.setValue(null);
    }
}
