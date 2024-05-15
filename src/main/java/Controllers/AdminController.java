package Controllers;

import ENTITY.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class AdminController {

    @FXML private VBox vboxOpciones;
    @FXML private Button subMenu2;
    @FXML private Button subMenu3;
    @FXML private Button subMenu4;
    @FXML private Button subMenu5;
    @FXML private Label nombreCliente;
    @FXML private StackPane panelContenido;
    @FXML private ImageView fotoUsuario;

    private Stage stage;
    private final VBox usuarios;
    private final VBox gestiones;
    private final VBox reservas;
    private final VBox pagos;
    private FXMLLoader loader;
    private final Pane opcionesSelects;
    private final Label cerrarSesion;
    private final ArrayList<ImageView> Imagenes;






    public void initialize() {




    }


    public AdminController() {




        opcionesSelects = new Pane();
        Label pruebita = new Label("Hola");
        pruebita.getStyleClass().add("label_opcionesUsuario");
        opcionesSelects.getStyleClass().add("vBoxsub");
        opcionesSelects.getChildren().add(pruebita);
        cerrarSesion = new Label("Cerrar Sesion");
        cerrarSesion.getStyleClass().add("label_opcionesUsuario");
        opcionesSelects.getChildren().add(cerrarSesion);
        cerrarSesion.setLayoutX(30);
        cerrarSesion.setLayoutY(20);
        cerrarSesion.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                cerrarSesion.getStyleClass().add("label_opcionesUsuarioHover");
            } else {
                cerrarSesion.getStyleClass().remove("label_opcionesUsuarioHover");
            }
        });

        pruebita.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                pruebita.getStyleClass().add("label_opcionesUsuarioHover");
            } else {
                pruebita.getStyleClass().remove("label_opcionesUsuarioHover");
            }
        });
        opcionesSelects.setMinSize(50, 50);
        opcionesSelects.setMaxSize(150, 50);
        Image clienteImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/clienteIMG.png")));
        Image empleadoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/trabajador.png")));
        Image actividadImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/actividades.png")));
        Image competenciaImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/competencia.png")));
        Image reservaActImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/eventos.png")));
        Image reservaComImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/competencias.png")));
        Image pagarImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pagar.png")));
        Image transaccionImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/transaccion.png")));

        Imagenes = new ArrayList<>();
        anadirImagen(clienteImage);
        anadirImagen(empleadoImage);
        anadirImagen(actividadImage);
        anadirImagen(competenciaImage);
        anadirImagen(reservaActImage);
        anadirImagen(reservaComImage);
        anadirImagen(pagarImage);
        anadirImagen(transaccionImage);
        ConfiguracionImagenes(Imagenes);

        String[]Contenido = {"Clientes","Empleados","Actividades","Competencias","Actividades","Competencias","Realizar Pago","Ver Transacciones", "Clientes", "Empleados"};

        ArrayList<Button>Botones = new ArrayList<>();

        Button btnClientes = new Button();
        Button btnEmpleados = new Button();
        Button bntAct = new Button();
        Button btnComp = new Button();
        Button btnRevAct = new Button();
        Button btnRevCom = new Button();
        Button btnPagar = new Button();
        Button btnTransferencias = new Button();
        Button btnInsCliente = new Button();
        Button btnInsEmp = new Button();

        Botones.add(btnClientes);
        Botones.add(btnEmpleados);
        Botones.add(bntAct);
        Botones.add(btnComp);
        Botones.add(btnRevAct);
        Botones.add(btnRevCom);
        Botones.add(btnPagar);
        Botones.add(btnTransferencias);
        Botones.add(btnInsCliente);
        Botones.add(btnInsEmp);
        ConfiguracionBotones(Botones,Contenido);



        usuarios = new VBox();
        usuarios.setVisible(false);
        usuarios.getStyleClass().add("vBox");
        usuarios.getChildren().addAll(btnClientes, btnEmpleados);


        gestiones = new VBox();
        gestiones.setVisible(false);
        gestiones.getStyleClass().add("vBox");
        gestiones.getChildren().addAll(bntAct, btnComp);


        reservas = new VBox();
        reservas.setVisible(false);
        reservas.getStyleClass().add("vBox");
        reservas.getChildren().addAll(btnRevAct, btnRevCom);

        pagos = new VBox();
        pagos.setVisible(false);
        pagos.getStyleClass().add("vBox");
        pagos.getChildren().addAll(btnPagar, btnTransferencias);

        btnEmpleados.setOnAction(event -> {
            try {
                handleBotonEmpleados();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        cerrarSesion.setOnMouseClicked(event -> {
            try {
                cerrarSesion();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



public void ConfiguracionImagenes(ArrayList<ImageView> Imagenes){

    for (int i = 0; i < Imagenes.size();i++ ){
        Imagenes.get(i).setFitWidth(30);
        Imagenes.get(i).setFitHeight(30);
    }
}

    private void anadirImagen(Image image) {
        ImageView imageView = new ImageView(image);
        Imagenes.add(imageView);
    }



public void ConfiguracionBotones(ArrayList<Button>Botones, String[] Contenido){
    for (int k = 0; k < Botones.size(); k++) {

        Botones.get(k).setText(Contenido[k]);
        Botones.get(k).setPrefWidth(223);
        Botones.get(k).setPrefHeight(50);
        Botones.get(k).getStyleClass().add("btnsub");
    }
}

    @FXML
    public void mostrarOpciones2() {
        volverVisible(usuarios, subMenu2);

    }

    @FXML
    public void mostrarOpciones3() {
        volverVisible(gestiones, subMenu3);

    }


    @FXML
    public void mostrarOpciones4() {
        volverVisible(reservas, subMenu4);

    }

    @FXML
    public void mostrarOpciones5() {

        volverVisible(pagos, subMenu5);

    }


    private void volverVisible(VBox prueba, Button subMenu) {
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
    public void EmpleadoInciado(Empleado empleado) {
        nombreCliente.setText(empleado.getNombre());
        fotoUsuario.setImage(transformar(empleado.getImagen()));
        Circle clip = new Circle(15, 15, 15);
        fotoUsuario.setClip(clip);
    }


    private void handleBotonEmpleados() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/templates/VerEmpleados.fxml"));
        Node contenidoEmpleado = loader.load();

        VerEmpleadosController controller = loader.getController();

        controller.setStage(stage);

        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(contenidoEmpleado);
        StackPane.setAlignment(contenidoEmpleado, Pos.TOP_CENTER);
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void mostraropciones(MouseEvent mouseEvent) {
        if (panelContenido.getChildren().contains(opcionesSelects)) {

            panelContenido.getChildren().remove(opcionesSelects);
        } else {


            StackPane.setAlignment(opcionesSelects, Pos.TOP_RIGHT);
            StackPane.setMargin(opcionesSelects, new Insets(0, 20, 0, 0));

            panelContenido.getChildren().add(opcionesSelects);
        }
    }

    public Image transformar(byte[] imageData) {

        InputStream inputStream = new ByteArrayInputStream(imageData);
        Image image = new Image(inputStream);


        return image;
    }



    public void mostrarIns(ActionEvent actionEvent) throws IOException {

        loader = new FXMLLoader(getClass().getResource("/templates/OpcionesInscripcion.fxml"));
        Node contenidoEmpleado = loader.load();


        OpcionesInsController opcionesInsController = loader.getController();
        opcionesInsController.setAdminController(this);

        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(contenidoEmpleado);
        StackPane.setAlignment(contenidoEmpleado, Pos.CENTER);
    }

    public void cargarFormularioCliente(Node contenido) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/templates/RegistroEmpleado.fxml"));
        Node contenidoEmpleado = loader.load();



        panelContenido.getChildren().clear();
        panelContenido.getChildren().add(contenidoEmpleado);
        StackPane.setAlignment(contenidoEmpleado, Pos.CENTER);

    }


    public void cerrarSesion() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/LOGIN.fxml"));
        Scene scene = new Scene(loader.load());

        Stage loginStage = new Stage();
        loginStage.setTitle("Iniciar Sesión");
        loginStage.setMaximized(false);
        loginStage.setResizable(false);
        loginStage.setScene(scene);
        Image icono = new Image(String.valueOf(getClass().getResource("/img/loguito.png")));
        loginStage.getIcons().add(icono);

        LoginController loginController = loader.getController();
        loginController.setStage(loginStage);

        loginStage.show();
        Stage stageActual = (Stage) vboxOpciones.getScene().getWindow();
        stageActual.close();
    }
}
