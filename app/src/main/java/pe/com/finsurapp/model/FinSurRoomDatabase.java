package pe.com.finsurapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {SolicitudEntity.class,IngresoEntity.class}, version = 1)
public abstract class FinSurRoomDatabase extends RoomDatabase {
    public abstract SolicitudDao getSolicitudDao();
    public abstract IngresosDao getIngresoDao();
    private static volatile FinSurRoomDatabase INSTANCE;

    public static FinSurRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (FinSurRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FinSurRoomDatabase.class,"finsur_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
