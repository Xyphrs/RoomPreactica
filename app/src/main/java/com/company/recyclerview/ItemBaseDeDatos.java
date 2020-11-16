package com.company.recyclerview;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

@Database(entities = {Items.class}, version = 1, exportSchema = false)
public abstract class ItemBaseDeDatos extends RoomDatabase {
    private static volatile ItemBaseDeDatos INSTANCIA;

    public abstract ItemsDao obtenerItemsDao();
    static ItemBaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (ItemBaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            ItemBaseDeDatos.class, "elementos.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }
    @Dao
    interface ItemsDao {
        @Query("SELECT * FROM Items")
        LiveData<List<Items>> obtener();

        @Insert
        void insertar(Items elemento);

        @Update
        void actualizar(Items elemento);

        @Delete
        void eliminar(Items elemento);
    }
}
