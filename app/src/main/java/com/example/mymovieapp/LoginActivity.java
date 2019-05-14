package com.example.mymovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText  email , password  ;
    private Button btn_login;
    private TextView link_regist;
    private ProgressBar loading;
 //   private static String URL_LOGIN = "http://192.168.43.94/android/login.php";
   //private static String URL_LOGIN = "http://192.168.1.7/android/login.php";
    private static String URL_LOGIN = "http://192.168.137.1/android/login.php";

    SessionManger sessionManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManger = new SessionManger(this);

        //sessionManger.checkLogin();
        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        link_regist = findViewById(R.id.link_regist);
        String mEmail = email.getText().toString().trim();
        String mpass = password.getText().toString().trim();
       // login(mEmail ,mpass );
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mpass = password.getText().toString().trim();

                if(!mEmail.isEmpty() || !mpass.isEmpty()){
                    login(mEmail ,mpass );

                }
                else{
                    email.setError("please enter your email");
                    password.setError("please enter your password");
                }
            }
        });
        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , MainActivity.class));

            }
        });


    }
    private void login(final String email ,final String password){


        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if(success.equals("1")){
                                for (int i = 0 ; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();

                                    sessionManger.createSession(name , email);
//                                    Toast.makeText(LoginActivity.this ,  "Register login. \n your name is " + name
//                                            +"your email " + email, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                                    intent.putExtra("name" , name) ;
                                    intent.putExtra("email" , email);
                                    startActivity(intent);
                                    finish();
                                    loading.setVisibility(View.GONE);
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this ,  "login Error!" + e.toString() , Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                           btn_login.setVisibility(View.VISIBLE);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this ,  "login Error!" + error.toString() , Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email" , email);
                params.put("password" , password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
