package food.app.activity;

import android.content.Context;
import android.content.SharedPreferences;

public class Token {
    private static final String PREFERENCES_FILE = "app_preferences";
    private static final String TOKEN_KEY = "token";
    private SharedPreferences sharedPreferences;

    public Token(Context context) {
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
}
