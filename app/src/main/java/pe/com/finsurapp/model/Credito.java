package pe.com.finsurapp.model;

public class Credito {
    int imgCredito;
    String nombreCredito;
    String descripcionCredito;

    public Credito(int imgCredito, String nombreCredito, String descripcionCredito) {
        this.imgCredito = imgCredito;
        this.nombreCredito = nombreCredito;
        this.descripcionCredito = descripcionCredito;
    }

    public int getImgCredito() {
        return imgCredito;
    }

    public void setImgCredito(int imgCredito) {
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
}
