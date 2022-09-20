package io.agora.openlive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Schedule extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Task> taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

       taskList = new ArrayList<>();
        fetchTasks();
    }
    private void fetchTasks() {

        String url = "https://vericlean-admin.herokuapp.com/api/tasks";



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String _id = jsonObject.getString("_id");
                                String cleaners_assigned = jsonObject.getString("cleaners_assigned");
                                String cleaning_tasks = jsonObject.getString("cleaning_tasks");
                                String task_head = jsonObject.getString("task_head");
                                String room = jsonObject.getString("room");
                                String floor = jsonObject.getString("floor");
                                String start_time = jsonObject.getString("start_time");
                                String end_time = jsonObject.getString("end_time");
                                String createdAt = jsonObject.getString("createdAt");


                                Task task = new Task(_id, cleaners_assigned, cleaning_tasks, task_head,room,floor,start_time,end_time,createdAt);
                                taskList.add(task);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            TaskAdapter adapter = new TaskAdapter(Schedule.this, taskList);

                            recyclerView.setAdapter(adapter);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Schedule.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String authValue = "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzE4NTM3Y2NmZTUzNDUyZDJjMDU5MjYiLCJyb2xlIjoiY2xlYW5lciIsImlhdCI6MTY2MzQxMjAzMiwiZXhwIjoxNjY2MDA0MDMyfQ.onf6bfnG2K1EiqOCk7CQ7VDCd6BwSz-u4-u-4o_5HcE";
                headers.put("Authorization", authValue);
                headers.put("Accept", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }

        };
        requestQueue.add(jsonArrayRequest);
    }



}


