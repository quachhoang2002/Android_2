package food.app.activity.Services;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.airbnb.lottie.L;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import food.app.activity.Response.FacebookInformation;
import food.app.activity.ShareRef;

public class Facebook {

//    public static FacebookInformation getFacebookInformation(AccessToken token,Context context) {
////        FacebookInformation facebookInformation = getFacebookInformationFromCtx(context);
////        if (facebookInformation != null) {
////            return facebookInformation;
////        }
//
//        GraphRequest request = GraphRequest.newMeRequest(
//                token,
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        try {
//                            String email = object.has("email") ? object.getString("email") : "";
//                            // You cannot directly access the phone number, but you can get other public profile details
//                            String name = object.has("name") ? object.getString("name") : "";
//
//                            Log.d("Facebook", "onCompleted: " + email + " " + name);
//                            Log.d("Facebook", "onCompleted: " + object.toString());
//                            ShareRef shareRef = new ShareRef(context);
//                            shareRef.saveFacebookUserInformation(email, name, token.getToken());
//                            // Handle the values here
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "email,name");
//        request.setParameters(parameters);
//        request.executeAsync();
//
//        return getFacebookInformationFromCtx(context);
//    }

    public static FacebookInformation getFacebookInformation(Context context) {
        ShareRef shareRef = new ShareRef(context);
        String email = shareRef.getEmail();
        String phone = shareRef.getPhone();
        String token = shareRef.getToken();

        return new FacebookInformation(email, phone, token);
    }
}
