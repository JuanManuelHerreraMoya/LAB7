package edu.eci.cvds.samples.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception {

    public ExcepcionServiciosAlquiler(String mensaje) {
        super(mensaje);
    }
    public ExcepcionServiciosAlquiler(String mensaje,PersistenceException ex) {
        super(mensaje);
    }
}