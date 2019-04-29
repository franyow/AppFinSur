package pe.com.finsurapp.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SolicitudDao {
    @Insert
    void insert(SolicitudEntity solicitudEntity);

    @Update
    void update(SolicitudEntity solicitudEntity);

    @Delete
    void delete(SolicitudEntity solicitudEntity);

    @Query("DELETE FROM solicitudentity WHERE id = :idSoli")
    void deleteById(String idSoli);

    @Query("SELECT * FROM solicitudentity ")
    LiveData<List<SolicitudEntity>> findAllSoli();
}

