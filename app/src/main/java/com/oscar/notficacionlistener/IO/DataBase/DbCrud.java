package com.oscar.notficacionlistener.IO.DataBase;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by oemy9 on 15/09/2017.
 */

public class DbCrud {
    private DbHelper dbHelper;
    private Context context;
    public DbCrud(Context context){
        this.context=context;
    }
    public DbHelper getDbHelper() {
        if(dbHelper==null){
            dbHelper= OpenHelperManager.getHelper(this.context,DbHelper.class);
        }
        return dbHelper;
    }
    public void addNotificacion(NotificacionTabla notificacion){
        try {
            Dao dao=getDbHelper().getNotificacionDao();
            dao.create(notificacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarNotificaciones(){
        try {
            Dao dao=getDbHelper().getNotificacionDao();
            dao.executeRawNoArgs("delete from NotificacionTabla");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<NotificacionTabla> getNotificaciones(){
        ArrayList<NotificacionTabla> notificacionTablaArrayList=null;
        ArrayList<NotificacionTabla>notificacionFiltrada=new ArrayList<>();
        try {
            notificacionTablaArrayList= (ArrayList<NotificacionTabla>)getDbHelper().getNotificacionDao().queryForAll();
            for(NotificacionTabla item: notificacionTablaArrayList){
                if(item.getFecha().equals(new Date())) {
                    notificacionFiltrada.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificacionTablaArrayList;
    }
}
