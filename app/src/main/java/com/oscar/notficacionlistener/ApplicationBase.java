package com.oscar.notficacionlistener;

import android.app.Application;
import android.content.Context;

import com.oscar.notficacionlistener.IO.DataBase.DbCrud;
import com.oscar.notficacionlistener.IO.WebService.Controller.ControllerAPI;

/**
 * Created by oemy9 on 13/03/2017.
 */
public class ApplicationBase extends Application {
    private static Context context;
    public static ApplicationBase instance;

    private ControllerAPI controllerAPI;
    private DbCrud dbCrud;

    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    public static ApplicationBase getIntance() {
        if (instance == null) {
            instance = new ApplicationBase();
        }
        return instance;
    }

    private void initApplication() {
        instance = this;
        context = getApplicationContext();
        controllerAPI = new ControllerAPI(context);
        dbCrud = new DbCrud(context);
    }

    public DbCrud getDbCrud() {
        return dbCrud;
    }

    public ControllerAPI getControllerAPI() {
        return controllerAPI;
    }
}
