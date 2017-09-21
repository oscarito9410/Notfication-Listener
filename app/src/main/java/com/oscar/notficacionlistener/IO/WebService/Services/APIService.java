package com.oscar.notficacionlistener.IO.WebService.Services;
import com.oscar.notficacionlistener.IO.WebService.Model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by oemy9 on 11/03/2017.
 */

public interface APIService {
    @POST("recibirNotificacion")
    Call<JsonResponse>recibiNotificacion(@Body Notificacion notificacion);
}
