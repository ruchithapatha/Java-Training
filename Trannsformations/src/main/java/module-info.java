module com.example.trannsformations {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trannsformations to javafx.fxml;
    exports com.example.trannsformations;
}