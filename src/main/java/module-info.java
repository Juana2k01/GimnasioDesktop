module jyl.desktopgimnasio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    requires javax.mail;
    requires kernel;
    requires io;
    requires layout;
    requires styled.xml.parser;
    requires javafx.swing;


    opens jyl.desktopgimnasio to javafx.fxml;
    exports jyl.desktopgimnasio;
    opens Controllers;
    exports Controllers;
}