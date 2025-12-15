module com.example.carrentalsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carrentalsystem to javafx.fxml;
    exports com.example.carrentalsystem;
}