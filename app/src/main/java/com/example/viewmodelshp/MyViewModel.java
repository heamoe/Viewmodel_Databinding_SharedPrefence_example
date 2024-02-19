package com.example.viewmodelshp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    String key = getApplication().getString(R.string.data);
    String shpname = getApplication().getString(R.string.myshp);
    SavedStateHandle handle;
    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        if (handle.contains("data")){
            load();
        }
    }
    void load(){
        SharedPreferences shp = getApplication().getSharedPreferences(shpname, Context.MODE_PRIVATE);
        int x = shp.getInt(key,0);
        handle.set(key,x);
    }
    public LiveData<Integer> getNumber(){
        return handle.getLiveData(key);
    }
    void save() {
        SharedPreferences shp = getApplication().getSharedPreferences(shpname, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key,getNumber().getValue());
        editor.apply();
    }
    public void add(int  a){
        handle.set(key,getNumber().getValue()+a);
        save();
    }
}
