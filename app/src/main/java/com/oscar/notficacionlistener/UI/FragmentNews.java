package com.oscar.notficacionlistener.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.oscar.notficacionlistener.ApplicationBase;
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaListResponse;
import com.oscar.notficacionlistener.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
public class FragmentNews extends FragmentBase implements Callback<NoticiaListResponse> {

    private RecyclerView mRvNews;

    public static FragmentNews newInstance() {
        Bundle args = new Bundle();
        FragmentNews fragment = new FragmentNews();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_news;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadNews();
    }


    @Override
    public void initView() {
        mRvNews = (RecyclerView) mRootView.findViewById(R.id.rv_news);
    }

    private void loadNews() {
        ApplicationBase.getIntance().getControllerAPI().obtenerNoticias(this);
    }

    @Override
    public void onResponse(Call<NoticiaListResponse> call, Response<NoticiaListResponse> response) {
        if (response.isSuccessful()) {

        }
    }

    @Override
    public void onFailure(Call<NoticiaListResponse> call, Throwable t) {

    }
}
