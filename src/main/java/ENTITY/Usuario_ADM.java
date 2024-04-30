package ENTITY;

public class Usuario_ADM {
    private int idEmpleado;
    private String username;
    private String password;



   public Usuario_ADM(){

   }

   public Usuario_ADM(int idEmpleado, String username, String password) {
        this.idEmpleado = idEmpleado;
        this.username = username;
        this.password = password;
    }


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
