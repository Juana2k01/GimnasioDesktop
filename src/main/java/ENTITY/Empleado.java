package ENTITY;

import java.time.LocalDate;
import java.util.Date;

public class Empleado {
    private int idEmpleado;
    private String DNI;
    private String nombre;
    private String apellido;
    private String genero;
    private Date fecha_nacimiento;
    private String Email;
    private Double sueldo;
    private int rolid;
    private int Telefono;
    private Date fecha_alta;
    private byte[] Imagen;
    private int estado_empleado;

    public Empleado(){

    }

    public int getEstado_empleado() {
        return estado_empleado;
    }

    public void setEstado_empleado(int estado_empleado) {
        this.estado_empleado = estado_empleado;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public int getRolid() {
        return rolid;
    }

    public void setRolid(int rolid) {
        this.rolid = rolid;
    }
}
