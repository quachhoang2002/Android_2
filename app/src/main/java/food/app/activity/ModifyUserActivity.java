package food.app.activity;


import static food.app.activity.R.layout.activity_modifyuser;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyUserActivity extends AppCompatActivity {

    //Them Intent len day ne

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView profileImage;
    private EditText etName, etPhone, etEmail, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyuser);

        profileImage = findViewById(R.id.profileImage);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etMail);
        etAddress = findViewById(R.id.etAddress);
    }

    public void chooseImage(View view) {
    //Viet vo day ne
    }

    public void saveProfile(View view) {
    //Viet vo day nua ne
    }
}
