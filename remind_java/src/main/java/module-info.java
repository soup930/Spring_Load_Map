module com.example.remind_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.remind_java to javafx.fxml;
    exports com.example.remind_java;
}