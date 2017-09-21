package com.oscar.notficacionlistener.UI.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oscar.notficacionlistener.R;

/**
 * Created by oemy9 on 16/09/2017.
 */

public class ViewHolderNotificacion extends RecyclerView.ViewHolder {

    public TextView tvTitulo,tvDescripcion,tvPaquete;

    public ViewHolderNotificacion(View itemView) {
        super(itemView);
        tvTitulo=(TextView)itemView.findViewById(R.id.tvTitulo);
        tvDescripcion=(TextView)itemView.findViewById(R.id.tvDescripcion);
        tvPaquete=(TextView)itemView.findViewById(R.id.tvPaquete);
    }
}
