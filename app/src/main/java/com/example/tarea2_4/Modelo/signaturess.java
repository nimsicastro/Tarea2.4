package com.example.tarea2_4.Modelo;

public class signaturess {

    private Integer id;
    private byte [] firma;
    private String descripcion;

    public signaturess(Integer id, byte[] firma, String descripcion) {
        this.id = id;
        this.firma = firma;
        this.descripcion = descripcion;
    }

    public signaturess() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}