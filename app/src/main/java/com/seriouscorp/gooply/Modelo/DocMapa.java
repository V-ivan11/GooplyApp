package com.seriouscorp.gooply.Modelo;

public class DocMapa {
    private String latitud;
    private String longitud;
    private String nombre;
    private String tipodoc;
    private String celular;

    public DocMapa() {
    }

    public DocMapa(String latitud, String longitud, String nombre, String tipodoc, String celular) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.tipodoc = tipodoc;
        this.celular = celular;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
