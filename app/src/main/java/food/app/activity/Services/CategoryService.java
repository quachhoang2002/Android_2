package food.app.activity.Services;

import food.app.activity.Models.CategoryResponse;
import food.app.activity.Models.FoodResponse;
import food.app.activity.Models.ResponseObject;
import food.app.activity.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface CategoryService {
<<<<<<< HEAD
=======
    @GET("api/admin/category")
    Call<CategoryResponse> getAllCategory();
>>>>>>> 23daf47f5e631103ce9f31ce84224979bcc53fde
}
