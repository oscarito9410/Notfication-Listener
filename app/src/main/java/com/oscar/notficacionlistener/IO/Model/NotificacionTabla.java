package com.oscar.notficacionlistener.IO.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by oemy9 on 15/09/2017.
 */
@DatabaseTable(tableName = "NotificacionTabla")
public class NotificacionTabla {
    public static final String _ID="_ID";
    public static final String TITULO="TITULO";
    public static final String DESCRIPCION="DESCRIPCION";
    public static final String PAQUETE="PAQUETE";
    public static final String FECHA="FECHA_HORA";
    public static final String ENVIADO="ENVIADO";

    @DatabaseField(generatedId = true,columnName = _ID)
    private int id;
    @DatabaseField(columnName = TITULO)
    private String titulo;
    @DatabaseField(columnName=DESCRIPCION)
    private String descripcion;
    @DatabaseField(columnName = PAQUETE)
    private String paquete;
    @DatabaseField(columnName = FECHA)
    private Date fecha;

    @DatabaseField(columnName = ENVIADO)
    private boolean enviado;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}
