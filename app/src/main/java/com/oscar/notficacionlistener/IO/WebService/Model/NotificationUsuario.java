package com.oscar.notficacionlistener.IO.WebService.Model;

public class NotificationUsuario {
    private String mensaje;
    private boolean sismo;

    public String getMensaje () {
        return mensaje;
    }

    public void setMensaje (String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isSismo () {
        return sismo;
    }

    public void setSismo (boolean sismo) {
        this.sismo = sismo;
    }
}
