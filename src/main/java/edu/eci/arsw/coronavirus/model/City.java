package edu.eci.arsw.coronavirus.model;

public class City {

    private String nombre = "";
    private int muertos = 0;
    private  int contagiados = 0;
    private int recuperados = 0;
    private String lastUpdate = "";
    private String keyId = "";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMuertos() {
        return muertos;
    }

    public void setMuertos(int muertos) {
        this.muertos = muertos;
    }

    public int getContagiados() {
        return contagiados;
    }

    public void setContagiados(int contagiados) {
        this.contagiados = contagiados;
    }

    public int getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(int recuperados) {
        this.recuperados = recuperados;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}
