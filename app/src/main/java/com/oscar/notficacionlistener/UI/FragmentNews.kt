package com.oscar.notficacionlistener.UI

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

import com.oscar.notficacionlistener.ApplicationBase
import com.oscar.notficacionlistener.IO.WebService.Model.JsonResponse
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaItem
import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaListResponse
import com.oscar.notficacionlistener.R
import com.oscar.notficacionlistener.UI.Adapters.AdapterNoticiaRecycler

import java.io.IOException
import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Oscar Emilio Pérez Mtz on 06/01/2019.
 * operez@na-at.com.mx
 */
class FragmentNews : FragmentBase(), Callback<NoticiaListResponse>, AdapterNoticiaRecycler.OnItemClickListener {

    private var mRvNews: RecyclerView? = null
    private var mProgressDialog: ProgressDialog? = null
    private var mAdpt: AdapterNoticiaRecycler? = null

    override fun getResourceLayout(): Int {
        return R.layout.fragment_news
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadNews()
    }


    public override fun initView() {
        mRvNews = mRootView.findViewById(R.id.rv_news) as RecyclerView
    }

    private fun loadNews() {
        showProgressDialog()
        ApplicationBase.getIntance().controllerAPI.obtenerNoticias(this)
    }

    override fun onResponse(call: Call<NoticiaListResponse>, response: Response<NoticiaListResponse>) {
        if (response.isSuccessful) {
            val listSorted = response.body().getListNoticias().sortedBy { it.getDateFromPubDate() }.reversed()
            setAdapter(listSorted)
        }
        hideProgressDialog()
    }

    override fun onFailure(call: Call<NoticiaListResponse>, t: Throwable) {
        hideProgressDialog()
    }


    private fun setAdapter(lists: List<NoticiaItem>) {
        mAdpt = AdapterNoticiaRecycler(context, lists, this)
        mRvNews!!.layoutManager = LinearLayoutManager(context)
        mRvNews!!.adapter = mAdpt
    }

    private fun showProgressDialog() {
        mProgressDialog = ProgressDialog(context)
        mProgressDialog!!.setMessage("Espera...")
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        mProgressDialog!!.dismiss()
    }

    override fun onItemClickListener(item: NoticiaItem) {
        AlertDialog.Builder(context)
                .setTitle("Noticia")
                .setMessage("¿Estás seguro de enviar esta noticia?")
                .setPositiveButton("aceptar") { dialog, _ ->
                    sendNewToService(item)
                    dialog.dismiss()
                }.setNegativeButton("cancelar", null)
                .create()
                .show()
    }

    fun sendNewToService(item: NoticiaItem) {
        showProgressDialog()
        ApplicationBase.getIntance().controllerAPI.agregarNoticia(item, object : Callback<JsonResponse> {
            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
                if (response.body() != null) {
                    Toast.makeText(context, response.body().mensaje, Toast.LENGTH_SHORT).show()
                    loadNews()
                } else {
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }

                hideProgressDialog()
            }

            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
                hideProgressDialog()

            }
        })
    }

    companion object {

        fun newInstance(): FragmentNews {
            val args = Bundle()
            val fragment = FragmentNews()
            fragment.arguments = args
            return fragment
        }
    }
}
