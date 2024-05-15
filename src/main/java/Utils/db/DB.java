package Utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static DB instance;
    private static final String DB_NAME = "jylgimnasio";
    private static final String USERNAME = "juan";
    private static final String PASSWORD = "juan";
    private static final String URL = "jdbc:mysql://localhost:3308/" + DB_NAME;

    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexion Exitosa a la base de datos " + DB_NAME);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DB getInstance(){
        if(instance == null){
            instance = new DB();
        }
        return  instance;
    }
    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
