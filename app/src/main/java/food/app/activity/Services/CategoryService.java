package food.app.activity.Services;

import food.app.activity.Models.CategoryResponse;
import food.app.activity.Models.FoodResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("api/admin/category")
    Call<CategoryResponse> getAllCategory();
}
