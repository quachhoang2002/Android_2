package food.app.activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import food.app.activity.Activities.DashboardActivity;
import food.app.activity.Activities.LoginActivity;
import food.app.activity.R;
import food.app.activity.Request.LoginRequest;
import food.app.activity.Request.RegisterRequest;
import food.app.activity.Response.LoginResponse;
import food.app.activity.Response.RegisterResponse;
import food.app.activity.Services.LoginService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final TextInputEditText emailEditText = view.findViewById(R.id.email_txt);
        final TextInputEditText nameEditText = view.findViewById(R.id.name_txt);
        final TextInputEditText phoneEditText = view.findViewById(R.id.phone_txt);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_txt);

        view.findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                RegisterRequest req = new RegisterRequest();
                req.setEmail(email);
                req.setPassword(password);
                req.setName(name);
                req.setPhone(phone);

                LoginService loginService = ServiceBuilder.buildService(LoginService.class);
                Call<RegisterResponse> call = loginService.register(req);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        Log.d("API_SUCCESS", response.toString());
                        if (response.isSuccessful()) {
                            RegisterResponse resp = response.body();
                            if (resp.getStatus().equals("success")) {

                                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                return;
                            }
                        } else {
                            // error response, no access to resource?
                            Log.e("API_FAILURE", "Request Failed", new Exception());
                            Toast.makeText(getActivity().getApplicationContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Log.e("API_FAILURE", "Request Failed", t);
                    }

                });

            };
        });

        return view;
    }
}