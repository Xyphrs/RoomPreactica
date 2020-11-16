package com.company.recyclerview;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ItemRepositorio {

    ItemBaseDeDatos.ItemsDao itemsDao;
    Executor executor = Executors.newSingleThreadExecutor();

    ItemRepositorio(Application application){
        itemsDao = ItemBaseDeDatos.obtenerInstancia(application).obtenerItemsDao();
    }



    LiveData<List<Items>> obtener(){
        return itemsDao.obtener();
    }

    void insertar(Items items){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                itemsDao.insertar(items);
            }
        });
    }

    void eliminar(Items items) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                itemsDao.eliminar(items);
            }
        });
    }

    public void actualizar(Items items) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                itemsDao.actualizar(items);
            }
        });
    }
}
