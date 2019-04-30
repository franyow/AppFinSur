package pe.com.finsurapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleCredito {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("codCredito")
    @Expose
    private Integer codCredito;
    @SerializedName("requisitos")
    @Expose
    private String requisitos;
    @SerializedName("beneficios")
    @Expose
    private String beneficios;

    public DetalleCredito() {
    }

    public DetalleCredito(Integer id, Integer codCredito, String requisitos, String beneficios) {
        this.id = id;
        this.codCredito = codCredito;
        this.requisitos = requisitos;
        this.beneficios = beneficios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodCredito() {
        return codCredito;
    }

    public void setCodCredito(Integer codCredito) {
        this.codCredito = codCredito;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }
}
