package food.app.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import food.app.activity.Models.CartResponse;
import food.app.activity.R;
import food.app.activity.Request.OrderRequest;
import food.app.activity.Response.OrderResponse;
import food.app.activity.Services.CartService;
import food.app.activity.Services.PaymentService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutPaymentActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private TextView proceedToPaymentBtn;
    private RadioGroup paymentMethodGroup, deliveryMethodGroup;
    private TextView totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_payment);

        Bundle extras = getIntent().getExtras();
        String total = extras.getString("total");
        String name = extras.getString("name");
        String phone = extras.getString("phone");
        String address = extras.getString("address");

        TextView textTotal = findViewById(R.id.total);

        System.out.println(total);


        // Khởi tạo các thành phần giao diện
        backBtn = findViewById(R.id.back_btn);
        proceedToPaymentBtn = findViewById(R.id.payment_btn);
        paymentMethodGroup = findViewById(R.id.payment);
        deliveryMethodGroup = findViewById(R.id.delivery);
        totalCost = findViewById(R.id.total);
        totalCost.setText(total + " VND");

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

                ShareRef shareRef = new ShareRef(CheckoutPaymentActivity.this);
                Log.d("USER_ID", String.valueOf(shareRef.getUserID()));
                Log.d("USER_EMAIL", shareRef.getEmail());

                PaymentService paymentSvc = ServiceBuilder.buildService(PaymentService.class);
                OrderRequest orderRequest = new OrderRequest();
                orderRequest.setCustomerName(name);
                orderRequest.setShippingAddress(address);
                orderRequest.setCustomerPhone(phone);
                orderRequest.setEmail_receive(shareRef.getEmail());
                orderRequest.setStatus(0);
                orderRequest.setTotal_price(Double.parseDouble(total));
                OrderRequest.User user = new OrderRequest.User();
                user.setId(shareRef.getUserID());
                orderRequest.setUser(user);

                Log.d("ORDER_REQUEST", orderRequest.toString());


                Call<OrderResponse> call = paymentSvc.addOrder(orderRequest);
                call.enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                        Log.d("API_SUCCESS", response.toString());
                        if (response.isSuccessful()) {
                            Log.d("API_SUCCESS_asdsad", response.body().toString());
                            OrderResponse cartResponse = response.body();
                            Double totalPrice = cartResponse.getData().totalPrice;
                            String totalStr = String.valueOf(totalPrice);
                            textTotal.setText("Total: " + totalStr + " VND");
                            String total = totalCost.getText().toString();
                            if (paymentMethod.equals("Card")) {
                                Integer id = cartResponse.getData().id;
                                Intent intent = new Intent(CheckoutPaymentActivity.this, WebPaymentAcitivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            } else {
                                 //back to dashboard
                                Intent intent = new Intent(CheckoutPaymentActivity.this, DashboardActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Log.d("API_SUCCESS", "Response not successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderResponse> call, Throwable t) {
                        Log.e("API_FAILURE", "Request Failed", t);
                    }
                });



//                // Hiển thị thông tin thanh toán (ví dụ)
//                Toast.makeText(CheckoutPaymentActivity.this,
//                        "Payment Method: " + paymentMethod + "\nDelivery Method: " + deliveryMethod +
//                                "\nTotal: " + total, Toast.LENGTH_LONG).show();
            }
        });
    }
}
