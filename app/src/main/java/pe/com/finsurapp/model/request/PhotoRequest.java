package pe.com.finsurapp.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoRequest {

    @SerializedName("codUser")
    @Expose
    private String codUser;
    @SerializedName("desContenido")
    @Expose
    private String desContenido;

    public PhotoRequest(String codUser, String desContenido) {
        this.codUser = codUser;
        this.desContenido = desContenido;
    }

    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    public String getDesContenido() {
        return desContenido;
    }

    public void setDesContenido(String desContenido) {
        this.desContenido = desContenido;
    }
}
