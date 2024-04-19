package food.app.activity.Services;

import food.app.activity.Request.FacebookLoginRequest;
import food.app.activity.Request.LoginRequest;
import food.app.activity.Request.PaymentRequest;
import food.app.activity.Request.RegisterRequest;
import food.app.activity.Response.LoginResponse;
import food.app.activity.Response.PaymentResponse;
import food.app.activity.Response.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/auth/login")
    Call<LoginResponse> loginWithFacebook(@Body FacebookLoginRequest loginRequest);

    @POST("/api/auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest loginRequest);

}
