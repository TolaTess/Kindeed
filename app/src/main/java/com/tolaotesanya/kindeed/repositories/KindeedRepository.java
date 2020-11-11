package com.tolaotesanya.kindeed.repositories;

import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.modellayer.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class KindeedRepository {
    private MutableLiveData<List<Product>> mutableLiveData;

    public LiveData<List<Product>> getProducts(){
        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
            loadProducts();
        }
        return mutableLiveData;
    }

    private void loadProducts() {
        //get list of products
        List<Product> productList = new ArrayList<>();

        //room or database call here
        productList.add(new Product(UUID.randomUUID().toString(), "Sandwich",
                "Donate 12 sandwich to homeless people in Dublin city center",
                "Food", 24, 3, R.drawable.sandwich));
        productList.add(new Product(UUID.randomUUID().toString(), "Chocolate",
                "Send 10 box of chocolate to children in Drogheda hospital",
                "Children", 40, 3, R.drawable.chocolate));
        productList.add(new Product(UUID.randomUUID().toString(), "Haircut",
                "Give 5 homeless people haircut",
                "Grooming", 50, 3, R.drawable.men_hair_cut));
        productList.add(new Product(UUID.randomUUID().toString(), "Tops",
                "Donate 5 tops for homeless people",
                "Grooming", 30, 3, R.drawable.cardigans));
        productList.add(new Product(UUID.randomUUID().toString(), "Party",
                "Sponsor a child's birthday party",
                "Donate", 60, 3, R.drawable.party));
        productList.add(new Product(UUID.randomUUID().toString(), "Toys",
                "Donate toys to an orphanage",
                "Children", 50, 3, R.drawable.toys));
        productList.add(new Product(UUID.randomUUID().toString(), "Food Supplies",
                "Donate food supplies to a soup kitchen",
                "Food", 40, 3, R.drawable.food_supplies));
        productList.add(new Product(UUID.randomUUID().toString(), "Clothes",
                "Donate cloth items to children in need",
                "Children", 40, 3, R.drawable.clothes_children));

        mutableLiveData.setValue(productList);
    }
/*
    public void loadRawFile() {
        //get raw json file
        Resources resources = Resources.getSystem();
        InputStream inputStream = resources.openRawResource(R.raw.products);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()){
            builder.append(scanner.nextLine());
        }

        parseJson(builder.toString());
    }

    private void parseJson(String toString) {
        Gson gson = new Gson();
        Product product = gson.fromJson(toString, Product.class);
        Log.i("TAG", "parse******" + product.toString());
    }*/
}
