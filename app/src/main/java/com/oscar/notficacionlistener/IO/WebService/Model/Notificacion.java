package com.oscar.notficacionlistener.IO.WebService.Model;

/**
 * Created by oemy9 on 16/09/2017.
 */

public class Notificacion {
    private String mensaje;
    private String mensaje_original;
    private String fecha;
    private String proveedor;
    private String data;

    public String getMensaje () {
        return mensaje;
    }

    public void setMensaje (String mensaje) {
        this.mensaje = mensaje;
    }


    public String getFecha () {
        return fecha;
    }

    public void setFecha (String fecha) {
        this.fecha = fecha;
    }

    public String getProveedor () {
        return proveedor;
    }

    public void setProveedor (String proveedor) {
        this.proveedor = proveedor;
    }


    public String getData () {
        return data;
    }

    public void setData (String data) {
        this.data = data;
    }

    public String getMensajeOriginal () {
        return mensaje_original;
    }

    public void setMensajeOriginal (String mensaje_original) {
        this.mensaje_original = mensaje_original;
    }
}
