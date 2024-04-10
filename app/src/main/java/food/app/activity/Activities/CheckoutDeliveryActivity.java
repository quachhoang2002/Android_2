package food.app.activity.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import food.app.activity.R;

public class CheckoutDeliveryActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private TextView proceedToPaymentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_delivery);

        // Initialize views
        backBtn = findViewById(R.id.back_btn);
        proceedToPaymentBtn = findViewById(R.id.payment_btn);

        // Set onClickListener for back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This will just finish the current activity and go back to the previous one.
                finish();
            }
        });

        // Set onClickListener for the proceed to payment button
        proceedToPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // For now, let's just show a toast message. You can replace this with your actual payment logic.
                Toast.makeText(CheckoutDeliveryActivity.this, "Proceeding to payment...", Toast.LENGTH_SHORT).show();
                // Add your payment intent here or any other logic you want to perform.
            }
        });

        // Optionally, you can also fill in customer details programmatically if needed.
        // Here is how you can do it for example:
        TextView nameCus = findViewById(R.id.nameCus);
        TextView addrCus = findViewById(R.id.addrCus);
        TextView phoneCus = findViewById(R.id.phoneCus);

        // Assuming these details are fetched from somewhere, like a database or passed through intent.
        nameCus.setText("Marvis Kparobo");
        addrCus.setText("Km 5 refinery road opposite republic road, effurun, delta state");
        phoneCus.setText("+234 9011039271");

        // Handle other components like RadioButtons etc. based on your requirements.
    }
}
