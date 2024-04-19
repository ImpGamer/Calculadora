module com.imp.calculadora {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;

    opens com.imp.calculadora to javafx.fxml;
    exports com.imp.calculadora;
}