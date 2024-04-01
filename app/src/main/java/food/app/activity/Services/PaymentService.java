package food.app.activity.Services;

import food.app.activity.Request.PaymentRequest;
import food.app.activity.Response.PaymentResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PaymentService {
    @POST("/api/payment/submit")
    Call<PaymentResponse> submitPayment(@Header("Token") String authToken, @Body PaymentRequest paymentRequest);
}
