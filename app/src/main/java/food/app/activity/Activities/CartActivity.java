package food.app.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity  {
    ListView listView;
    public CartAdapter productAdapter;
    public List<CartItemModel> productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Sample data
        productListView = new ArrayList<>();
        loadData();
        System.out.println("CartActivity: " + productListView);
        ImageButton backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView starOrder = findViewById(R.id.starOrder);
        starOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CheckoutDeliveryActivity.class);
                startActivity(intent);
            }
        });

    }
    public void loadData(){
        CartService cartService = ServiceBuilder.buildService(CartService.class);
        ShareRef shareRef = new ShareRef(this);
        int userId = shareRef.getUserID();

        Call<CartResponse> call = cartService.getCartByUserId(userId);
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
                    System.out.println("CartActivity: " + productListView);

                } else {
                    try {
                        String errorMessage = response.errorBody().string();
                        System.out.println("Error: " + errorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                productAdapter = new CartAdapter(CartActivity.this, productListView, CartActivity.this);

                listView = findViewById(R.id.cartListView);
                listView.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}