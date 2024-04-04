package food.app.activity.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import food.app.activity.Adapters.CartAdapter;
import food.app.activity.Models.CartItem;
import food.app.activity.Models.CartItemModel;
import food.app.activity.Models.CartResponse;
import food.app.activity.Models.FoodItemModel;
import food.app.activity.R;
import food.app.activity.Services.CartService;
import food.app.activity.Services.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Sample data
        List<CartItemModel> productListView = new ArrayList<>();
        CartService cartService = ServiceBuilder.buildService(CartService.class);
        Call<CartResponse> call = cartService.getCartByUserId(1);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    CartResponse cartResponse = response.body();
                    List<FoodItemModel> productList = cartResponse.getProductItems();
                    List<CartItem> cartLists = cartResponse.getCartItems();
                    System.out.println(productList);
                    System.out.println(cartLists);

                    for (CartItem cartItem : cartLists) {
                        for (FoodItemModel product : productList) {
                            if (cartItem.getProductId() == product.getId()) {
                                String imageResId = product.getImagePath();
                                String productName = product.getName();
                                double price = product.getPrice();
                                int quantity = cartItem.getQuantity();
                                System.out.println(quantity);

                                productListView.add(new CartItemModel(imageResId, productName, price, quantity));
                                break;
                            }
                        }
                    }
                    CartAdapter productAdapter = new CartAdapter(CartActivity.this, productListView);
                    ListView listView = findViewById(R.id.cartListView);
                    listView.setAdapter(productAdapter);

                } else {
                    try {
                        String errorMessage = response.errorBody().string();
                        System.out.println("Error: " + errorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
//        productList.add(new CartItemModel(R.mipmap.ic_launcher, "Product 1", 10.99, 2));
//        productList.add(new CartItemModel(R.mipmap.ic_launcher, "Product 2", 24.99, 1));
//        productList.add(new CartItemModel(R.mipmap.ic_launcher, "Product 3", 5.99, 3));

        // Set up the adapter
//        CartAdapter productAdapter = new CartAdapter(this, productListView);
//
//        // Attach the adapter to a ListView
//        ListView listView = findViewById(R.id.cartListView);
//        listView.setAdapter(productAdapter);
    }
}