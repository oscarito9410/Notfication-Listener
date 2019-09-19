package com.oscar.notficacionlistener.UI.Adapters;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.oscar.notficacionlistener.ApplicationBase;
import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import com.oscar.notficacionlistener.IO.Presenters.NotificationPresenter;
import com.oscar.notficacionlistener.IO.WebService.Controller.ControllerAPI;
import com.oscar.notficacionlistener.IO.WebService.Model.Notificacion;
import com.oscar.notficacionlistener.IO.WebService.Model.NotificationUsuario;
import com.oscar.notficacionlistener.R;
import com.oscar.notficacionlistener.UI.ViewHolders.ViewHolderNotificacion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by oemy9 on 16/09/2017.
 */

public class AdapterNotificacion extends RecyclerView.Adapter <ViewHolderNotificacion> {

    private ArrayList <NotificacionTabla> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public AdapterNotificacion (Context context, ArrayList <NotificacionTabla> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(this.context);
        Collections.reverse(list);
    }


    @Override
    public ViewHolderNotificacion onCreateViewHolder (ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_notificacion, parent, false);
        return new ViewHolderNotificacion(itemView);
    }

    @Override
    public void onBindViewHolder (ViewHolderNotificacion holder, final int position) {
        final NotificacionTabla item = getItem(position);
        holder.tvTitulo.setText(item.getTitulo());
        holder.tvDescripcion.setText(item.getDescripcion());
        holder.tvPaquete.setText(item.getPaquete());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            int selectedOption = -1;

            @Override
            public void onClick (View v) {
                new AlertDialog.Builder(context).setTitle("Opciones")
                        .setSingleChoiceItems(new CharSequence[]{"Enviar notificación"}, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface dialog, int which) {
                                selectedOption = which;
                            }
                        })
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface dialog, int which) {
                                if (selectedOption == 0)
                                    sendNotificaction(getItem(position));
                                dialog.dismiss();
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });
    }

    private void sendNotificaction (final NotificacionTabla notification) {
        if (!TextUtils.isEmpty(notification.getTitulo())) {
            final EditText edittext = new EditText(context);
            edittext.setText(notification.getDescripcion());
            edittext.setSelectAllOnFocus(true);
            new AlertDialog.Builder(context).setTitle("Pregunta").setMessage("¿Estás seguro de enviar esto como notificación?")
                    .setView(edittext)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog, int which) {
                            new AlertDialog.Builder(context).setTitle("¿Es sismo?")
                                    .setSingleChoiceItems(new CharSequence[]{"SI", "NO"}, 0, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick (DialogInterface dialog, int which) {
                                            NotificationPresenter presenter = new NotificationPresenter();
                                            NotificationUsuario notificationUsuario = new NotificationUsuario();
                                            notificationUsuario.setMensaje(edittext.getText().toString());
                                            notificationUsuario.setSismo(which == 0);
                                            presenter.enviarNotificacion(notificationUsuario);
                                            dialog.dismiss();
                                        }
                                    }).create().show();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .create()
                    .show();
        }
    }

    @Override
    public int getItemCount () {
        return list.size();
    }

    public NotificacionTabla getItem (int position) {
        return list.get(position);
    }
}
