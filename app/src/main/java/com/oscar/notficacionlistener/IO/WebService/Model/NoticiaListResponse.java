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
    public List<NoticiaItem> listNoticias = null;
    @SerializedName("nuevos")
    @Expose
    public Integer nuevos;
    @SerializedName("actualizado")
    @Expose
    public Boolean actualizado;

    public List<NoticiaItem> getListNoticias() {
        return listNoticias;
    }

    public void setListNoticias(List<NoticiaItem> listNoticias) {
        this.listNoticias = listNoticias;
    }

    public Integer getNuevos() {
        return nuevos;
    }

    public void setNuevos(Integer nuevos) {
        this.nuevos = nuevos;
    }

    public Boolean getActualizado() {
        return actualizado;
    }

    public void setActualizado(Boolean actualizado) {
        this.actualizado = actualizado;
    }
}
