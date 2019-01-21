package com.oscar.notficacionlistener.UI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.oscar.notficacionlistener.ApplicationBase;
import com.oscar.notficacionlistener.IO.WebService.Model.JsonResponse;
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaItem;
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaListResponse;
import com.oscar.notficacionlistener.R;
import com.oscar.notficacionlistener.UI.Adapters.AdapterNoticiaRecycler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
public class FragmentNews extends FragmentBase implements Callback<NoticiaListResponse>, AdapterNoticiaRecycler.OnItemClickListener {

    private RecyclerView mRvNews;
    private ProgressDialog mProgressDialog;
    private AdapterNoticiaRecycler mAdpt;

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
        showProgressDialog();
        ApplicationBase.getIntance().getControllerAPI().obtenerNoticias(this);
    }

    @Override
    public void onResponse(Call<NoticiaListResponse> call, Response<NoticiaListResponse> response) {
        if (response.isSuccessful()) {
            setAdapter(response.body().getListNoticias());
        }
        hideProgressDialog();
    }

    @Override
    public void onFailure(Call<NoticiaListResponse> call, Throwable t) {
        hideProgressDialog();
    }


    private void setAdapter(List<NoticiaItem> lists) {
        mAdpt = new AdapterNoticiaRecycler(getContext(), (ArrayList<NoticiaItem>) lists, this);
        mRvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvNews.setAdapter(mAdpt);
        Toast.makeText(getContext(), "Size of list is: " + String.valueOf(lists.size()), Toast.LENGTH_SHORT).show();
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Espera...");
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onItemClickListener(NoticiaItem item) {
        showProgressDialog();
        ApplicationBase.getIntance().getControllerAPI().agregarNoticia(item, new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if (response.body() != null) {
                    Toast.makeText(getContext(), response.body().getMensaje(), Toast.LENGTH_SHORT).show();
                    loadNews();
                } else {
                    try {
                        Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}
