package com.oscar.notficacionlistener.IO.WebService.Controller;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oscar.notficacionlistener.IO.WebService.Model.JsonResponse;
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaItem;
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaListResponse;
import com.oscar.notficacionlistener.IO.WebService.Model.Notificacion;
import com.oscar.notficacionlistener.IO.WebService.Model.NotificationUsuario;
import com.oscar.notficacionlistener.IO.WebService.Services.APIService;
import com.oscar.notficacionlistener.Utils.Contants;
import com.oscar.notficacionlistener.Utils.Utilerias;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oemy9 on 16/09/2017.
 */

public class ControllerAPI {

    private APIService service;
    private Context ctx;

    public ControllerAPI(final Context ctx) {
        this.ctx = ctx;
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(client).baseUrl(Contants.URL_BASE).build();
        this.service = retrofit.create(APIService.class);
    }

    public void enviarNotificacion(NotificationUsuario notificationUsuario) {
        Toast.makeText(ctx, "Enviando......", Toast.LENGTH_SHORT).show();
        service.notificarUsuario(notificationUsuario).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ctx, "Enviado correctactemte", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Toast.makeText(ctx, "Error al enviar", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void recibirNotificacion(final Notificacion notificacion) {
        service.recibiNotificacion(notificacion).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if (response.isSuccessful()) {
                    Utilerias.sendNotification(ctx, "Enviado a server notificacion");
                } else if (response.code() == 503) {
                    Utilerias.sendNotification(ctx, "Error 503 envio a server");
                    recibirNotificacion(notificacion);
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }

    public void obtenerNoticias(Callback<NoticiaListResponse> call) {
        service.noticias().enqueue(call);
    }

    public void agregarNoticia(NoticiaItem item, Callback<JsonResponse> callback) {
        service.agregarNoticia(item).enqueue(callback);
    }
}
