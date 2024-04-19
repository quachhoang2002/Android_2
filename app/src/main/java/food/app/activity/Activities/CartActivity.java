package food.app.activity.Activities;

import android.content.Intent;
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
    ListView listView;
    public CartAdapter productAdapter;
    public List<CartItemModel> productListView;
    final boolean[] isNull = {false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Sample data
        productListView = new ArrayList<>();
        CartService cartService = ServiceBuilder.buildService(CartService.class);
        Call<CartResponse> call = cartService.getCartByUserId(1);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    CartResponse cartResponse = response.body();
                    List<FoodItemModel> productList = cartResponse.getProductItems();
                    List<CartItem> cartLists = cartResponse.getCartItems();
                    System.out.println(productList);
                    System.out.println(cartLists);

                    for (CartItem cartItem : cartLists) {
                        for (FoodItemModel product : productList) {
                            if (cartItem.getProductId() == product.getId()) {
                                int productId = product.getId();
                                String imageResId = product.getImagePath();
                                String productName = product.getName();
                                double price = product.getPrice();
                                int quantity = cartItem.getQuantity();
                                System.out.println(productId);

                                productListView.add(new CartItemModel(productId,imageResId, productName, price, quantity));
                                break;
                            }
                        }
                    }




                } else {
                    try {
                        isNull[0] = true;
                        String errorMessage = response.errorBody().string();
                        System.out.println("Error: " + errorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (isNull[0]){
                    Intent intent = new Intent(CartActivity.this, NoOrderActivity.class);
                    startActivity(intent);
                }else {
                    productAdapter = new CartAdapter(CartActivity.this, productListView);
                    listView = findViewById(R.id.cartListView);
                    listView.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
//    public void reloadView() {
//        if (productAdapter != null) {
//            productAdapter.notifyDataSetChanged();
//        }else {
//            Intent intent = new Intent(CartActivity.this, NoOrderActivity.class);
//            startActivity(intent);
//        }
//
//    }

}