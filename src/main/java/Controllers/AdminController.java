package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AdminController {


    @FXML
    private VBox vboxOpciones;

    @FXML
    private Button subMenu1;

    @FXML
    private Button subMenu2;

    @FXML
    private Button subMenu3;

    @FXML
    private Button subMenu4;

    @FXML
    private Button subMenu5;


    @FXML
    private Button subMenu6;



    private VBox prueba1;
    private VBox prueba2;
    private VBox prueba3;
    private VBox prueba4;
    private VBox prueba5;
    private VBox prueba6;

    public AdminController() {

        VBox[] pruebas = {prueba1, prueba2, prueba3, prueba4, prueba5, prueba6};
        Button[][] subButtons = new Button[pruebas.length][2];

        for (int i = 0; i < pruebas.length; i++) {
            pruebas[i] = new VBox();
            pruebas[i].setVisible(false);
            subButtons[i][0] = new Button("Boton " + (i + 1) + ".1");
            subButtons[i][1] = new Button("Boton " + (i + 1) + ".2");


            for (Button button : subButtons[i]) {
                button.setPrefWidth(223);
                button.setPrefHeight(25);
                button.getStyleClass().add("btnsub");
            }


            pruebas[i].getStyleClass().add("vBoxsub");
            pruebas[i].getChildren().addAll(subButtons[i]);
        }


        prueba1 = pruebas[0];
        prueba2 = pruebas[1];
        prueba3 = pruebas[2];
        prueba4 = pruebas[3];
        prueba5 = pruebas[4];
        prueba6 = pruebas[5];
    }



    public void mostrarOpciones1(ActionEvent actionEvent) {
        toggleVisibility(prueba1, subMenu1);
    }

    @FXML
    public void mostrarOpciones2(ActionEvent actionEvent) {
        toggleVisibility(prueba2, subMenu2);
    }
    @FXML
    public void mostrarOpciones3(ActionEvent actionEvent) {
        toggleVisibility(prueba3, subMenu3);
    }


    @FXML
    public void mostrarOpciones4(ActionEvent actionEvent) {
        toggleVisibility(prueba4, subMenu4);
    }

    @FXML
    public void mostrarOpciones5(ActionEvent actionEvent) {

        toggleVisibility(prueba5, subMenu5);
    }

    @FXML
    public void mostrarOpciones6(ActionEvent actionEvent) {

        toggleVisibility(prueba6, subMenu6);
    }

    private void toggleVisibility(VBox prueba, Button subMenu) {
        prueba.setVisible(!prueba.isVisible());
        if (prueba.isVisible()) {
            vboxOpciones.getChildren().add(vboxOpciones.getChildren().indexOf(subMenu) + 1, prueba);
        } else {
            vboxOpciones.getChildren().remove(prueba);
        }
        actualizarScrollPane();
    }


    private void actualizarScrollPane() {

        vboxOpciones.layout();

        vboxOpciones.getParent().requestLayout();
    }



}
