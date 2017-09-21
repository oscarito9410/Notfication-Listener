package com.oscar.notficacionlistener.UI.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oscar.notficacionlistener.IO.Model.NotificacionTabla;
import com.oscar.notficacionlistener.R;
import com.oscar.notficacionlistener.UI.ViewHolders.ViewHolderNotificacion;

import java.util.ArrayList;

/**
 * Created by oemy9 on 16/09/2017.
 */

public class AdapterNotificacion extends RecyclerView.Adapter<ViewHolderNotificacion> {

    private ArrayList<NotificacionTabla>list;
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterNotificacion(Context context, ArrayList<NotificacionTabla>list){
            this.context=context;
            this.list=list;
            this.layoutInflater=LayoutInflater.from(this.context);
    }


    @Override
    public ViewHolderNotificacion onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=layoutInflater.inflate(R.layout.item_notificacion,parent,false);
        return new  ViewHolderNotificacion(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderNotificacion holder, int position) {
        NotificacionTabla item=getItem(position);
        holder.tvTitulo.setText(item.getTitulo());
        holder.tvDescripcion.setText(item.getDescripcion());
        holder.tvPaquete.setText(item.getPaquete());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public NotificacionTabla getItem(int position){
        return list.get(position);
    }
}
