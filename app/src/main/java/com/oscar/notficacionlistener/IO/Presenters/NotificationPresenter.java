package com.oscar.notficacionlistener.IO.Presenters;

import android.util.Log;

import com.oscar.notficacionlistener.ApplicationBase;
import com.oscar.notficacionlistener.IO.Interactor.NotificationInteractor;
import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import com.oscar.notficacionlistener.IO.WebService.Controller.ControllerAPI;
import com.oscar.notficacionlistener.IO.WebService.Model.Notificacion;
import com.oscar.notficacionlistener.IO.WebService.Model.NotificationUsuario;
import com.oscar.notficacionlistener.Utils.Contants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by oemy9 on 15/09/2017.
 */

public class NotificationPresenter extends PresenterBase <NotificationPresenter.View> implements NotificationInteractor {

    private static final String TAG = "NotificationPresenter";

    @Override
    public void enviarNotificacion (NotificationUsuario notificationUsuario) {
        ApplicationBase.getIntance().getControllerAPI().enviarNotificacion(notificationUsuario);
    }

    @Override
    public void agregarNotificacion (String title, String descripcion, String paquete, String json, boolean toWebService, boolean toDBLocal) {
        NotificacionTabla notificacion = new NotificacionTabla();
        notificacion.setTitulo(title);
        notificacion.setDescripcion(descripcion);
        notificacion.setPaquete(paquete);
        notificacion.setData(json);
        notificacion.setFecha(new Date());
        if (toWebService) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Contants.DATE_FORMAT_AGREGAR);
            Notificacion notificacionWs = new Notificacion();
            notificacionWs.setMensaje(descripcion);
            notificacionWs.setFecha(dateFormat.format(new Date()));
            notificacionWs.setProveedor(paquete);
            notificacionWs.setMensajeOriginal(descripcion);
            notificacion.setEnviado(true);
            notificacion.setData(json);
            ControllerAPI controllerAPI = new ControllerAPI(getView().context());
            controllerAPI.recibirNotificacion(notificacionWs);
            Log.i(TAG, "AGREGANDO NOTIFICACION");
        }
        if (toDBLocal)
            ApplicationBase.getIntance().getDbCrud().addNotificacion(notificacion);


    }


    @Override
    public void loadNotificaciones () {
        getView().getNotificaciones(ApplicationBase.getIntance().getDbCrud().getNotificaciones());
    }

    //TODO AGREGAR ELIMINAR AQUI
    public void eliminar () {
        //ApplicationBase.getIntance().getDbCrud().eliminarNotificaciones();
    }

    public interface View extends PresenterBase.View {
        void onSuccess ();

        void getNotificaciones (ArrayList <NotificacionTabla> listNotificaciones);
    }

}
