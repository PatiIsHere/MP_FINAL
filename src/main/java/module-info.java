module com.mpfinal.mp_final {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mpfinal.mp_final to javafx.fxml;
    exports com.mpfinal.mp_final;
    exports com.mpfinal.mp_final.Controller;
    opens com.mpfinal.mp_final.Controller to javafx.fxml;
}