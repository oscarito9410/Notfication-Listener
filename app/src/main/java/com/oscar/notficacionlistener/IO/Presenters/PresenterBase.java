package com.oscar.notficacionlistener.IO.Presenters;

/**
 * Created by oemy9 on 15/09/2017.
 */

import android.content.Context;

import com.oscar.notficacionlistener.IO.WebService.Controller.ControllerAPI;

public abstract class PresenterBase <T extends PresenterBase.View> {

    private T view;
    public T getView() {
        return view;
    }
    public void setView(T view) {
        this.view = view;
    }

    public void initialize() {

    }

    public interface View {
        Context context();

    }
}