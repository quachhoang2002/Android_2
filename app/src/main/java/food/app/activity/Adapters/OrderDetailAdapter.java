package food.app.activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import food.app.activity.Models.OrderDetailItem;
import food.app.activity.R;

public class OrderDetailAdapter extends ArrayAdapter<OrderDetailItem> {
    ImageView imageView;
    TextView name, totalPrice, quantity;
    public OrderDetailAdapter(@NonNull Context context, List<OrderDetailItem> orderDetailItemList) {
        super(context, 0, orderDetailItemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OrderDetailItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_detail_item, parent, false);
        }

        imageView = convertView.findViewById(R.id.orderDetailItemImage);
        name = convertView.findViewById(R.id.orderDetailItemName);
        totalPrice = convertView.findViewById(R.id.orderDetailItemPrice);
        quantity = convertView.findViewById(R.id.orderDetailItemquantityTextView);

        if(item != null) {
            Picasso.get().load(item.getImagePath()).into(imageView);
            name.setText(item.getProductName());
            totalPrice.setText(item.getTotalPrice() + "$");
            quantity.setText(item.getQuantity() + "");
        }

        return convertView;
    }
}
