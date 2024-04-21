package food.app.activity.Activities;


import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.security.AccessControlContext;


import food.app.activity.Models.CartModel;
import food.app.activity.R;
import food.app.activity.Services.CartService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends AppCompatActivity {

    ImageView food_back;
    TextView food_name;
    TextView food_price;
    TextView food_description;
    ImageView food_image;
    TextView ordernow;
    private static int food_id;
    private static float food_price_db;
    private ShareRef shareRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        shareRef = new ShareRef(FoodDetailActivity.this);

        food_back = findViewById(R.id.food_back);
        food_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();

        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        food_image = findViewById(R.id.food_image);
        food_description = findViewById(R.id.description_info);
        food_price_db = intent.getFloatExtra("food_price", 0.0f);
        System.out.println(food_price_db);
        food_name.setText(intent.getStringExtra("food_name"));
        food_price.setText(String.valueOf(food_price_db) + "$");
        String imageUrl = intent.getStringExtra("food_image");
        imageUrl = imageUrl.replace("http://", "https://");
        food_id = intent.getIntExtra("food_id", 0);
        System.out.println("Imasdasd " + imageUrl);
        Picasso.get()
                .load(imageUrl)
//                .placeholder(R.drawable.food_image)
//                .error(R.drawable.food_image)
                .resize(200, 200)
                .centerCrop()
                .into(food_image);
        food_description.setText(intent.getStringExtra("food_desc"));

        ordernow = findViewById(R.id.food_order);
        ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderFood = new Intent(Intent.ACTION_SENDTO);
                CartModel cartModel = new CartModel();
                cartModel.setProductId(food_id);
                cartModel.setQuantity(1);
                cartModel.setUser_id(shareRef.getUserID());
                cartModel.setPrice(food_price_db);

                CartService cartService = ServiceBuilder.buildService(CartService.class);
                Call<CartModel> call = cartService.addToCart(cartModel);

                System.out.println(cartModel);
                call.enqueue(new Callback<CartModel>() {
                    @Override
                    public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                        System.out.println("Response: " + response);
                        if (response.isSuccessful()) {
                            String message = response.message();
                            Toast.makeText(FoodDetailActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                String errorMessage = response.errorBody().string();
                                //get message property from json
                                Toast.makeText(FoodDetailActivity.this, "San pham da het hang!", Toast.LENGTH_SHORT).show();
                                System.out.println("Error: " + errorMessage);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CartModel> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }
        });

    }
}