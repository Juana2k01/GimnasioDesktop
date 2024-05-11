package Persistence;

import ENTITY.Empleado;
import ENTITY.Usuario_ADM;
import Utils.db.DB;

import java.io.IOException;
import java.sql.*;


public class Usuario_ADMDAO {

    private Connection connection;

    public Usuario_ADMDAO() {
        this.connection = DB.getInstance().getConnection();
    }


    public boolean VerificacionUsuario(Usuario_ADM usuarioAdm) throws SQLException {
        Boolean Respuesta = false;
        String query = "SELECT  Username, Password_ FROM usuarios_adm WHERE Username = ? AND Password_ = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, usuarioAdm.getUsername());
        statement.setString(2, usuarioAdm.getPassword());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Respuesta = true;
        } else {
            Respuesta = false;
        }


        return Respuesta;
    }
    // Aqui recibo un usuarioadm proporcionado por el Login Controller y devuelvo un tipo de dato int del id_empleado de ese empleado

    public int getIdEmpleado(Usuario_ADM usuario_adm) throws SQLException {
        int numeroID = -1;

        String query = "SELECT id_empleado FROM USUARIOS_ADM WHERE Username = ? AND Password_ = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, usuario_adm.getUsername());
        statement.setString(2, usuario_adm.getPassword());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            numeroID = resultSet.getInt("id_empleado");
        }

        resultSet.close();
        statement.close();

        return numeroID;
    }

    // Aqui recibo un usuarioadm proporcionado por el Login Controller y devuelvo un tipo de dato Empleado
    public Empleado getEmpleado(Usuario_ADM usuarioAdm) throws SQLException, IOException {
        int idEmpleado = getIdEmpleado(usuarioAdm);
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        return empleadoDAO.getEmpleadoConID(idEmpleado);
    }


    public Usuario_ADM getUsuario(Empleado empleado) throws SQLException {


        Usuario_ADM usuarioAdm = new Usuario_ADM();


        String query = "SELECT Username, Password_ FROM usuarios_adm WHERE id_empleado = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, empleado.getIdEmpleado());


        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {

            usuarioAdm.setUsername(resultSet.getString("Username"));
            usuarioAdm.setPassword(resultSet.getString("Password_"));
        }

        resultSet.close();
        statement.close();




        return usuarioAdm;
    }


    public void IngresarUsuario(Usuario_ADM usuarioAdm){


            String query = "INSERT INTO usuarios_adm (id_empleado, Username, Password_) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, usuarioAdm.getIdEmpleado());
            statement.setString(2, usuarioAdm.getUsername());
            statement.setString(3, usuarioAdm.getPassword());
            statement.executeUpdate();



        } catch (Exception e) {
        }





    }

}
