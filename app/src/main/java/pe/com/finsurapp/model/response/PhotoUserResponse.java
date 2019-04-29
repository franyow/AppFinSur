package pe.com.finsurapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoUserResponse {
    @SerializedName("codPhoto")
    @Expose
    private Integer codPhoto;
    @SerializedName("codUser")
    @Expose
    private Integer codUser;
    @SerializedName("desContenido")
    @Expose
    private String desContenido;



    public Integer getCodPhoto() {
        return codPhoto;
    }

    public void setCodPhoto(Integer codPhoto) {
        this.codPhoto = codPhoto;
    }

    public Integer getCodUser() {
        return codUser;
    }

    public void setCodUser(Integer codUser) {
        this.codUser = codUser;
    }

    public String getDesContenido() {
        return desContenido;
    }

    public void setDesContenido(String desContenido) {
        this.desContenido = desContenido;
    }
}
