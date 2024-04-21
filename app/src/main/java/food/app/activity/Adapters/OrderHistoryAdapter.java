package food.app.activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import food.app.activity.Activities.HistoryOrderActivity;
import food.app.activity.Models.OrderHistoryItem;
import food.app.activity.R;
import food.app.activity.ShareRef;

public class OrderHistoryAdapter extends ArrayAdapter<OrderHistoryItem> {
    TextView name, address, phone, email, totalPrice;
    public OrderHistoryAdapter(@NonNull Context context, List<OrderHistoryItem> orderHistoryItemList) {
        super(context, 0, orderHistoryItemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OrderHistoryItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_history_item, parent, false);
        }

        name = convertView.findViewById(R.id.customerName);
        address = convertView.findViewById(R.id.address);
        phone = convertView.findViewById(R.id.phone);
        email = convertView.findViewById(R.id.email);
        totalPrice = convertView.findViewById(R.id.price);

        if(item != null) {
            name.setText("Name: " + item.getCustomerName());
            address.setText("Address: " + item.getShippingAddress());
            phone.setText("Phone: " + item.getCustomerPhone());
            email.setText("Email: " + item.getEmail_receive());
            totalPrice.setText("Total price: " + item.getTotal_price() + "$");
        }

        return convertView;
    }
}
