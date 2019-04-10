package pe.com.finsurapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credito {
    @SerializedName("codCredito")
    @Expose
    private String codCredito;
    @SerializedName("imgCredito")
    @Expose
    private String imgCredito;
    @SerializedName("nombreCredito")
    @Expose
    private String nombreCredito;
    @SerializedName("descripcionCredito")
    @Expose
    private String descripcionCredito;

    public Credito(String codCredito, String imgCredito, String nombreCredito, String descripcionCredito) {
        this.codCredito = codCredito;
        this.imgCredito = imgCredito;
        this.nombreCredito = nombreCredito;
        this.descripcionCredito = descripcionCredito;
    }

    public String getImgCredito() {
        return imgCredito;
    }

    public void setImgCredito(String imgCredito) {
        this.imgCredito = imgCredito;
    }

    public String getNombreCredito() {
        return nombreCredito;
    }

    public void setNombreCredito(String nombreCredito) {
        this.nombreCredito = nombreCredito;
    }

    public String getDescripcionCredito() {
        return descripcionCredito;
    }

    public void setDescripcionCredito(String descripcionCredito) {
        this.descripcionCredito = descripcionCredito;
    }
    public String getCodCredito() {
        return codCredito;
    }
    public void setCodCredito(String codCredito) {
        this.codCredito = codCredito;
    }
}
