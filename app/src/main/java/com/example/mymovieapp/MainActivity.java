package com.example.mymovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText name , email , password , c_password ;
    private Button btn_regist;
    private ProgressBar loading;
 //   private static String URL_RREGIST = "http://192.168.43.94/android/register.php";
  //  private static String URL_RREGIST = "http://192.168.1.6/android/register.php";
      private static String URL_RREGIST = "http://192.168.137.1/android/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        btn_regist = findViewById(R.id.btn_regist);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rergist();
            }
        });
    }
    private void Rergist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);
        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL_RREGIST,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                        String success = jsonObject.getString("success");
                                        if(success.equals("1")){
                                          //  Toast.makeText(MainActivity.this ,  "Register Seccess" , Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                                            intent.putExtra("name" , name) ;
                                            intent.putExtra("email" , email);
                                            startActivity(intent);
                                            loading.setVisibility(View.GONE);
                                        }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this ,  "Register Error!" + e.toString() , Toast.LENGTH_LONG).show();
                                    loading.setVisibility(View.GONE);
                                    btn_regist.setVisibility(View.VISIBLE);

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this ,  "Register Error!" + error.toString() , Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);
                            }
                        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name" , name);
                params.put("email" , email);
                params.put("password" , password);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
