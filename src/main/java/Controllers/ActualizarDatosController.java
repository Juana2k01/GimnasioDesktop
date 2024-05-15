package Controllers;

import ENTITY.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.*;


public class ActualizarDatosController {

    private Empleado empleadoDatos;

    @FXML private TextField cargarNombre;
    @FXML private ImageView foto;
    @FXML private TextField cargarApellido;
    @FXML private TextField cargarDNI;
    @FXML private TextField cargarTelefono;
    @FXML private TextField cargarEmail;
    @FXML private TextField cargarSueldo;
    @FXML private ChoiceBox elegirCargo;
    @FXML private ChoiceBox cargarGenero;

    private File imagenFile;
    public void initialize() {

        ObservableList<String> opcionesCargos = FXCollections.observableArrayList(
                "Gerente", "Monitor"
        );


        elegirCargo.setItems(opcionesCargos);




        ObservableList<String> opcionesGenero = FXCollections.observableArrayList(
                "M", "F"
        );


        cargarGenero.setItems(opcionesGenero);


        cargarGenero.setValue("Seleccionar genero");

    }
    public void cargarFoto(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        imagenFile = fileChooser.showOpenDialog(null);

        if (imagenFile != null) {
            Image imagensubida = new Image(imagenFile.toURI().toString());
            foto.setImage(imagensubida);
        }
    }





    public void ActualizarEmpleado(Empleado empleadopropio) throws IOException {

        this.empleadoDatos = empleadopropio;

        cargarNombre.setText(empleadoDatos.getNombre());
        foto.setImage(transformar(empleadoDatos.getImagen()));
        cargarApellido.setText(empleadoDatos.getApellido());
        cargarDNI.setText(empleadoDatos.getDNI());
        cargarEmail.setText(empleadoDatos.getEmail());
        cargarTelefono.setText(String.valueOf(empleadoDatos.getTelefono()));
        cargarSueldo.setText(String.valueOf(empleadoDatos.getSueldo()));
        Circle clip = new Circle(75, 75, 75);
        foto.setClip(clip);

        if(empleadoDatos.getRolid() == 2) {


            elegirCargo.setValue("Gerente");

        }else {
            elegirCargo.setValue("Monitor");
        }





    }

    public Empleado tomarDatos() throws IOException {
        Empleado empleado = new Empleado();

        empleado.setNombre(cargarNombre.getText());
        empleado.setApellido(cargarApellido.getText());
        empleado.setDNI(cargarDNI.getText());
        empleado.setEmail(cargarEmail.getText());
        empleado.setTelefono(Integer.parseInt(cargarTelefono.getText()));
        empleado.setSueldo(Double.valueOf(cargarSueldo.getText()));
        String cargoSeleccionado = (String) elegirCargo.getValue();


        int idCargo;
        if ("Gerente".equals(cargoSeleccionado)) {
            idCargo = 2;
        } else if ("Monitor".equals(cargoSeleccionado)) {
            idCargo = 3;
        } else {
            idCargo = empleadoDatos.getRolid();
        }

        empleado.setRolid(idCargo);

        String generoCargado = (String) cargarGenero.getValue();


        String genero;
        if ("M".equals(generoCargado)) {
            genero = "M";
        } else if ("F".equals(generoCargado)) {
            genero = "F";
        } else {
            genero = "N";
        }
        empleado.setGenero(genero);

        if (imagenFile != null) {
            byte[] imagenData = new byte[(int) imagenFile.length()];
            FileInputStream fis = new FileInputStream(imagenFile);
            fis.read(imagenData);
            fis.close();
            empleado.setImagen(imagenData);
        } else {
            Image imageDefault = foto.getImage(); // Obtiene la imagen predeterminada del ImageView
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(imageDefault, null), "png", outputStream);
                byte[] imagenData = outputStream.toByteArray();
                empleado.setImagen(imagenData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return empleado;
    }



    public Image transformar(byte[] imageData) {

        InputStream inputStream = new ByteArrayInputStream(imageData);
        Image image = new Image(inputStream);


        return image;
    }

}
