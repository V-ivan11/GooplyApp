package com.seriouscorp.gooply.Modelo;

public class EventosAgenda {
    private String nombreevento;
    private String tipoevento;
    private String fecha;

    public EventosAgenda() {
    }

    public EventosAgenda(String nombreevento, String tipoevento, String fecha) {
        this.nombreevento = nombreevento;
        this.tipoevento = tipoevento;
        this.fecha = fecha;
    }


    public String getNombreevento() {
        return nombreevento;
    }

    public void setNombreevento(String nombreevento) {
        this.nombreevento = nombreevento;
    }

    public String getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(String tipoevento) {
        this.tipoevento = tipoevento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
