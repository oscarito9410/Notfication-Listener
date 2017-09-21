package com.oscar.notficacionlistener.IO.Interactor;

import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;

import java.util.ArrayList;

/**
 * Created by oemy9 on 15/09/2017.
 */

public interface NotificationInteractor {
     void agregarNotificacion(String title,  String descripcion,String paquete,boolean toWebService);
     void loadNotificaciones();
}
