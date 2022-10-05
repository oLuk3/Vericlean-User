package io.agora.openlive;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import io.agora.openlive.activities.MainActivity;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    TextView timeinTV;
    TextView timeoutTV;
    TextView displayTimeInS;
    TextView displayTimeOutS;
    TextView idTask;
    private RequestQueue requestQueue;
    TextView getTimeinScanQr;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //scan
        timeinTV = (TextView) findViewById(R.id.timeInTVD);
        timeoutTV = (TextView) findViewById(R.id.timeOutTVD);


        ///TextView _idD_tv = findViewById(R.id._idD);
        TextView cleaning_tasksD_tv = findViewById(R.id.cleaning_tasksD);
        TextView roomD_tv = findViewById(R.id.roomD);
        TextView floorD_tv = findViewById(R.id.floorD);
        TextView start_timeD_tv = findViewById(R.id.start_timeD);
        TextView end_timeD_tv = findViewById(R.id.end_timeD);


        Bundle bundle = getIntent().getExtras();
        String m_id = bundle.getString("_id");
        String mroom = bundle.getString("room");
        String mfloor = bundle.getString("floor");
        String mcleaning_tasks = bundle.getString("cleaning_tasks");
        String mstart_time = bundle.getString("start_time");
        String mend_time = bundle.getString("end_time");


        //_idD_tv.setText((m_id));
        cleaning_tasksD_tv.setText((mcleaning_tasks));
        roomD_tv.setText(mroom);
        floorD_tv.setText(mfloor);
        start_timeD_tv.setText(mstart_time);
        end_timeD_tv.setText(mend_time);


        //get time

        displayTimeOutS = findViewById(R.id.timeOutTVD);
        //time out values
        String TimeNow =
                Instant.now()
                        .atZone(ZoneId.of("Asia/Manila"))
                        .format(
                                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                                        .withLocale(Locale.US).ofPattern("h:mm a"));
        //scan
        Button scanTimeOut = findViewById(R.id.scanTimeOut);
        scanTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                displayTimeOutS.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        displayTimeOutS.setText(TimeNow);
//                    }
//                }, 3000);

                String currentTimeinUTC = Instant.now().toString();
                String url = "https://vericlean-admin.herokuapp.com/api/qr/"+m_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(DetailActivity.this, response.trim(), Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("scan_time_out", currentTimeinUTC);
                        return params;
                    }

                    public Map<String, String> getHeaders () throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        String authValue = "Bearer " + User.getToken();
                        headers.put("Authorization", authValue);
                        //headers.put("Accept", "application/json; charset=UTF-8");
                       // headers.put("Content-Type", "application/json; charset=UTF-8");
                        return headers;
                    }





                };

                RequestQueue requestQueue = Volley.newRequestQueue(DetailActivity.this);
                requestQueue.add(stringRequest);

                //QR code scanner method
                scanTimeOut();

            }

        });



        //utcTime is current time
// String TimeInS =  Instant.now().toString();
        displayTimeInS = findViewById(R.id.timeInTVD);



        String TimeInSched =
                Instant.now ()
                        .atZone( ZoneId.of ( "Asia/Manila" ) )
                        .format(
                                DateTimeFormatter.ofLocalizedDateTime ( FormatStyle.FULL )
                                        .withLocale ( Locale.US ).ofPattern("h:mm a"));


       // this is UTC Time zone
       // String currentTime =  Instant.now().toString();
       //getTimeinScanQr=findViewById(R.id.getTimeinScanQr);

        //this is ID
        //idTask = findViewById(R.id._idD);
        //idTask.setText(m_id);

        Button scanTimeIn = findViewById(R.id.scanTimeIn);
        scanTimeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentTimeinUTC = Instant.now().toString();
                String url = "https://vericlean-admin.herokuapp.com/api/qr/"+m_id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(DetailActivity.this, response.trim(), Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("scan_time_in", currentTimeinUTC);
                        return params;
                    }
                    public Map<String, String> getHeaders () throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        String authValue = "Bearer " + User.getToken();
                        headers.put("Authorization", authValue);
                        return headers;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(DetailActivity.this);
                requestQueue.add(stringRequest);

                scanTimein();
            }
        });

        //live
        Button live = findViewById(R.id.liveBtn2);
        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        RequestQueue queue = Volley.newRequestQueue(DetailActivity.this);

        //GET TIMEIN/TIMEOUT FROM QR ENDPOINT
         String url = "https://vericlean-admin.herokuapp.com/api/qr/"+m_id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String timein = response.getString("scan_time_in");
                            String timeout = response.getString("scan_time_out");

                            String convertTimeIn =
                                    Instant.parse (timein)
                                            .atZone( ZoneId.of ( "Asia/Manila" ) )
                                            .format(
                                                    DateTimeFormatter.ofLocalizedDateTime ( FormatStyle.FULL )
                                                            .withLocale ( Locale.US ).ofPattern("h:mm a"));
                            String convertTimeOut =
                                    Instant.parse (timeout)
                                            .atZone( ZoneId.of ( "Asia/Manila" ) )
                                            .format(
                                                    DateTimeFormatter.ofLocalizedDateTime ( FormatStyle.FULL )
                                                            .withLocale ( Locale.US ).ofPattern("h:mm a"));


                            timeinTV.setText(convertTimeIn);
                            timeoutTV.setText(convertTimeOut);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        }){

            public Map<String, String> getHeaders () throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();

                String authValue = "Bearer " + User.getToken();
                headers.put("Authorization", authValue);
                headers.put("Accept", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };

        queue.add(request);
    }


    private void scanTimeOut() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning QR Code");
        integrator.initiateScan();
    }


    private void scanTimein() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning QR Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanned Complete");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanTimein();
                    }
                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }else {
                Toast.makeText(this, "No Results",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onClick(View v) {

    }
}