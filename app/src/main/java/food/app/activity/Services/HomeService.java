package food.app.activity.Services;


import food.app.activity.Models.FoodResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeService {
    @GET("api/admin/product")
    Call<FoodResponse> getAllFood();
}
