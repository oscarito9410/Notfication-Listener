package com.oscar.notficacionlistener.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import com.oscar.notficacionlistener.IO.Presenters.NotificationPresenter;
import com.oscar.notficacionlistener.R;
import com.oscar.notficacionlistener.UI.Adapters.AdapterNotificacion;

import java.util.ArrayList;

import static com.oscar.notficacionlistener.Utils.Utilerias.sendNotification;

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
public class FragmentHome extends FragmentBase implements NotificationPresenter.View {

    private NotificationPresenter mPresenter;
    private RecyclerView mRvNotifications;

    public static FragmentHome newInstance() {
        Bundle args = new Bundle();
        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView() {
        mRvNotifications = (RecyclerView) mRootView.findViewById(R.id.rv_notifications);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new NotificationPresenter();
        mPresenter.setView(this);
        mPresenter.loadNotificaciones();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notificacion, menu);
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void getNotificaciones(ArrayList<NotificacionTabla> listNotificaciones) {
        if (!listNotificaciones.isEmpty()) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            AdapterNotificacion adapterNotificacion = new AdapterNotificacion(getContext(), listNotificaciones);
            mRvNotifications.setLayoutManager(layoutManager);
            mRvNotifications.setAdapter(adapterNotificacion);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_habilitar:
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                break;
            case R.id.action_update:
                sendNotification(getActivity(), "Prueba");
                mPresenter.loadNotificaciones();
                break;
            case R.id.action_eliminar:
                mPresenter.eliminar();
                break;
        }
        return true;
    }

    @Override
    public Context context() {
        return getContext();
    }
}
