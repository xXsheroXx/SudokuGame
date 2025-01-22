module com.example.labb4 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.labb4 to javafx.fxml;
    exports com.example.labb4;
}