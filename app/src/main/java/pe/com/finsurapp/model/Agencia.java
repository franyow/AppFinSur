package pe.com.finsurapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agencia {
    @SerializedName("codAgencia")
    @Expose
    private Integer codAgencia;
    @SerializedName("nombreAgencia")
    @Expose
    private String nombreAgencia;
    @SerializedName("direccionAgencia")
    @Expose
    private String direccionAgencia;
    @SerializedName("telefonoAgencia")
    @Expose
    private String telefonoAgencia;
    @SerializedName("latAgencia")
    @Expose
    private String latAgencia;
    @SerializedName("longAgencia")
    @Expose
    private String longAgencia;

    public Agencia() {
    }

    public Agencia(String nombreAgencia, String direccionAgencia, String telefonoAgencia, String latAgencia, String longAgencia) {
        this.nombreAgencia = nombreAgencia;
        this.direccionAgencia = direccionAgencia;
        this.telefonoAgencia = telefonoAgencia;
        this.latAgencia = latAgencia;
        this.longAgencia = longAgencia;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public String getDireccionAgencia() {
        return direccionAgencia;
    }

    public void setDireccionAgencia(String direccionAgencia) {
        this.direccionAgencia = direccionAgencia;
    }

    public String getTelefonoAgencia() {
        return telefonoAgencia;
    }

    public void setTelefonoAgencia(String telefonoAgencia) {
        this.telefonoAgencia = telefonoAgencia;
    }

    public String getLatAgencia() {
        return latAgencia;
    }

    public void setLatAgencia(String latAgencia) {
        this.latAgencia = latAgencia;
    }

    public String getLongAgencia() {
        return longAgencia;
    }

    public void setLongAgencia(String longAgencia) {
        this.longAgencia = longAgencia;
    }


}
