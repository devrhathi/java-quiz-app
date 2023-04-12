module com.ooadproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ooadproject to javafx.fxml;
    exports com.ooadproject;
}
