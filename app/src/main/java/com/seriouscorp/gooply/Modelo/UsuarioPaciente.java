package com.seriouscorp.gooply.Modelo;

public class UsuarioPaciente {

    private String usunombre;
    private String foto;
    private String mail;
    private String sexo;
    private String fecha;
    private String edad;
    private String peso;
    private String altura;
    private String sangre;

    public UsuarioPaciente() {
    }

    public UsuarioPaciente(String usunombre, String foto, String mail, String sexo, String fecha, String edad, String peso, String altura, String sangre) {
        this.usunombre = usunombre;
        this.foto = foto;
        this.mail = mail;
        this.sexo = sexo;
        this.fecha = fecha;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.sangre = sangre;
    }

    public String getUsunombre() {
        return usunombre;
    }

    public void setUsunombre(String usunombre) {
        this.usunombre = usunombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }
}