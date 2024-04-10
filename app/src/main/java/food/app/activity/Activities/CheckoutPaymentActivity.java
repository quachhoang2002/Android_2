package food.app.activity.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import food.app.activity.R;

public class CheckoutPaymentActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private TextView proceedToPaymentBtn;
    private RadioGroup paymentMethodGroup, deliveryMethodGroup;
    private TextView totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_payment);

        // Khởi tạo các thành phần giao diện
        backBtn = findViewById(R.id.back_btn);
        proceedToPaymentBtn = findViewById(R.id.payment_btn);
        paymentMethodGroup = findViewById(R.id.payment);
        deliveryMethodGroup = findViewById(R.id.delivery);
        totalCost = findViewById(R.id.cost);

        // Đặt sự kiện nhấp cho nút quay lại
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kết thúc activity này và quay về activity trước
                finish();
            }
        });

        // Đặt sự kiện nhấp cho nút tiến hành thanh toán
        proceedToPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý tiến hành thanh toán
                int selectedPaymentMethodId = paymentMethodGroup.getCheckedRadioButtonId();
                RadioButton selectedPaymentMethod = findViewById(selectedPaymentMethodId);
                String paymentMethod = selectedPaymentMethod.getText().toString();

                int selectedDeliveryMethodId = deliveryMethodGroup.getCheckedRadioButtonId();
                RadioButton selectedDeliveryMethod = findViewById(selectedDeliveryMethodId);
                String deliveryMethod = selectedDeliveryMethod.getText().toString();

                String total = totalCost.getText().toString();

                // Hiển thị thông tin thanh toán (ví dụ)
                Toast.makeText(CheckoutPaymentActivity.this,
                        "Payment Method: " + paymentMethod + "\nDelivery Method: " + deliveryMethod +
                                "\nTotal: " + total, Toast.LENGTH_LONG).show();
            }
        });
    }
}