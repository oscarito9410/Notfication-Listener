package com.oscar.notficacionlistener.IO.WebService.Controller;
import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oscar.notficacionlistener.IO.WebService.Model.JsonResponse;
import com.oscar.notficacionlistener.IO.WebService.Model.Notificacion;
import com.oscar.notficacionlistener.IO.WebService.Services.APIService;
import com.oscar.notficacionlistener.Utils.Contants;
import com.oscar.notficacionlistener.Utils.Utilerias;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
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

    public ControllerAPI(final Context ctx){
        this.ctx=ctx;
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client=new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(client).baseUrl(Contants.URL_BASE).build();
        this.service = retrofit.create(APIService.class);
    }

    public void recibirNotificacion(final Notificacion notificacion){
        service.recibiNotificacion(notificacion).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if(response.isSuccessful()){
                    if(response.code()==200) {
                        Utilerias.sendNotification(ctx,"Enviado a server notificacion");
                    }
                    else if(response.code()==503){
                        Utilerias.sendNotification(ctx,"Error 503 envio a server");
                        recibirNotificacion(notificacion);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });
    }
}
