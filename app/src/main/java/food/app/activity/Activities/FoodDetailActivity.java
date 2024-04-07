package food.app.activity.Activities;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import food.app.activity.Models.CartModel;
import food.app.activity.R;
import food.app.activity.Services.CartService;
import food.app.activity.Services.ServiceBuilder;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

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
        food_id = intent.getIntExtra("food_id",0);
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
                cartModel.setProductId(2);
                cartModel.setQuantity(1);
                cartModel.setUserId(1);
                cartModel.setPrice(food_price_db);
                cartModel.setCreatedAt("2023-10-22");


                CartService cartService = ServiceBuilder.buildService(CartService.class);
                Call<CartModel> call = cartService.addToCart(cartModel);
                call.enqueue(new Callback<CartModel>() {
                    @Override
                    public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                        if (response.isSuccessful()) {
//                            String message = response.body();
//                            System.out.println(message);
                            System.out.println("Success");
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
                    public void onFailure(Call<CartModel> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });


//                orderFood.setData(Uri.parse("mailto:"));
//                Toast.makeText( getApplicationContext(), "acscascasc", Toast.LENGTH_LONG).show();
//                String[] email = {"YourEmail@gmail.com", "", ""};
//                String subject = "New Order for " + intent.getStringExtra("food_name" + "!");
//                String text = "Order Details \n\n " + "Food Name: " + intent.getStringExtra("food_name" + "\nFood Price: " + intent.getFloatExtra("food_price")
//                        + "Address: ");
//                orderFood.putExtra(Intent.EXTRA_EMAIL, email);
//                orderFood.putExtra(Intent.EXTRA_SUBJECT, subject);
//                orderFood.putExtra(Intent.EXTRA_TEXT, text);
//                if (orderFood.resolveActivity(getApplicationContext().getPackageManager()) != null) {
//                    startActivity(orderFood);
//                }

            }
        });

    }
}