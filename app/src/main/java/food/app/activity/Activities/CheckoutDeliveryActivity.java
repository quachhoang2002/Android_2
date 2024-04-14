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


        CartService cartSvc = ServiceBuilder.buildService(CartService.class);
        ShareRef shareRef = new ShareRef(CheckoutDeliveryActivity.this);

        Call<CartResponse> call = cartSvc.getCartByUserId(1);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                Log.d("API_SUCCESS", response.toString());
                if (response.isSuccessful()) {
                    CartResponse cartResponse = response.body();
                    int totalPrice = cartResponse.getTotalPrice();
                    String totalStr = String.valueOf(totalPrice);
                    total.setText("Total: " + totalStr + " VND");

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
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("address", address.getText().toString());
                intent.putExtra("total", total.getText().toString());
                startActivity(intent);
           }
        });

    }
}
