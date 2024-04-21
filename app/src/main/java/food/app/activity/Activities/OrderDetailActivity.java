package food.app.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import food.app.activity.Adapters.CartAdapter;
import food.app.activity.Adapters.OrderDetailAdapter;
import food.app.activity.Adapters.OrderHistoryAdapter;
import food.app.activity.Models.CartItem;
import food.app.activity.Models.CartItemModel;
import food.app.activity.Models.CartResponse;
import food.app.activity.Models.FoodItemModel;
import food.app.activity.Models.OrderDetailItem;
import food.app.activity.Models.ResponseObject;
import food.app.activity.R;
import food.app.activity.Response.OrderDetailResponse;
import food.app.activity.Response.OrderHistoryResponse;
import food.app.activity.Services.CartService;
import food.app.activity.Services.OrderService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {
    ListView listView;

    List<OrderDetailItem> orderDetailItemList;
    OrderDetailAdapter orderDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product);

        listView = findViewById(R.id.orderDetailView);
        orderDetailItemList = new ArrayList<>();

        int orderId = getIntent().getIntExtra("orderId", 1);

        loadData(orderId);
        ImageButton backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailActivity.this, HistoryOrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void loadData(int orderId){
        OrderService orderService = ServiceBuilder.buildService(OrderService.class);
        Call<ResponseObject<List<OrderDetailResponse>>> call = orderService.getCartByOrder(orderId);

        call.enqueue(new Callback<ResponseObject<List<OrderDetailResponse>>>() {
            @Override
            public void onResponse(Call<ResponseObject<List<OrderDetailResponse>>> call, Response<ResponseObject<List<OrderDetailResponse>>> response) {
                if(response.isSuccessful()) {
                    List<OrderDetailResponse> list = response.body().getData();
                    list.stream().forEach(item -> {
                        orderDetailItemList.add(new OrderDetailItem(
                                item.getId(),
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getPrice(),
                                item.getProduct().getImagePath(),
                                item.getQuantity(),
                                item.getPrice()
                        ));
                    });
                }
                else {
                    try {
                        String errorMessage = response.errorBody().string();
                        System.out.println("Error: " + errorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                orderDetailAdapter = new OrderDetailAdapter(OrderDetailActivity.this, orderDetailItemList);
                listView.setAdapter(orderDetailAdapter);
            }

            @Override
            public void onFailure(Call<ResponseObject<List<OrderDetailResponse>>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}