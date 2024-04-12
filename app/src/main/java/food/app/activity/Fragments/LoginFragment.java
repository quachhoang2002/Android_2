package food.app.activity.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.facebook.FacebookActivity;
import com.google.android.material.textfield.TextInputEditText;

import food.app.activity.Activities.DashboardActivity;
import food.app.activity.Activities.LoginFBActivity;
import food.app.activity.R;
import food.app.activity.Request.LoginRequest;
import food.app.activity.Response.LoginResponse;
import food.app.activity.Response.PaymentResponse;
import food.app.activity.Services.LoginService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private static final String path = "/api/auth/login";
    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final TextInputEditText emailEditText = view.findViewById(R.id.email_txt);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_txt);
        view.findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                LoginRequest req = new LoginRequest();
                req.setEmail(email);
                req.setPassword(password);

                LoginService loginService = ServiceBuilder.buildService(LoginService.class);
                Call<LoginResponse> call = loginService.login(req);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Log.d("API_SUCCESS", response.toString());
                        if (response.isSuccessful()) {
                            LoginResponse paymentResponse = response.body();
                            Log.d("API_SUCCESS", paymentResponse.toString());
                            Log.d("API_SUCCESS", paymentResponse.getData().name);
                            //set to shared preferences
                            ShareRef shareRef = new ShareRef(getActivity().getApplicationContext());
                            shareRef.saveToken(paymentResponse.getData().token);
                            shareRef.saveUserInformation(paymentResponse.getData().id,paymentResponse.getData().email, paymentResponse.getData().phone);

                            Intent intent = new Intent(getActivity().getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                        } else {
                            // error response, no access to resource?
                            Log.e("API_FAILURE", "Request Failed", new Exception());
                            Toast.makeText(getActivity().getApplicationContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("API_FAILURE", "Request Failed", t);
                    }
                });


            }
        });


        //click on fb icon
        ImageView facebookIcon = view.findViewById(R.id.facebook_icon);

        if (facebookIcon != null) {
            facebookIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginFBActivity.class);
                    startActivity(intent);
                }
            });
        }


        return view;
    }
}