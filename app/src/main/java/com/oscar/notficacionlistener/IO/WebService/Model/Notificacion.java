package com.oscar.notficacionlistener.IO.WebService.Model;

/**
 * Created by oemy9 on 16/09/2017.
 */

public class Notificacion {
    private String mensaje;
    private String fecha;
    private String proveedor;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
