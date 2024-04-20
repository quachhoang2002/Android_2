package food.app.activity.Services;


import food.app.activity.Models.FoodResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface HomeService {
    @GET("api/admin/product")
    Call<FoodResponse> getAllFood();
    @GET("api/admin/product?category=1")
    Call<FoodResponse> getFoodCategory();
    @GET("api/admin/product?category=2")
    Call<FoodResponse> getDrinkCategory();
    @GET("api/admin/product?category=3")
    Call<FoodResponse> getSnackCategory();
    @GET("api/admin/product")
    Call<FoodResponse> getFoods(@Query("name") String name,
                                @Query("category") int category);
}
