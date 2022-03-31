package edu.eci.cvds.entities;

import java.util.Date;

public class ReportCategory {
    private Date fecha;
    private String nombre;
    private String token;
    private String descripcion;

    public ReportCategory() {
    }

    public ReportCategory(Date fecha, String nombre, String token, String descripcion) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.token = token;
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

