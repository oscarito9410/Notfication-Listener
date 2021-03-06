package com.oscar.notficacionlistener.UI.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oscar.notficacionlistener.IO.WebService.Model.NoticiaItem;
import com.oscar.notficacionlistener.R;
import com.oscar.notficacionlistener.Utils.linkPreview.SourceContent;
import com.oscar.notficacionlistener.Utils.linkPreview.TextCrawler;
import com.oscar.notficacionlistener.Utils.loaderView.LoaderImageView;
import com.oscar.notficacionlistener.Utils.loaderView.LoaderTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oemy9 on 01/10/2017.
 */

public class AdapterNoticiaRecycler extends RecyclerView.Adapter <AdapterNoticiaRecycler.ViewHolderNoticia> {

    private List <NoticiaItem> listNotificaciones;
    private Context context;
    private LayoutInflater layoutInflater;

    public interface OnItemClickListener {

        void onItemClickListener (NoticiaItem item);
    }

    private OnItemClickListener mOnItemClickListener;

    public AdapterNoticiaRecycler (Context context, List <NoticiaItem> listNotificaciones, OnItemClickListener listener) {
        this.listNotificaciones = listNotificaciones;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.mOnItemClickListener = listener;
    }


    @Override
    public ViewHolderNoticia onCreateViewHolder (ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolderNoticia(itemView);
    }


    @Override
    public void onBindViewHolder (final ViewHolderNoticia holderNoticia, final int position) {

        holderNoticia.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClickListener((NoticiaItem) getItem(position));
            }
        });

        final NoticiaItem selectedItem = (NoticiaItem) getItem(position);
        TextCrawler textCrawler = new TextCrawler();
        textCrawler.makePreview(new TextCrawler.LinkPreviewCallback() {
            @Override
            public void onPreExecute () {
                holderNoticia.imgNoticia.resetLoader();
                holderNoticia.tvTitulo.resetLoader();
                holderNoticia.tvDescripcion.resetLoader();
            }


            @Override
            public void onPosExecute (SourceContent sourceContent, boolean isNull) {
                if (!isNull) {
                    if (!sourceContent.getImages().isEmpty()) {
                        Picasso.with(context).load(sourceContent.getImages().get(0)).into(holderNoticia.imgNoticia);
                        selectedItem.setUrlImage(sourceContent.getImages().get(0));
                    }

                    holderNoticia.tvTitulo.setText(sourceContent.getTitle());
                    holderNoticia.tvDescripcion.setText(sourceContent.getDescription());
                    holderNoticia.tvFecha.setText(selectedItem.getReadeablePubdDate());
                }
            }

            @Override
            public void onError () {
                holderNoticia.tvTitulo.setText(selectedItem.getTitle());
                holderNoticia.tvDescripcion.setText(selectedItem.getDescription());
            }
        }, selectedItem.getLink());
    }

    private Object getItem (int position) {
        return listNotificaciones.get(position);
    }


    @Override
    public int getItemCount () {
        return listNotificaciones.size();
    }


    public class ViewHolderNoticia extends RecyclerView.ViewHolder {

        LoaderImageView imgNoticia;
        LoaderTextView tvTitulo;
        LoaderTextView tvDescripcion;
        TextView tvFecha;


        public ViewHolderNoticia (View itemView) {
            super(itemView);
            imgNoticia = (LoaderImageView) itemView.findViewById(R.id.imageNoticia);
            tvTitulo = (LoaderTextView) itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = (LoaderTextView) itemView.findViewById(R.id.tvDescripcion);
            tvFecha = (TextView) itemView.findViewById(R.id.tvFecha);
        }


    }


}
