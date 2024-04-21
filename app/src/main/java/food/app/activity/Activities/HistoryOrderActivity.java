package food.app.activity.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import food.app.activity.Adapters.OrderHistoryAdapter;
import food.app.activity.Models.OrderHistoryItem;
import food.app.activity.Models.ResponseObject;
import food.app.activity.R;
import food.app.activity.Response.OrderHistoryResponse;
import food.app.activity.Services.OrderService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOrderActivity extends AppCompatActivity {
    ListView listView;
    public OrderHistoryAdapter orderHistoryAdapter;
    public List<OrderHistoryItem> orderHistoryItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        listView = findViewById(R.id.orderListView);

        // Sample data
        orderHistoryItems = new ArrayList<>();
        loadData();
        ImageButton backBtn = findViewById(R.id.back_btn);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int orderId = orderHistoryItems.get(i).getId();
                Intent intent = new Intent(HistoryOrderActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryOrderActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void loadData(){
        OrderService orderService = ServiceBuilder.buildService(OrderService.class);
        ShareRef shareRef = new ShareRef(this);
        int userId = shareRef.getUserID();

        Call<ResponseObject<List<OrderHistoryResponse>>> call = orderService.getOrderByUserId(userId);
        call.enqueue(new Callback<ResponseObject<List<OrderHistoryResponse>>>() {
            @Override
            public void onResponse(Call<ResponseObject<List<OrderHistoryResponse>>> call, Response<ResponseObject<List<OrderHistoryResponse>>> response) {
                if (response.isSuccessful()) {
                    List<OrderHistoryResponse> list = response.body().getData();
                    list.stream().forEach(item -> {
                        orderHistoryItems.add(new OrderHistoryItem(
                                item.getId(),
                                item.getCustomerName(),
                                item.getShippingAddress(),
                                item.getCustomerPhone(),
                                item.getEmail_receive(),
                                item.getTotal_price(),
                                item.getStatus()
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

                orderHistoryAdapter = new OrderHistoryAdapter(HistoryOrderActivity.this, orderHistoryItems);

                listView.setAdapter(orderHistoryAdapter);
            }

            @Override
            public void onFailure(Call<ResponseObject<List<OrderHistoryResponse>>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}