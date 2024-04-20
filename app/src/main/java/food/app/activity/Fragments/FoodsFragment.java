package food.app.activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import food.app.activity.Adapters.FoodAdapter;
import food.app.activity.Models.FoodItemModel;
import food.app.activity.Models.FoodResponse;
import food.app.activity.R;
import food.app.activity.Services.CategoryService;
import food.app.activity.Services.HomeService;
import food.app.activity.Services.ServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsFragment extends Fragment {

    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<FoodItemModel> list;
    private String name;
    public FoodsFragment(String name) {
        this.name = name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foods, container, false);

        recyclerView = view.findViewById(R.id.food_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        HomeService homeService = ServiceBuilder.buildService(HomeService.class);
        Call<FoodResponse> call = homeService.getFoodCategory();
        System.out.println("API CONNECTING");
        if (name.isEmpty())
        {
            System.out.println("Vào đây ở FoodFragment name empty");

            call.enqueue(new Callback<FoodResponse>() {
                @Override
                public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                    FoodResponse foodResponse = response.body();
                    System.out.println("API CONNECTED");

                    if (response.isSuccessful()) {
                        if (foodResponse != null) {
                            List<FoodItemModel> products = foodResponse.getData();
                            System.out.println(products);

                            foodAdapter = new FoodAdapter(products, getActivity().getApplicationContext());
                            recyclerView.setAdapter(foodAdapter);
                            System.out.println(products.toString());
                            Toast.makeText(getActivity().getApplicationContext(), "Empty response", Toast.LENGTH_SHORT).show();

                        } else {
//                        Toast.makeText(getActivity().getApplicationContext(), "Empty response", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FoodResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            System.out.println("Vào đây ở FoodFragment");
            Call<FoodResponse> callWithName = homeService.getFoods(name, 1);
            callWithName.enqueue(new Callback<FoodResponse>() {

                @Override
                public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                    FoodResponse foodResponse = response.body();
                }

                @Override
                public void onFailure(Call<FoodResponse> call, Throwable t) {

                }
            });
        }

        return view;
    }
}