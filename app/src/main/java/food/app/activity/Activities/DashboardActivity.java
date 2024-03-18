package food.app.activity.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
import food.app.activity.Services.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
                    FoodItemAdapter foodItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (!isConnected(this)) {
            showInternetDialog();
        }

        tabLayout = findViewById(R.id.food_tab);
        viewPager = findViewById(R.id.food_viewpager);
        CategoryService categoryService = ServiceBuilder.buildService(CategoryService.class);
        Call<CategoryResponse> call = categoryService.getAllCategory();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse categoryResponse = response.body();
                    if (categoryResponse != null) {
                        List<CategoryModel> categoryModels = categoryResponse.getData();
                        setDataTablayout(categoryModels);
                    } else {
                        System.out.println("Cate: null" );
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

//        tabLayout.addTab(tabLayout.newTab().setText("Foods"), 0);
//        tabLayout.addTab(tabLayout.newTab().setText("Drink"), 1);
//        tabLayout.addTab(tabLayout.newTab().setText("zxc"), 2);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final FoodItemAdapter adapter = new FoodItemAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

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


    }
    private void setDataTablayout(List<CategoryModel> categoryModels){
        for (int i = 0; i < categoryModels.size(); i++) {
            CategoryModel categoryModel = categoryModels.get(i);
            tabLayout.addTab(tabLayout.newTab().setText(categoryModel.getName()), i);
            System.out.println(i);
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

}