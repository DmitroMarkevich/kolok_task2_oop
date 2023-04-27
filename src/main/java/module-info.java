module com.example.kolok_task2_oop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kolok_task2_oop to javafx.fxml;
    exports com.example.kolok_task2_oop;
}