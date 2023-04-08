module com.example.empdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.empdemo to javafx.fxml;
    exports com.example.empdemo;
}