package food.app.activity.Services;

import java.util.Map;

import food.app.activity.Models.CartModel;
import food.app.activity.Models.CartResponse;
import food.app.activity.Models.DeleteCartModel;
import food.app.activity.Response.DeleteCartResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CartService {
    @POST("api/cart/add")
    Call<CartModel> addToCart(@Body CartModel cart);

    @GET("api/cart/getByUserId/{userId}")
    Call<CartResponse> getCartByUserId(@Path("userId") int userId);

    @HTTP(method = "DELETE", path = "api/cart/deleteItem", hasBody = true)
    Call<DeleteCartResponse> deleteCartItem(@Body Map<String, Object> request);
    @PUT("api/cart/updateQuantity")
    Call<DeleteCartResponse> updateCartItem(@Body Map<String, Object> request);

}
