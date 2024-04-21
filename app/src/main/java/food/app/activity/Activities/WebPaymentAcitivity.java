package food.app.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import food.app.activity.R;
import food.app.activity.Request.PaymentRequest;
import food.app.activity.Response.PaymentResponse;
import food.app.activity.Services.PaymentService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebPaymentAcitivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_web_page);

        webView = findViewById(R.id.payment_webview);

        int orderID = getIntent().getIntExtra("order_id", 0);
        fetchPaymentPage(orderID);
    }


    private void showWebPage(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://t.hoangdeptrai.online/api/payment/confirm")) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Intent to start DashboardActivity after delay
                            Intent intent = new Intent(WebPaymentAcitivity.this, DashboardActivity.class);
                            startActivity(intent);
                        }
                    }, 200); // Delay in milliseconds, e.g., 2000ms for 2 seconds

                    Intent intent = new Intent(WebPaymentAcitivity.this, DashboardActivity.class);
                    startActivity(intent);
                    return true; // Indicates you've handled the URL
                }
                return false; // Let the WebView handle the URL
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    //call to get the payment response
    private void fetchPaymentPage(int orderID) {
        PaymentService paymentService = ServiceBuilder.buildService(PaymentService.class);
        String token = getToken();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderID(orderID);
        paymentRequest.setOrderInfo("Order Info");

        Log.d("API_PARAMS", paymentRequest.toString());

        Call<PaymentResponse> call = paymentService.submitPayment(token, paymentRequest);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                Log.d("API_SUCCESS", response.toString());
                if (response.isSuccessful()) {
                    PaymentResponse paymentResponse = response.body();
                    Log.d("API_SUCCESS", paymentResponse.toString());
                    Log.d("API_SUCCESS", paymentResponse.getData().getPaymentURL());
                    showWebPage(paymentResponse.getData().getPaymentURL());
                } else {
                    Toast.makeText(WebPaymentAcitivity.this, "Failed to get payment page", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WebPaymentAcitivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                Log.e("API_FAILURE", "Request Failed", t);
                Intent intent = new Intent(WebPaymentAcitivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

    }

    private String getToken() {
        ShareRef token = new ShareRef(this);
//        token.saveToken("cafab202-e1711888676203");
        return token.getToken();
    }



}
