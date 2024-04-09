package food.app.activity;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareRef {
    private static final String PREFERENCES_FILE = "app_preferences";
    private static final String TOKEN_KEY = "token";
    private SharedPreferences sharedPreferences;

    public ShareRef(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null); // Returns null if token doesn't exist
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public void saveUserInformation(Integer userID,String email, String phone) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("userID", userID.toString());
        editor.apply();
    }

    public void saveFacebookUserInformation(String email, String phone,String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("token", token);
        //is facebook login
        editor.putBoolean("isFacebook", true);
        editor.apply();
    }

    public String getFacebookToken() {
        return sharedPreferences.getString("token", null);
    }

    public String getEmail() {
        return sharedPreferences.getString("email", null);
    }

    public String getPhone() {
        return sharedPreferences.getString("phone", null);
    }

    public Integer getUserID() {
        return Integer.parseInt(sharedPreferences.getString("userID", null));
    }

    //clear
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
