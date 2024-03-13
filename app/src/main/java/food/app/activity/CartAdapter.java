package food.app.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends ArrayAdapter<CartItem> {

    public CartAdapter(Context context, List<CartItem> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CartItem product = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }

        // Lookup view for data population
        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);
        NumberPicker productQuantity = convertView.findViewById(R.id.productQuantity);

        // Populate the data into the template view using the data object
        if (product != null) {
            productImage.setImageResource(product.getImageResId());
            productName.setText(product.getProductName());
            productPrice.setText(String.format("$%.2f", product.getPrice()));

            // Set value for NumberPicker before setting the listener
            productQuantity.setValue(product.getQuantity());

            productQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    // Khi giá trị của NumberPicker thay đổi, cập nhật giá trị trong danh sách sản phẩm
                    if (product != null) {
                        product.setQuantity(newValue);
                    }
                }
            });
        }


        // Return the completed view to render on screen
        return convertView;
    }
}

