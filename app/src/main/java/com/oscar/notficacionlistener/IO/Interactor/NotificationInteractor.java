package com.oscar.notficacionlistener.IO.Interactor;

import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import com.oscar.notficacionlistener.IO.WebService.Model.NotificationUsuario;

import java.util.ArrayList;

/**
 * Created by oemy9 on 15/09/2017.
 */

public interface NotificationInteractor {

    void enviarNotificacion (NotificationUsuario notificationUsuario);

    void agregarNotificacion (String title, String descripcion, String paquete, String json, boolean toWebService, boolean toDBLocal);

    void loadNotificaciones ();
}
