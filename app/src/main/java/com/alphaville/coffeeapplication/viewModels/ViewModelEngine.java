package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.alphaville.coffeeapplication.Model.ModelFacade;

/**
 * Abstract ViewModel-class that generates a single instance of the model for all its subclasses.
 */
public abstract class ViewModelEngine extends ViewModel {

    /**
     * Single instance of model
     */
    private static final ModelFacade model = new ModelFacade();

    public ViewModelEngine(Application application) {
    }

    protected final ModelFacade getModel() {
        return model;
    }
}
