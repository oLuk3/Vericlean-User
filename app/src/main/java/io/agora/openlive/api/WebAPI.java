package io.agora.openlive.api;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.agora.openlive.Model;
import io.agora.openlive.Schedule;
import io.agora.openlive.User;

public class WebAPI implements API {

    public static final String BASE_URL = "https://vericlean-admin.herokuapp.com/";

    private final Application mApplication;

    private RequestQueue mRequestQueue;
    private Model mModel;


    public WebAPI(Application application) {
        mApplication = application;
        mRequestQueue = Volley.newRequestQueue(application);

    }

    public void login(String email, String password, final APIListener listener) {

        String url = BASE_URL + "api/cleaners/login";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email );
            jsonObject.put("password", password );


            Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        User user = User.getUser(response);
                        listener.onLogin(user);
                    } catch (JSONException e) {
                        Toast.makeText(mApplication, "JSON exception", Toast.LENGTH_LONG).show();
                    }

                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Toast.makeText(mApplication, "Invalid Credentials", Toast.LENGTH_LONG).show();
                }
            };
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
            mRequestQueue.add(request);
        } catch (JSONException e) {
            Toast.makeText(mApplication, "JSON exception", Toast.LENGTH_LONG).show();
        }


    }


}
