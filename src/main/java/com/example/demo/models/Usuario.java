package com.example.demo.models;

public class Usuario {
    // Atributos de la clase
    private String nombre;
    private String apellidos;
    private String fechaDeNacimiento;
    private String correoElectronico;
    private String lugarDeResidencia;
    private String comentarios;

    // Constructor
    public Usuario(String nombre, String apellidos, String fechaDeNacimiento, String correoElectronico, String lugarDeResidencia, String comentarios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.correoElectronico = correoElectronico;
        this.lugarDeResidencia = lugarDeResidencia;
        this.comentarios = comentarios;
    }

    // Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getLugarDeResidencia() {
        return lugarDeResidencia;
    }

    public void setLugarDeResidencia(String lugarDeResidencia) {
        this.lugarDeResidencia = lugarDeResidencia;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    // Método para mostrar la información del usuario
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Fecha de Nacimiento: " + fechaDeNacimiento);
        System.out.println("Correo Electrónico: " + correoElectronico);
        System.out.println("Lugar de Residencia: " + lugarDeResidencia);
        System.out.println("Comentarios: " + comentarios);
    }
}
