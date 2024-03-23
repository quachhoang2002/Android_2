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

public class DrinkFragment extends Fragment {
    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<FoodItemModel> list;

    public DrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);

        recyclerView = view.findViewById(R.id.drink_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        HomeService homeService = ServiceBuilder.buildService(HomeService.class);
        Call<FoodResponse> call = homeService.getDrinkCategory();
        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if (response.isSuccessful()) {
                    FoodResponse foodResponse = response.body();
                    if (foodResponse != null) {
                        List<FoodItemModel> products = foodResponse.getData();
                        foodAdapter = new FoodAdapter(products, getActivity().getApplicationContext());
                        recyclerView.setAdapter(foodAdapter);
                    } else {
//                        Toast.makeText(getActivity().getApplicationContext(), "Empty response", Toast.LENGTH_SHORT).show();
                    }
                } else {
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
