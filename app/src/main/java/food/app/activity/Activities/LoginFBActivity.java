package food.app.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import food.app.activity.R;
import food.app.activity.Request.FacebookLoginRequest;
import food.app.activity.Response.FacebookInformation;
import food.app.activity.Response.LoginResponse;
import food.app.activity.Services.Facebook;
import food.app.activity.Services.LoginService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;

public class LoginFBActivity extends AppCompatActivity {
    private static final long SPLASH_TIMER = 3000;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ShareRef shareRef = new ShareRef(getApplicationContext());
//        shareRef.clear();


        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        try {
                                            String email = object.has("email") ? object.getString("email") : "";
                                            // You cannot directly access the phone number, but you can get other public profile details
                                            String name = object.has("name") ? object.getString("name") : "";

                                            Log.d("Facebook", "onCompleted: " + email + " " + name);
                                            Log.d("Facebook", "onCompleted: " + object.toString());
                                            ShareRef shareRef = new ShareRef(getApplicationContext());
                                            shareRef.saveFacebookUserInformation(email, name, loginResult.getAccessToken().getToken());
                                            // Handle the values here
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });


                        FacebookInformation facebookInformation = Facebook.getFacebookInformation(getApplicationContext());
                        FacebookLoginRequest req = new FacebookLoginRequest(facebookInformation.getEmail(), "", facebookInformation.getName(), "facebook");
                        LoginService loginService = ServiceBuilder.buildService(LoginService.class);
                        Call<LoginResponse> call = loginService.loginWithFacebook(req);
                        call.enqueue(new retrofit2.Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                                Log.d("API_SUCCESS", response.toString());
                                if (response.isSuccessful()) {
                                    LoginResponse paymentResponse = response.body();
                                    Log.d("API_SUCCESS", paymentResponse.toString());
                                    Log.d("API_SUCCESS", paymentResponse.getData().name);


                                    //set to shared preferences
                                    ShareRef shareRef = new ShareRef(getApplicationContext());
                                    shareRef.saveToken(paymentResponse.getData().token);
                                    shareRef.saveUserInformation(paymentResponse.getData().id, paymentResponse.getData().email, paymentResponse.getData().phone);

                                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.d("API_FAILURE", t.toString());
                                Toast.makeText(getApplicationContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        // App cod
                        Toast.makeText(getApplicationContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}