package com.alphaville.coffeeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.alphaville.coffeeapplication.Model.CoffeeProduct;
import com.alphaville.coffeeapplication.Model.CoffeeSearcher;
import com.alphaville.coffeeapplication.Model.Database.CoffeeProductRepository;
import com.alphaville.coffeeapplication.Model.Review;

import java.util.ArrayList;
import java.util.List;

// Denna viewmodel borde användas av både searchlistan & searchbaren/filter
// Om problem: ta tillbaka ViewModelEngine
public class SearchListViewModel extends AndroidViewModel {

    private final MutableLiveData<CoffeeProduct> selected = new MutableLiveData<CoffeeProduct>();
    private MutableLiveData<Filter> filter = new MutableLiveData<>();

    private LiveData<List<CoffeeProduct>> filteredList;
    private CoffeeProductRepository repository;

    public SearchListViewModel(@NonNull Application application) {
        super(application);
        repository = new CoffeeProductRepository(application);
        filteredList = Transformations.switchMap(filter, (input) ->
        {
            if (input == null /*|| (filter.getValue().getString().equals("")
                                    && (filter.getValue().getSweetness() == 0 && filter.getValue().getMaxElevation() == 0)
            )*/) {
                System.out.println("get all products");
                return repository.getAllProducts();
            } else {
                System.out.println("filter");
                return repository.filter(input);
            }
        });
    }

    public LiveData<List<CoffeeProduct>> getFilteredList() {
        return filteredList;
    }


    public void setFilter(String query, String taste, String country, String process,
                          int acidityUpper, int acidityLower, int bodyUpper, int bodyLower,
                          int sweetnessUpper, int sweetnessLower, int minElevation, int maxElevation,
                          boolean isLiked) {
        filter.setValue(new Filter(query, taste, country, process, acidityUpper, acidityLower, bodyUpper, bodyLower,
                sweetnessUpper, sweetnessLower, minElevation, maxElevation, isLiked));
    }

    public void selectItem(CoffeeProduct product) {
        selected.setValue(product);
    }

    public LiveData<CoffeeProduct> getSelected() {
        return selected;
    }

    public class Filter {
        private String string, taste, country, process;
        // klockor, 0, 1, 2, 3, 4, 5
        private int acidityUpper, acidityLower, bodyUpper, bodyLower,
                sweetnessUpper, sweetnessLower, minElevation, maxElevation;
        private boolean isLiked;

        public Filter(String string, String taste, String country, String process,
                      int acidityUpper, int acidityLower, int bodyUpper, int bodyLower,
                      int sweetnessUpper, int sweetnessLower, int minElevation, int maxElevation,
                      boolean isLiked) {
            this.string = string;
            this.taste = taste;
            this.country = country;
            this.process = process;
            this.acidityUpper = acidityUpper;
            this.acidityLower = acidityLower;
            this.bodyUpper = bodyUpper;
            this.bodyLower = bodyLower;
            this.sweetnessUpper = sweetnessUpper;
            this.sweetnessLower = sweetnessLower;
            this.minElevation = minElevation;
            this.maxElevation = maxElevation;
            this.isLiked = isLiked;
        }

        public String getString() {
            return string;
        }

        public int getAcidityUpper() {
            return acidityUpper;
        }

        public int getAcidityLower() {
            return acidityLower;
        }

        public int getBodyUpper() {
            return bodyUpper;
        }

        public int getBodyLower() {
            return bodyLower;
        }

        public int getSweetnessUpper() {
            return sweetnessUpper;
        }

        public int getSweetnessLower() {
            return sweetnessLower;
        }

        public String getTaste() {
            return taste;
        }

        public String getCountry() {
            return country;
        }

        public boolean getIsLiked() {
            return isLiked;
        }

        public int getMinElevation() {
            return minElevation;
        }

        public int getMaxElevation() {
            return maxElevation;
        }

        public String getProcess() {
            return process;
        }
    }
}
