package com.oscar.notficacionlistener.IO.WebService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
public class NoticiaListResponse {

    @SerializedName("listNoticias")
    @Expose
    public List<NoticiaList> listNoticias = null;
    @SerializedName("nuevos")
    @Expose
    public Integer nuevos;
    @SerializedName("actualizado")
    @Expose
    public Boolean actualizado;
}
