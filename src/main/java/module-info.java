module com.mpfinal.mp_final {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mpfinal.mp_final to javafx.fxml;
    exports com.mpfinal.mp_final;
}