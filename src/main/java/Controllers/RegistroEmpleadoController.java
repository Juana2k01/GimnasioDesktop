package Controllers;

import ENTITY.Empleado;
import Persistence.EmpleadoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class RegistroEmpleadoController {


    @FXML
    private ImageView imagenEmpleado;
    @FXML
    private TextField textDNI;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textSueldo;
    @FXML
    private TextField textTelefono;
    @FXML
    private ChoiceBox elegirCargo;

    private File imagenFile;

    private int id;
    Empleado empleado;
    EmpleadoDAO empleadoDAO;

    public void initialize() {

        ObservableList<String> opcionesCargos = FXCollections.observableArrayList(
                "Gerente", "Monitor"
        );


        elegirCargo.setItems(opcionesCargos);


        elegirCargo.setValue("Seleccionar Cargo");
    }


    public void cargarFoto(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        imagenFile = fileChooser.showOpenDialog(null);

        if (imagenFile != null) {
            Image imagensubida = new Image(imagenFile.toURI().toString());
            imagenEmpleado.setImage(imagensubida);
        }
    }



    public void borrarFoto(ActionEvent actionEvent) {

    }

    public void borrarTodo(ActionEvent actionEvent) {
    }

    public void siguiente(ActionEvent actionEvent) throws SQLException, IOException {
        empleadoDAO = new EmpleadoDAO();
        empleado = new Empleado();
        empleado.setDNI(textDNI.getText());
        empleado.setNombre(textNombre.getText());
        empleado.setApellido(textApellido.getText());
        empleado.setEmail(textEmail.getText());
        empleado.setTelefono(Integer.parseInt(textTelefono.getText()));
        empleado.setSueldo(Double.valueOf(textSueldo.getText()));

        byte[] imagenData = new byte[(int) imagenFile.length()];
        FileInputStream fis = new FileInputStream(imagenFile);
        fis.read(imagenData);
        fis.close();

        empleado.setImagen(imagenData);
        String cargoSeleccionado = (String) elegirCargo.getValue();


        int idCargo;
        if ("Gerente".equals(cargoSeleccionado)) {
            idCargo = 2;
        } else if ("Monitor".equals(cargoSeleccionado)) {
            idCargo = 3;
        } else {
            idCargo = 0;
        }
        empleado.setRolid(idCargo);


        id = empleadoDAO.IngresarEmpleado(empleado);

        System.out.println("Se ha cargado el nuevo usuario");
    }



    public void getNuevoID (int id){

        this.id = id;
    }


}
