package Persistence;

import ENTITY.Empleado;
import Utils.db.DB;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class EmpleadoDAO {

    private static Connection connection;


    public EmpleadoDAO() {
        this.connection = DB.getInstance().getConnection();
    }


    public  int IngresarEmpleado(Empleado empleado) throws SQLException {

        String query = "Insert INTO empleado (DNI, nombre, apellido, Email, telefono, sueldo, rol_id, Imagen_Empleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, empleado.getDNI());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getApellido());
            statement.setString(4, empleado.getEmail());
            statement.setInt(5, empleado.getTelefono());
            statement.setDouble(6, empleado.getSueldo());
            statement.setInt(7, empleado.getRolid());
            statement.setBytes(8, empleado.getImagen());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }


        } catch (Exception e) {
        }
        return 0;
    }




 // Aqui recibo el ID de un empleado y hago la consulta para obtener todos los datos de el

    public Empleado getEmpleadoConID(int idEmpleado) throws SQLException, IOException {
     Empleado empleado = null;
     String query = "SELECT * FROM empleado WHERE id = ?";
     try (PreparedStatement statement = connection.prepareStatement(query)) {
         statement.setInt(1, idEmpleado);
         try (ResultSet resultSet = statement.executeQuery()) {
             if (resultSet.next()) {
                 empleado = new Empleado();
                 empleado.setIdEmpleado(resultSet.getInt("id"));
                 empleado.setDNI(resultSet.getString("DNI"));
                 empleado.setNombre(resultSet.getString("nombre"));
                 empleado.setApellido(resultSet.getString("apellido"));
                 empleado.setEmail(resultSet.getString("Email"));
                 empleado.setTelefono(resultSet.getInt("telefono"));
                 empleado.setSueldo(resultSet.getDouble("sueldo"));
                 empleado.setRolid(resultSet.getInt("rol_id"));
                 empleado.setImagen(resultSet.getBytes("Imagen_Empleado"));

             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return empleado;
 }
    public Empleado getEmpleado() throws SQLException, IOException {
        Empleado empleado = null;
        String query = "SELECT * FROM empleado";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    empleado = new Empleado();
                    empleado.setIdEmpleado(resultSet.getInt("id"));
                    empleado.setDNI(resultSet.getString("DNI"));
                    empleado.setNombre(resultSet.getString("nombre"));
                    empleado.setApellido(resultSet.getString("apellido"));
                    empleado.setEmail(resultSet.getString("Email"));
                    empleado.setTelefono(resultSet.getInt("telefono"));
                    empleado.setSueldo(resultSet.getDouble("sueldo"));
                    empleado.setRolid(resultSet.getInt("rol_id"));
                    empleado.setImagen(resultSet.getBytes("Imagen_Empleado"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }


    public ArrayList<Empleado> getEmpleados() throws SQLException, IOException {
       ArrayList<Empleado> Empleados = new ArrayList<Empleado>();
        Empleado empleado = null;
        String query = "SELECT * FROM empleado";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    empleado = new Empleado();
                    empleado.setIdEmpleado(resultSet.getInt("id"));
                    empleado.setDNI(resultSet.getString("DNI"));
                    empleado.setNombre(resultSet.getString("nombre"));
                    empleado.setApellido(resultSet.getString("apellido"));
                    empleado.setEmail(resultSet.getString("Email"));
                    empleado.setTelefono(resultSet.getInt("telefono"));
                    empleado.setSueldo(resultSet.getDouble("sueldo"));
                    empleado.setRolid(resultSet.getInt("rol_id"));
                    empleado.setImagen(resultSet.getBytes("Imagen_Empleado"));
                    Empleados.add(empleado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Empleados;
    }



    public int contadorEmpleados () throws SQLException {
        int numerito = 0;
        String query = "SELECT COUNT(*) AS total FROM empleado";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    numerito = resultSet.getInt("total");
                }
            }
        }
        return numerito;
    }


}
