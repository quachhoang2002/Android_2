package food.app.activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import food.app.activity.Models.CartItemModel;
import food.app.activity.R;

public class CartAdapter extends ArrayAdapter<CartItemModel> {

    public CartAdapter(Context context, List<CartItemModel> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CartItemModel product = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }

        // Lookup view for data population
        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);
        NumberPicker productQuantity = convertView.findViewById(R.id.quantityNumberPicker);

        // Populate the data into the template view using the data object
        if (product != null) {
            // Sử dụng Picasso để tải hình ảnh từ URL và hiển thị nó trong ImageView
            Picasso.get().load(product.getImageResId()).into(productImage);
            productName.setText(product.getProductName());
            productPrice.setText(String.format("$%.2f", product.getPrice()));



//            productQuantity.setDisplayedValues(displayValues);

            // Set value for NumberPicker before setting the listener
//            productQuantity.setValue(product.getQuantity());
//            productQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//                @Override
//                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
//                    // Khi giá trị của NumberPicker thay đổi, cập nhật giá trị trong danh sách sản phẩm
//                    if (product != null) {
//                        product.setQuantity(newValue);
//                    }
//                }
//            });
        }

        // Return the completed view to render on screen
        return convertView;
    }

}

