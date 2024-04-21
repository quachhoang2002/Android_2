package food.app.activity.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.sql.SQLOutput;
import java.util.List;

import food.app.activity.Adapters.FoodAdapter;
import food.app.activity.Adapters.FoodItemAdapter;
import food.app.activity.Fragments.DrinkFragment;
import food.app.activity.Fragments.FoodsFragment;
import food.app.activity.Fragments.SnackFragment;
import food.app.activity.Models.CategoryModel;
import food.app.activity.Models.CategoryResponse;
import food.app.activity.Models.FoodItemModel;
import food.app.activity.Models.FoodResponse;
import food.app.activity.R;
import food.app.activity.Services.CategoryService;
import food.app.activity.Services.HomeService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView foodCart;

    ImageView user_profile;

    FoodItemAdapter foodItemAdapter;

    ShareRef shareRef;
    private EditText searchEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        if (!isConnected(this)) {
            showInternetDialog();
        }

        shareRef = new ShareRef(this);
        if (!shareRef.isLogin()){
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        shareRef.removeSearchName();
        Log.d("TOKEN", shareRef.getToken());



        tabLayout = findViewById(R.id.food_tab);
        viewPager = findViewById(R.id.food_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Foods"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("Drink"), 1);
        tabLayout.addTab(tabLayout.newTab().setText("Snack"), 2);
        searchEdit = findViewById(R.id.food_search);

        foodItemAdapter = new FoodItemAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(foodItemAdapter);

        // Set the OnKeyListener for the searchEdit to listen for the Enter key press
        searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Reload the current tab content
                    shareRef.setSearchName(searchEdit.getText().toString().trim());

                    int currentTabPosition = tabLayout.getSelectedTabPosition();
                    foodItemAdapter.reloadTab(currentTabPosition);
                    foodItemAdapter = new FoodItemAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
                    viewPager.setAdapter(foodItemAdapter);
                    viewPager.setCurrentItem(currentTabPosition);

                    return true;
                }
                return false;
            }
        });


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        foodCart = findViewById(R.id.food_cart);
        foodCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        user_profile = findViewById(R.id.menu_user);
        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setDataTablayout(List<CategoryModel> categoryModels) {
        for (int i = 0; i < categoryModels.size(); i++) {
            CategoryModel categoryModel = categoryModels.get(i);
            tabLayout.addTab(tabLayout.newTab().setText(categoryModel.getName()), i);
            System.out.println(categoryModel.getName());
        }
    }

    private void showInternetDialog() {

        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        View view = LayoutInflater.from(this).inflate(R.layout.no_internet_dialog, findViewById(R.id.no_internet_layout));
        view.findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnected(DashboardActivity.this)) {
                    showInternetDialog();
                } else {
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    finish();
                }
            }
        });

        dialog.setContentView(view);
        dialog.show();

    }

    private boolean isConnected(DashboardActivity dashboardActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) dashboardActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }

    private Fragment performSearch(int position) {
        String searchText = searchEdit.getText().toString().trim();
        return new FoodsFragment();
    }
}