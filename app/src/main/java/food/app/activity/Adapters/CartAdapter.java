package food.app.activity.Adapters;

import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import food.app.activity.Activities.CartActivity;
import food.app.activity.Activities.NoOrderActivity;
import food.app.activity.Models.CartItemModel;
import food.app.activity.Models.DeleteCartModel;
import food.app.activity.R;
import food.app.activity.Response.DeleteCartResponse;
import food.app.activity.Services.CartService;
import food.app.activity.Services.ServiceBuilder;
import food.app.activity.ShareRef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends ArrayAdapter<CartItemModel> {
    private ShareRef shareRef;
    private CartActivity cartActivity = new CartActivity();
    public CartAdapter(Context context, List<CartItemModel> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartItemModel product = getItem(position);
        System.out.println(product.getId());
        shareRef = new ShareRef(getContext());
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }
        CartService cartService = ServiceBuilder.buildService(CartService.class);

        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);
        TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);

        int productId = product.getId();
        int userId = 1;

        if (product != null) {
            Picasso.get().load(product.getImageResId()).into(productImage);
            productName.setText(product.getProductName());
            productPrice.setText(String.format("$%.2f", product.getPrice()));
            quantityTextView.setText(String.valueOf(product.getQuantity()));

        }
        Button decreaseQuantityButton = convertView.findViewById(R.id.decreaseQuantityButton);
        Button increaseQuantityButton = convertView.findViewById(R.id.increaseQuantityButton);
        ImageButton deleteProductButton = convertView.findViewById(R.id.deleteProductButton);

        decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
                if (currentQuantity > 1) {
                    currentQuantity--;
                    quantityTextView.setText(String.valueOf(currentQuantity));
                    Map<String, Object> requestBody = new HashMap<>();
                    requestBody.put("id", productId);
                    requestBody.put("quantity", currentQuantity);
                    requestBody.put("user_id", userId);
                    System.out.println(requestBody);
                    Call<DeleteCartResponse> call = cartService.updateCartItem(requestBody);
                    call.enqueue(new Callback<DeleteCartResponse>() {
                        @Override
                        public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                            if(response.code() == 200){
                                DeleteCartResponse deleteCartResponse = response.body();
                                if (deleteCartResponse != null) {
                                    String message = deleteCartResponse.getMessage();
                                    if (message != null) {
                                        System.out.println("Message: " + message);
                                        // Handle message appropriately
                                    } else {
                                        System.out.println("Response message is null");
                                        // Handle null message
                                    }
                                } else {
                                    System.out.println("Response body is null");
                                    // Handle null response body
                                }
                            } else {
                                System.out.println("Response is not successful");
                                // Handle unsuccessful response
                            }
                        }

                        @Override
                        public void onFailure(Call<DeleteCartResponse> call, Throwable t) {
                            System.out.println("Error " + t.getMessage());
                        }
                    });
                }else {
                    Toast.makeText(getContext(), "Không thể giảm nữa!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
                currentQuantity++;
                quantityTextView.setText(String.valueOf(currentQuantity));
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("id", productId);
                requestBody.put("quantity", currentQuantity);
                requestBody.put("user_id", userId);
                System.out.println(requestBody);
                Call<DeleteCartResponse> call = cartService.updateCartItem(requestBody);
                call.enqueue(new Callback<DeleteCartResponse>() {
                    @Override
                    public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                        if (response.code() == 200) {
                            DeleteCartResponse deleteCartResponse = response.body();
                            if (deleteCartResponse != null) {
                                String message = deleteCartResponse.getMessage();
                                if (message != null) {
                                    System.out.println("Message: " + message);
                                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                                } else {
                                    System.out.println("Response message is null");
                                    // Handle null message
                                }
                            } else {
                                System.out.println("Response body is null");
                                // Handle null response body
                            }
                        } else {
                            System.out.println("Response is not successful");
                            // Handle unsuccessful response
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteCartResponse> call, Throwable t) {
                        System.out.println("Error " + t.getMessage());
                    }
                });

            }
        });

        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?")
                        .setTitle("Xác nhận xóa");

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        DeleteCartModel request = new DeleteCartModel(productId, userId);
                        Map<String, Object> requestBody = new HashMap<>();
                        requestBody.put("productId", productId);
                        requestBody.put("user_id", userId);
                        Call<DeleteCartResponse> call = cartService.deleteCartItem(requestBody);
                        call.enqueue(new Callback<DeleteCartResponse>() {
                            @Override
                            public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                                System.out.println(response.code());
                                System.out.println("con di me m");
                                if (response.code() == 200) {
                                    DeleteCartResponse deleteCartResponse = response.body();
                                    assert deleteCartResponse != null;
                                    String message = deleteCartResponse.getMessage();
                                    System.out.println("Message: " + message);
                                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                                    cartActivity.productAdapter.notifyDataSetChanged();

                                    if (cartActivity.productAdapter != null) {
                                        cartActivity.productAdapter.notifyDataSetChanged();
                                    } else {
                                        Intent intent = new Intent(getContext(), NoOrderActivity.class); // Sử dụng getContext() thay vì CartActivity.this
                                        getContext().startActivity(intent);
                                    }
                                } else {
                                    System.out.println(response.body());
                                    Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DeleteCartResponse> call, Throwable t) {
                                System.out.println(t.getMessage());
                            }
                        });
                    }
                });

                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return convertView;
    }

    public interface CartItemUpdateListener {
        void onItemQuantityChanged(int productId, int newQuantity);
    }
}

