package com.oscar.notficacionlistener.IO.WebService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
public class NoticiaList {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("guid")
    @Expose
    public Object guid;
    @SerializedName("pubDate")
    @Expose
    public String pubDate;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("token")
    @Expose
    public String token;
}
