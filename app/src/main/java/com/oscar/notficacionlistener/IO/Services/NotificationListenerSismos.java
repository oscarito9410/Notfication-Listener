package com.oscar.notficacionlistener.IO.Services;

import android.content.Context;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;

import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import com.oscar.notficacionlistener.IO.Presenters.NotificationPresenter;
import com.oscar.notficacionlistener.Utils.Contants;

import java.util.ArrayList;

/**
 * Created by oemy9 on 15/09/2017.
 */

public class NotificationListenerSismos extends NotificationListenerService implements NotificationPresenter.View {

    public static final String TAG = "NotificationListener";

    private NotificationPresenter presenter;


    @Override
    public void onNotificationPosted (StatusBarNotification sbn) {
        readAllNotifications(sbn);
        super.onNotificationPosted(sbn);
    }

    @Override
    public void onNotificationRemoved (StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

    @Override
    public StatusBarNotification[] getActiveNotifications () {
        return super.getActiveNotifications();
    }

    private void readAllNotifications (StatusBarNotification sbn) {
        presenter = new NotificationPresenter();
        presenter.setView(this);
        final String packageName = sbn.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {

            if (sbn.getNotification() != null) {
                Bundle extras = sbn.getNotification().extras;
                if (extras != null) {
                    String titulo = extras.containsKey("android.title") ? extras.getString("android.title") : "";
                    String descripcion = extras.containsKey("android.text") ? extras.getString("android.text") : "";
                    if (!TextUtils.isEmpty(descripcion)) {
                        if (packageName.equalsIgnoreCase(Contants.TWITTER_PACKAGE_NAME)) {
                            if (titulo.equalsIgnoreCase(Contants.SAXSMEX_TWITTER) || titulo.equalsIgnoreCase("sasmex.net"))
                                presenter.agregarNotificacion(titulo, descripcion, packageName, "", true, true);
                            else if (titulo.equalsIgnoreCase("Sismológico Nacional")) {
                                presenter.agregarNotificacion(titulo, descripcion, packageName, "", false, true);
                            }
                        } else if (packageName.equalsIgnoreCase(Contants.MY_PAQUETE)) {
                            presenter.agregarNotificacion(titulo, descripcion, packageName, "", false, true);
                        } else if (packageName.equalsIgnoreCase(Contants.TELEGRAM_PACKAGE_NAME)) {
                            if (titulo.contains("Sasmex") || descripcion.contains("Sasmex"))
                                presenter.agregarNotificacion(titulo, descripcion, packageName, "", true, true);
                        }
                    }

                }
            }
        }

    }

    @Override
    public Context context () {
        return this;
    }

    @Override
    public void onSuccess () {

    }

    @Override
    public void getNotificaciones (ArrayList <NotificacionTabla> listNotificaciones) {

    }
}
