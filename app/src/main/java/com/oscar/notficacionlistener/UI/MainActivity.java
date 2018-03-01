package com.oscar.notficacionlistener.UI;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import com.oscar.notficacionlistener.IO.Presenters.NotificationPresenter;
import com.oscar.notficacionlistener.IO.Services.NotificationListenerSismos;
import com.oscar.notficacionlistener.R;
import com.oscar.notficacionlistener.UI.Adapters.AdapterNotificacion;

import java.util.ArrayList;

import static com.oscar.notficacionlistener.Utils.Utilerias.sendNotification;

public class MainActivity extends AppCompatActivity implements NotificationPresenter.View {

    private NotificationPresenter presenter;
    private RecyclerView recyclerNotificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new NotificationPresenter();
        presenter.setView(this);
        presenter.loadNotificaciones();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notificacion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_habilitar:
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                break;
            case R.id.action_update:
                sendNotification(this,"Prueba");
                presenter.loadNotificaciones();
                break;
            case R.id.action_eliminar:
                presenter.eliminar();
                break;
        }
        return true;
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void getNotificaciones(ArrayList<NotificacionTabla> listNotificaciones) {
        if(!listNotificaciones.isEmpty()){
            recyclerNotificaciones=(RecyclerView)findViewById(R.id.recyclerNotificaciones);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            AdapterNotificacion adapterNotificacion=new AdapterNotificacion(this,listNotificaciones);
            recyclerNotificaciones.setLayoutManager(layoutManager);
            recyclerNotificaciones.setAdapter(adapterNotificacion);
        }

    }


}
