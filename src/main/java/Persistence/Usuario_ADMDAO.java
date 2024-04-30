package Persistence;

import ENTITY.Usuario_ADM;
import Utils.db.DB;

import java.sql.*;



public class Usuario_ADMDAO {

    private DB db;

    public Usuario_ADMDAO() {
        this.db = new DB();
    }


    public boolean VerificacionUsuario(Usuario_ADM usuarioAdm) {
        Boolean Respuesta = false;
        String query = "SELECT  Username, Password_ FROM usuarios_adm WHERE Username = ? AND Password_ = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuarioAdm.getUsername());
            statement.setString(2, usuarioAdm.getPassword());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Respuesta = true;
                } else { Respuesta = false;}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  Respuesta;
    }


}
