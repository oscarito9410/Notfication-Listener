package com.oscar.notficacionlistener.IO.WebService.Services;

import com.oscar.notficacionlistener.IO.WebService.Model.JsonResponse;
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaItem;
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaListResponse;
import com.oscar.notficacionlistener.IO.WebService.Model.Notificacion;
import com.oscar.notficacionlistener.IO.WebService.Model.NotificationUsuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by oemy9 on 11/03/2017.
 */

public interface APIService {

    @POST("recibirNotificacion")
    Call<JsonResponse> recibiNotificacion(@Body Notificacion notificacion);

    @POST("notificarUsuario")
    Call<JsonResponse> notificarUsuario(@Body NotificationUsuario notificationUsuario);

    @GET("noticias")
    Call<NoticiaListResponse> noticias();

    @POST("noticias/agregar")
    Call<JsonResponse> agregarNoticia(@Body NoticiaItem noticia);
}
