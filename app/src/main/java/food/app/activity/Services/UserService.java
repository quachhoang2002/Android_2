package food.app.activity.Services;

import food.app.activity.Models.ResponseObject;
import food.app.activity.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("api/user/getInfo/{id}")
    Call<ResponseObject<User>> getUserProfile(@Path("id") int id);

    @PUT("api/auth/editUser")
    Call<ResponseObject<User>> editUser(@Body User user);
}
