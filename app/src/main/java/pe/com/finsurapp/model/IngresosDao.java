package pe.com.finsurapp.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface IngresosDao {
    @Insert
    void insertIngreso(IngresoEntity ingresoEntity);

    @Update
    void updateIngreso(IngresoEntity ingresoEntity);

    @Delete
    void deleteIngreso(IngresoEntity ingresoEntity);



}
