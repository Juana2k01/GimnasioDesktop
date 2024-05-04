package Controllers;

import ENTITY.Empleado;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    private Label nombreCliente;

    @FXML
    private StackPane panelContenido;

    @FXML
    private ImageView iconsub1;

    @FXML
    private ImageView iconsub2;

    @FXML
    private ImageView iconsub3;

    @FXML
    private ImageView iconsub4;

    @FXML
    private ImageView iconsub5;

    @FXML
    private ImageView iconsub6;

    @FXML
    private AnchorPane opcionesUsuario;

    private Stage stage;
    private final VBox prueba1;
    private final VBox prueba2;
    private final VBox prueba3;
    private final VBox prueba4;
    private final VBox prueba5;
    private final VBox prueba6;
    private  FXMLLoader loader;
    private final Pane opcionesSelects;


    public AdminController() {



        opcionesSelects =new Pane();

         Label pruebita = new Label("Hola");
         opcionesSelects.getStyleClass().add("vBoxsub");
         opcionesSelects.getChildren().add(pruebita);





        prueba1 = new VBox();
        prueba1.setVisible(false);
        Button subButton11 = new Button("Clientes");
        Button BotonEmpleados = new Button("Empleados");
        subButton11.setPrefWidth(223);
        subButton11.setPrefHeight(50);
        BotonEmpleados.setPrefWidth(223);
        BotonEmpleados.setPrefHeight(50);


        prueba1.getStyleClass().add("vBoxsub");

        subButton11.getStyleClass().add("btnsub");
        BotonEmpleados.getStyleClass().add("btnsub");
        prueba1.getChildren().addAll(subButton11, BotonEmpleados);

        prueba2 = new VBox();
        prueba2.setVisible(false);
        Button subButton21 = new Button("Boton 2.1");
        Button subButton22 = new Button("Boton 2.2");
        subButton21.setPrefWidth(223);
        subButton21.setPrefHeight(50);
        subButton22.setPrefWidth(223);
        subButton22.setPrefHeight(50);
        prueba2.getStyleClass().add("vBox");

        subButton21.getStyleClass().add("btnsub");
        subButton22.getStyleClass().add("btnsub");
        prueba2.getChildren().addAll(subButton21, subButton22);


        prueba3 = new VBox();
        prueba3.setVisible(false);
        Button subButton31 = new Button("Boton 3.1");
        Button subButton32 = new Button("Boton 3.2");
        subButton31.setPrefWidth(223);
        subButton31.setPrefHeight(50);
        subButton32.setPrefWidth(223);
        subButton32.setPrefHeight(50);
        prueba3.getStyleClass().add("vBox");
        subButton31.getStyleClass().add("btnsub");
        subButton32.getStyleClass().add("btnsub");
        prueba3.getChildren().addAll(subButton31, subButton32);

        prueba4 = new VBox();
        prueba4.setVisible(false);
        Button subButton41 = new Button("Boton 4.1");
        Button subButton42 = new Button("Boton 4.2");
        subButton41.setPrefWidth(223);
        subButton41.setPrefHeight(50);
        subButton42.setPrefWidth(223);
        subButton42.setPrefHeight(50);
        prueba4.getStyleClass().add("vBox");
        prueba4.setSpacing(10);
        subButton41.getStyleClass().add("btnsub");
        subButton42.getStyleClass().add("btnsub");
        prueba4.getChildren().addAll(subButton41, subButton42);

        prueba5 = new VBox();
        prueba5.setVisible(false);
        Button subButton51 = new Button("Boton 5.1");
        Button subButton52 = new Button("Boton 5.2");
        subButton51.setPrefWidth(223);
        subButton51.setPrefHeight(50);
        subButton52.setPrefWidth(223);
        subButton52.setPrefHeight(50);
        prueba5.getStyleClass().add("vBox");
        prueba5.setSpacing(10);
        subButton51.getStyleClass().add("btnsub");
        subButton52.getStyleClass().add("btnsub");
        prueba5.getChildren().addAll(subButton51, subButton52);

        prueba6 = new VBox();
        prueba6.setVisible(false);
        Button subButton61 = new Button("Boton 6.1");
        Button subButton62 = new Button("Boton 6.2");
        subButton61.setPrefWidth(223);
        subButton61.setPrefHeight(50);
        subButton62.setPrefWidth(223);
        subButton62.setPrefHeight(50);
        prueba6.getStyleClass().add("vBox");
        prueba6.setSpacing(10);
        subButton61.getStyleClass().add("btnsub");
        subButton62.getStyleClass().add("btnsub");
        prueba6.getChildren().addAll(subButton61, subButton62);


        BotonEmpleados.setOnAction(event -> {
            try {
                handleBotonEmpleados();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }


    public void mostrarOpciones1() {
        toggleVisibility(prueba1, subMenu1);
        GirarImagen(iconsub1);
    }

    @FXML
    public void mostrarOpciones2() {
        toggleVisibility(prueba2, subMenu2);
        GirarImagen(iconsub2);
    }
    @FXML
    public void mostrarOpciones3() {
        toggleVisibility(prueba3, subMenu3);
        GirarImagen(iconsub3);
    }


    @FXML
    public void mostrarOpciones4() {
        toggleVisibility(prueba4, subMenu4);
        GirarImagen(iconsub4);
    }

    @FXML
    public void mostrarOpciones5() {

        toggleVisibility(prueba5, subMenu5);
        GirarImagen(iconsub5);
    }

    @FXML
    public void mostrarOpciones6() {

        toggleVisibility(prueba6, subMenu6);
        GirarImagen(iconsub6);
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

 // Aqui asigno el Nombre del Empleado a la Label
    public void EmpleadoInciado (Empleado empleado){
        nombreCliente.setText(empleado.getNombre());

    }



    private void handleBotonEmpleados() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/templates/VerEmpleados.fxml"));
        Node contenidoEmpleado = loader.load();

        VerEmpleadosController controller = loader.getController();
        if (controller != null) {
            System.out.println("Controlador obtenido correctamente en AdminController.");
        } else {
            System.out.println("¡ADVERTENCIA! No se pudo obtener el controlador en AdminController.");
        }

        controller.setStage(stage);

        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(contenidoEmpleado);
    }

    private void GirarImagen (ImageView imageView){
        if (imageView.getRotate()==90){
            imageView.setRotate(0);
        } else{
            imageView.setRotate(90);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void mostraropciones(MouseEvent mouseEvent) {
        if (panelContenido.getChildren().contains(opcionesSelects)) {

            panelContenido.getChildren().remove(opcionesSelects);
        }else {


            opcionesSelects.setMinSize(50, 50);
            opcionesSelects.setMaxSize(150, 50);


            StackPane.setAlignment(opcionesSelects, Pos.TOP_RIGHT);
            StackPane.setMargin(opcionesSelects, new Insets(0, 20, 0, 0));

            panelContenido.getChildren().add(opcionesSelects);
        }
        }
}
