package food.app.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import food.app.activity.Models.CartResponse;
import food.app.activity.R;
import food.app.activity.Response.PaymentResponse;
import food.app.activity.Services.CartService;
import food.app.activity.Services.PaymentService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutDeliveryActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private TextView proceedToPaymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_delivery);

        TextView total = findViewById(R.id.total);
        EditText name = findViewById(R.id.nameCus);
        EditText phone = findViewById(R.id.phoneCus);
        EditText address = findViewById(R.id.addrCus);
        TextView total_price = findViewById(R.id.total_price);

        CartService cartSvc = ServiceBuilder.buildService(CartService.class);
        ShareRef shareRef = new ShareRef(CheckoutDeliveryActivity.this);

        Log.d("USER_ID", String.valueOf(shareRef.getUserID()));
        Call<CartResponse> call = cartSvc.getCartByUserId(shareRef.getUserID());
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                Log.d("API_SUCCESS", response.toString());
                if (response.isSuccessful()) {
                    CartResponse cartResponse = response.body();
                    int totalPrice = cartResponse.getTotalPrice();
                    Log.d("API_SUCCESS", cartResponse.toString());
                    Log.d("API_SUCCESS", String.valueOf(totalPrice));
                    String totalStr = String.valueOf(totalPrice);
                    total_price.setText(totalStr+" VND");
                } else {
               }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Request Failed", t);
            }
        });


        // Initialize views
        backBtn = findViewById(R.id.back_btn);
        proceedToPaymentBtn = findViewById(R.id.payment_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        proceedToPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutDeliveryActivity.this, CheckoutPaymentActivity.class);
                //remove vnd
                String total_price_str = total_price.getText().toString();
                String total_price_str_no_vnd = total_price_str.substring(0, total_price_str.length() - 4);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("address", address.getText().toString());
                intent.putExtra("total", total_price_str_no_vnd);
                startActivity(intent);
           }
        });

    }
}
