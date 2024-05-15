package Controllers;

import ENTITY.Empleado;
import Persistence.EmpleadoDAO;
import Utils.PDF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.util.Optional;

public class DetallesPersonaController  {

    @FXML
    private Pane panelDetalles;

    @FXML
    private Label nombrecito;

    @FXML
    private ImageView foto;

    @FXML
    private Label cargo;

    @FXML
    private Label labelEdad;

    @FXML
    private Button cerrarPanel;


    @FXML
    private Label labelTelefono;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelSueldo;

    @FXML private Button btnPDF;

    private Empleado empleadopropio;

    private VerEmpleadosController verEmpleadosController;


    public void setVerEmpleadosController(VerEmpleadosController controller) {
        this.verEmpleadosController = controller;
    }


    public void initialize() {


        botonHover(btnPDF);


    }



    public void EmpleadoDetalle(Empleado empleado) {

        nombrecito.setText(empleado.getNombre());
        foto.setImage(transformar(empleado.getImagen()));
        Circle clip = new Circle(75, 75, 75);
        foto.setClip(clip);
        if (empleado.getRolid()==2){
            cargo.setText("Gerente");
        }else{
            cargo.setText("Monitor");
        }
        labelEmail.setText(empleado.getEmail());
        labelTelefono.setText(String.valueOf(empleado.getTelefono()));
        labelSueldo.setText(String.valueOf(empleado.getSueldo()));
        Date fecha = (Date) empleado.getFecha_nacimiento();
        LocalDate fechaNacimiento = fecha.toLocalDate();
        LocalDate fechaActual = LocalDate.now();


        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();


        labelEdad.setText(edad +" años");
        Date alta = (Date) empleado.getFecha_alta();
        LocalDate fechaalta = alta.toLocalDate();
        labelFecha.setText(fechaalta.toString());

        empleadopropio = empleado;
    }


    public Image transformar(byte[] imageData) {

        InputStream inputStream = new ByteArrayInputStream(imageData);
        Image image = new Image(inputStream);


        return image;
    }
    @FXML
    private void cerrarPanel() {
        if (verEmpleadosController != null) {
            verEmpleadosController.cerrarPanel();
        }
    }

    public void pdfGen(ActionEvent actionEvent) throws IOException {
        PDF pdf = new PDF();
        pdf.generarPDFEmp(empleadopropio);
    }


    public void botonHover(Button button){

        button.setOnMouseEntered(event -> {
            button.getStyleClass().add("btn_pdfH");
        });


        button.setOnMouseExited(event -> {
            button.getStyleClass().remove("btn_pdfH");
        });
    }

    public void editarDatosEmpleado(ActionEvent actionEvent) throws IOException {

        try {
            System.out.println("Editar datos del empleado: Inicio");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/ActualizarDatos.fxml"));
            DialogPane empleadoDialogPane = loader.load();

            ActualizarDatosController actualizarDatosController = loader.getController();
            actualizarDatosController.ActualizarEmpleado(empleadopropio);

            Dialog<ButtonType>dialog= new Dialog<>();
            dialog.setDialogPane(empleadoDialogPane);
            dialog.setTitle("Actualizar Datos");

            Optional<ButtonType> clickedButton = dialog.showAndWait();



            if(clickedButton.get()==ButtonType.OK){
                Empleado empleadoActualizado = actualizarDatosController.tomarDatos();
                empleadoActualizado.setIdEmpleado(empleadopropio.getIdEmpleado());
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                empleadoDAO.actualizarDatos(empleadoActualizado);
                System.out.println("se presionó ok");


            }else{
                System.out.println("se presionó cancelar");

            }





        } catch (Exception e){

        }


    }
}
