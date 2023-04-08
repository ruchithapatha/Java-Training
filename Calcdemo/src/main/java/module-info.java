module com.example.calcdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calcdemo to javafx.fxml;
    exports com.example.calcdemo;
}