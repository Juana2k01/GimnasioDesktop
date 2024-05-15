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


    public int IngresarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO empleado (DNI, nombre, apellido, genero, Email, telefono, sueldo, rol_id, Imagen_Empleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int empleadoId = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, empleado.getDNI());
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getApellido());
            statement.setString(4, empleado.getGenero());
            statement.setString(5, empleado.getEmail());
            statement.setInt(6, empleado.getTelefono());
            statement.setDouble(7, empleado.getSueldo());
            statement.setInt(8, empleado.getRolid());
            statement.setBytes(9, empleado.getImagen());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el empleado, ninguna fila afectada.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    empleadoId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el empleado.");
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción
            e.printStackTrace();
            throw e;
        }

        return empleadoId;
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
                    empleado.setGenero(resultSet.getString("genero"));
                    empleado.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                    empleado.setEmail(resultSet.getString("Email"));
                    empleado.setTelefono(resultSet.getInt("telefono"));
                    empleado.setSueldo(resultSet.getDouble("sueldo"));
                    empleado.setFecha_alta(resultSet.getDate("fecha_de_alta"));
                    empleado.setRolid(resultSet.getInt("rol_id"));
                    empleado.setImagen(resultSet.getBytes("Imagen_Empleado"));
                    empleado.setEstado_empleado(resultSet.getInt("estado_empleado"));
                    Empleados.add(empleado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Empleados;
    }


    public int contadorEmpleados() throws SQLException {
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


    public void actualizarDatos(Empleado empleadoActualizado) {
        System.out.println("Actualizando datos del empleado: Inicio");
        String query = "UPDATE empleado SET nombre = ?, apellido = ?, DNI = ?, genero = ?, Email = ?, telefono = ?, sueldo = ?, rol_id = ?, Imagen_Empleado = ? WHERE id = ?";


        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, empleadoActualizado.getNombre());
            statement.setString(2, empleadoActualizado.getApellido());
            statement.setString(3, empleadoActualizado.getDNI());
            statement.setString(4, empleadoActualizado.getGenero());
            statement.setString(5, empleadoActualizado.getEmail());
            statement.setInt(6, empleadoActualizado.getTelefono());
            statement.setDouble(7, empleadoActualizado.getSueldo());
            statement.setInt(8, empleadoActualizado.getRolid());
            statement.setBytes(9, empleadoActualizado.getImagen());
            statement.setInt(10, empleadoActualizado.getIdEmpleado());

            statement.executeUpdate();
            System.out.println("Actualización de datos del empleado exitosa");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos del empleado: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
