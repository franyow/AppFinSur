package pe.com.finsurapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class IngresoEntity {
    @PrimaryKey(autoGenerate = true)
    Long idIngreso;
    String codUsuario;
    String ingreso;
    String descripcion;

    public IngresoEntity() {
    }

    public IngresoEntity(Long idIngreso, String codUsuario, String ingreso, String descripcion) {
        this.idIngreso = idIngreso;
        this.codUsuario = codUsuario;
        this.ingreso = ingreso;
        this.descripcion = descripcion;
    }

    public Long getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Long idIngreso) {
        this.idIngreso = idIngreso;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
