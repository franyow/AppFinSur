package pe.com.finsurapp.model;

public class Agencia {
    String nombreAgencia;
    String direccionAgencia;
    String telefonoAgencia;
    String latAgencia;
    String longAgencia;
    String departamento;

    public Agencia() {
    }

    public Agencia(String nombreAgencia, String direccionAgencia, String telefonoAgencia, String latAgencia, String longAgencia, String departamento) {
        this.nombreAgencia = nombreAgencia;
        this.direccionAgencia = direccionAgencia;
        this.telefonoAgencia = telefonoAgencia;
        this.latAgencia = latAgencia;
        this.longAgencia = longAgencia;
        this.departamento = departamento;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
