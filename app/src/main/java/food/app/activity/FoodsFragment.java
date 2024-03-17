package food.app.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import food.app.activity.Models.FoodModel;
import food.app.activity.Models.FoodResponse;
import food.app.activity.Services.HomeService;
import food.app.activity.Services.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsFragment extends Fragment {

    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<FoodItemModel> list;

    public FoodsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_foods, container, false);
//
//        list = new ArrayList<>();
//        list.add(new FoodItemModel(R.drawable.food_img_1, "Food name 1", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_2, "Food name 2", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_1, "Food name 3", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_2, "Food name 4", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_1, "Food name 5", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_2, "Food name 6", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_1, "Food name 7", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_2, "Food name 8", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_1, "Food name 9", "Rs: Food price"));
//        list.add(new FoodItemModel(R.drawable.food_img_2, "Food name 10", "Rs: Food price"));
//
//        recyclerView = view.findViewById(R.id.food_recycler);
//        foodAdapter = new FoodAdapter(list, getActivity().getApplicationContext());
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(foodAdapter);
//        return view;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foods, container, false);

        recyclerView = view.findViewById(R.id.food_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        HomeService productService = ServiceBuilder.buildService(HomeService.class);
        Call<FoodResponse> call = productService.getAllFood();
        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if (response.isSuccessful()) {
                    FoodResponse foodResponse = response.body();
                    if (foodResponse != null) {
                        List<FoodItemModel> products = foodResponse.getData();
                        System.out.println(products.toString());
                        foodAdapter = new FoodAdapter(products, getActivity().getApplicationContext());
                        recyclerView.setAdapter(foodAdapter);
                    } else {
                        System.out.println("Here: " + getActivity().getApplicationContext());
                        Toast.makeText(getActivity().getApplicationContext(), "Empty response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    System.out.println("Here: " + getActivity().getApplicationContext());
                    Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}