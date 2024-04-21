package food.app.activity.Activities;

import static food.app.activity.R.layout.activity_profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import food.app.activity.Models.ResponseObject;
import food.app.activity.Models.User;
import food.app.activity.R;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.Services.UserService;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ImageView profileImage;
    TextView profileName, profilePhone, profileMail;
    TextView orderBtn, logoutBtn, updateBtn;
    ImageButton backBtn;

    Bundle userBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_name);
        profilePhone = findViewById(R.id.profile_phone);
        profileMail = findViewById(R.id.profile_mail);

        logoutBtn = findViewById(R.id.logout_btn);
        backBtn = findViewById(R.id.back_btn);
        updateBtn = findViewById(R.id.Update);
        orderBtn = findViewById(R.id.to_order_btn);

        ShareRef shareRef = new ShareRef(this);

        UserService userService = ServiceBuilder.buildService(UserService.class);
        Call<ResponseObject<User>> call = userService.getUserProfile(shareRef.getUserID());
        call.enqueue(new Callback<ResponseObject<User>>() {
            @Override
            public void onResponse(Call<ResponseObject<User>> call, Response<ResponseObject<User>> response) {
                ResponseObject<User> body = response.body();
                if (body.getStatus().equals("success")) {
                    Log.i("ProfileActivity", "Success");
                    User user = body.getData();
                    profileName.setText(user.getName());
                    profilePhone.setText("Phone: " + user.getPhone());
                    profileMail.setText("Mail: " + user.getEmail());

                    userBundle = setBundle(user);
                } else {
                    System.out.println("Error: " + body.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<User>> call, Throwable t) {
                Log.e("ProfileActivity", "Error: \n" + t.toString());
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareRef.clear();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HistoryOrderActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onClick(View v) {
        Intent intent = new Intent(ProfileActivity.this, ModifyUserActivity.class);
        intent.putExtras(userBundle);
        startActivity(intent);
    }

    private Bundle setBundle(User user) {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", user.getId());
        bundle.putString("Name", user.getName());
        bundle.putString("Phone", user.getPhone());
        bundle.putString("Mail", user.getEmail());

        Bitmap bitmap = ((BitmapDrawable) profileImage.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        bundle.putByteArray("Avatar", stream.toByteArray());

        return bundle;
    }
}