package com.oscar.notficacionlistener.IO.WebService.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
class NoticiaItem {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("guid")
    @Expose
    var guid: Any? = null
    @SerializedName("pubDate")
    @Expose
    var pubDate: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("token")
    @Expose
    var token: String? = null
    @SerializedName("urlImage")
    @Expose
    var urlImage: String? = null

    fun getDateFromPubDate(): Date? {
        val simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US)
        return simpleDateFormat.parse(pubDate)
    }

    fun getReadeablePubdDate(): String? {
        return getDateFromPubDate()?.run {
            val simpleDateFormat = SimpleDateFormat(" dd MMM yyyy HH:mm:ss", Locale.US)
            simpleDateFormat.format(this)
        }
    }
}
