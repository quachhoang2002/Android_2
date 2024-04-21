package food.app.activity.Services;

import java.util.List;

import food.app.activity.Models.CartResponse;
import food.app.activity.Models.ResponseObject;
import food.app.activity.Request.OrderRequest;
import food.app.activity.Response.OrderDetailResponse;
import food.app.activity.Response.OrderHistoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService
{
    @GET("api/order/getByUserId/{userId}")
    Call<ResponseObject<List<OrderHistoryResponse>>> getOrderByUserId(@Path("userId") int userId);

    @GET("api/admin/order/{orderId}/detail")
    Call<ResponseObject<List<OrderDetailResponse>>> getCartByOrder(@Path("orderId") int orderId);
}
