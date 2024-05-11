package Controllers;

import ENTITY.Empleado;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ResourceBundle;

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

    private VerEmpleadosController verEmpleadosController;


    public void setVerEmpleadosController(VerEmpleadosController controller) {
        this.verEmpleadosController = controller;
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


        labelEdad.setText(String.valueOf(edad));

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

}
