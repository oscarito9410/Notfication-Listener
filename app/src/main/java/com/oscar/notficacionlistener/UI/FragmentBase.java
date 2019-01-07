package com.oscar.notficacionlistener.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
public abstract class FragmentBase extends Fragment {

    protected View mRootView;

    protected abstract int getResourceLayout();

    protected abstract void initView();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getResourceLayout(), container, false);
        initView();
        return mRootView;
    }
}
