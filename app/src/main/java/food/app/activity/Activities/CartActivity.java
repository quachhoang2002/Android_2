package food.app.activity.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import food.app.activity.Adapters.CartAdapter;
import food.app.activity.Models.CartItemModel;
import food.app.activity.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Sample data
        List<CartItemModel> productList = new ArrayList<>();
        productList.add(new CartItemModel(R.mipmap.ic_launcher, "Product 1", 10.99, 2));
        productList.add(new CartItemModel(R.mipmap.ic_launcher, "Product 2", 24.99, 1));
        productList.add(new CartItemModel(R.mipmap.ic_launcher, "Product 3", 5.99, 3));

        // Set up the adapter
        CartAdapter productAdapter = new CartAdapter(this, productList);

        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.cartListView);
        listView.setAdapter(productAdapter);
    }
}