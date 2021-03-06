package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.Database.CoffeeProductRepository;
import com.alphaville.coffeeapplication.Model.Database.ReviewRepository;
import android.app.Application;

import com.alphaville.coffeeapplication.Model.Review;

import java.util.ArrayList;
import java.util.List;

public class RecTabViewModel extends AndroidViewModel {

    private final MutableLiveData<CoffeeProduct> selected = new MutableLiveData<CoffeeProduct>();
    private LiveData<List<CoffeeProduct>> filteredList;
    private CoffeeProductRepository repository;
    private LiveData<List<CoffeeProduct>> rankedList;


    public RecTabViewModel(@NonNull Application application) {
        super(application);
        repository = new CoffeeProductRepository(application);
        filteredList = repository.getAllProducts();

    }



    /**
     * skicka filterList till Matcher och få rankedList
     */
    public LiveData<List<CoffeeProduct>> getRankedList(){
        return filteredList;
    }

    public MutableLiveData<CoffeeProduct> getSelected() {
        return selected;
    }

    public void selectItem(CoffeeProduct product) {
        selected.setValue(product);
    }
}