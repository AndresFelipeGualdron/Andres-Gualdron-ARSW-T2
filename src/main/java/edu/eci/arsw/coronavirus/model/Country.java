package edu.eci.arsw.coronavirus.model;

import java.util.List;

public class Country {

    private String nombre;
    private int muertos;
    private  int contagiados;
    private int recuperados;
    private String lastUpdate;
    private String keyId;
    private List<Province> provinces;

    public Country(){

    }

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

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
