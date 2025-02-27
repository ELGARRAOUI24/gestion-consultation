package ma.iibdcc.gestionconsultation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.iibdcc.gestionconsultation.Dao.ConsultationImplementationConsultation;
import ma.iibdcc.gestionconsultation.Dao.IPatientDao;
import ma.iibdcc.gestionconsultation.Dao.PatientImplementationPatient;
import ma.iibdcc.gestionconsultation.entities.Patient;
import ma.iibdcc.gestionconsultation.service.CabinetService;
import ma.iibdcc.gestionconsultation.service.ICabinetService;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML private TextField textNom;
    @FXML private TextField textPrenom;
    @FXML private TextField textTelephone;
    @FXML private TextField textSearch;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, Long> columnId;
    @FXML private TableColumn<Patient, String> columnnom;
    @FXML private TableColumn<Patient, String> columnPrenom;
    @FXML private TableColumn<Patient, String> columnTelephone;
    private Patient editPatient;

    private ICabinetService cabinetService;
    private final ObservableList<Patient> patients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     cabinetService = new CabinetService(new PatientImplementationPatient(), new ConsultationImplementationConsultation());
     columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
     columnnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
     columnTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
     loadPatient();
     tablePatients.setItems(patients);
     textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
         patients.setAll(cabinetService.searchPatientsByQuery(newValue));
     });
     tablePatients.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if(newValue != null) {
            textNom.setText(newValue.getNom());
            textPrenom.setText(newValue.getPrenom());
            textTelephone.setText(newValue.getTelephone());
            editPatient = newValue;
        }
     });
    }

    public void addPatient() {
        if(Objects.equals(textNom.getText(), "") || Objects.equals(textPrenom.getText(), "") || Objects.equals(textTelephone.getText(), ""))
            return;
        Patient patient = new Patient();
        patient.setNom(textNom.getText());
        patient.setPrenom(textPrenom.getText());
        patient.setTelephone(textTelephone.getText());
        cabinetService.createPatient(patient);
        loadPatient();
    }

    public void updatePatient() {
        if(Objects.equals(textNom.getText(), "") || Objects.equals(textPrenom.getText(), "") || Objects.equals(textTelephone.getText(), ""))
            return;
        editPatient.setNom(textNom.getText());
        editPatient.setPrenom(textPrenom.getText());
        editPatient.setTelephone(textTelephone.getText());
        cabinetService.updatePatient(editPatient);
        loadPatient();
    }

    public void deletePatient() {
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        cabinetService.deletePatient(patient);
        loadPatient();
    }

    private void loadPatient() {
        patients.setAll(cabinetService.getPatients());
        textNom.setText("");
        textPrenom.setText("");
        textTelephone.setText("");
    }
}
