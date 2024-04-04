package food.app.activity.Services;

import com.google.gson.Gson;

import java.util.List;

import food.app.activity.Models.CartModel;
import food.app.activity.Models.CartResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartService {
    @POST("api/cart/add")
    Call<CartModel> addToCart(@Body CartModel cart);

    @GET("api/cart/getByUserId/{userId}")
    Call<CartResponse> getCartByUserId(@Path("userId") int userId);

}
