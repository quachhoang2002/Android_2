package food.app.activity.Activities;


import static food.app.activity.R.layout.activity_modifyuser;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import food.app.activity.Models.ResponseObject;
import food.app.activity.Models.User;
import food.app.activity.R;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.Services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyUserActivity extends AppCompatActivity {

    //Them Intent len day ne
    Intent intent;
    Bundle bundle;

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView profileImage;
    private EditText etName, etPhone, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyuser);

        intent = new Intent(ModifyUserActivity.this, ProfileActivity.class);
        bundle = getIntent().getExtras();

        profileImage = findViewById(R.id.profileImage);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etMail);

        getBundleData(bundle);
    }

    public void chooseImage(View view) {
    //Viet vo day ne
    }

    public void saveProfile(View view) {
    //Viet vo day nua ne
        User user = new User(bundle.getInt("Id"), etName.getText().toString(), etEmail.getText().toString(), etPhone.getText().toString());

        UserService userService = ServiceBuilder.buildService(UserService.class);
        Call<ResponseObject<User>> call = userService.editUser(user);

        call.enqueue(new Callback<ResponseObject<User>>() {
            @Override
            public void onResponse(Call<ResponseObject<User>> call, Response<ResponseObject<User>> response) {
                if(response.body() != null && response.body().getStatus().equals("success")){
                    Log.i("ModifyUserActivity", "Updated");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ModifyUserActivity.this,"Không thể update", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<User>> call, Throwable t) {
                Log.e("ModifyUserActivity", "Error: \n" + t.toString());
            }
        });
    }

    public void backEvent(View view) {
        startActivity(intent);
    }

    private void getBundleData(Bundle bundle) {
        etName.setText(bundle.getString("Name"));
        etEmail.setText(bundle.getString("Mail"));
        etPhone.setText(bundle.getString("Phone"));

        byte[] bytes = bundle.getByteArray("Avatar");
        profileImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0 , bytes.length));
    }
}
