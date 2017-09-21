package com.oscar.notficacionlistener.IO.DataBase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import java.sql.SQLException;

/**
 * Created by oemy9 on 15/09/2017.
 */

public class DbHelper  extends OrmLiteSqliteOpenHelper{
    public static final String DB_NAME="notificaciones.db";
    public static final int VERSION=1;

    private Dao<NotificacionTabla,Integer> notificacionDao;

    public DbHelper(Context ctx){
        super(ctx,DB_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,NotificacionTabla.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        onCreate(sqLiteDatabase,connectionSource);
    }

    public Dao<NotificacionTabla, Integer> getNotificacionDao() throws  SQLException {
        if(notificacionDao ==null){
            notificacionDao =getDao(NotificacionTabla.class);
        }
        return notificacionDao;
    }

    @Override
    public void close() {
        super.close();
        notificacionDao =null;
    }
}