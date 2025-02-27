module ma.iibdcc.gestionconsultation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ma.iibdcc.gestionconsultation.controllers to javafx.fxml;
    opens ma.iibdcc.gestionconsultation.entities to javafx.base;
    exports ma.iibdcc.gestionconsultation;
}