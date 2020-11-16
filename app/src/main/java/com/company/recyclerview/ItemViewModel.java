package com.company.recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    ItemRepositorio itemRepositorio;

    MutableLiveData<Items> elementoSeleccionado = new MutableLiveData<>();

    public ItemViewModel(@NonNull Application application) {
        super(application);

        itemRepositorio = new ItemRepositorio(application);
    }

    void establecerElementoSeleccionado(Items items){
        elementoSeleccionado.setValue(items);
    }
    LiveData<List<Items>> obtener(){
        return itemRepositorio.obtener();
    }

    void insertar(Items items){
        itemRepositorio.insertar(items);
    }

    void eliminar(Items items){
        itemRepositorio.eliminar(items);
    }

    void actualizar(Items items){
        itemRepositorio.actualizar(items);
    }
}
