package com.oscar.notficacionlistener.IO.WebService.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
public class NoticiaItem {

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Object getGuid() {
        return guid;
    }

    public void setGuid(Object guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
