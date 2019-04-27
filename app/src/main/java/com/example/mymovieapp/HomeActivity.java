package com.example.mymovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private TextView name , email ;
    SessionManger sessionManger;
    RecyclerView recyclerView ;
    LinearLayout linearLayout;
    ArrayList<Movie> LM=new ArrayList<>();
    recycledapter recycledapter;
    RecyclerView.LayoutManager layoutManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManger = new SessionManger(this);
        sessionManger.checkLogin();
        recyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        linearLayout=(LinearLayout)findViewById(R.id.move_item);

        getjson();
        recycledapter =new recycledapter(LM,this);
        recyclerView.setAdapter(recycledapter);
        layoutManager =new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);



      /*  name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        HashMap<String, String> user = sessionManger.getUserDetail();
        String mName = user.get(sessionManger.NAME);
        String mEmail = user.get(sessionManger.EMAIL);
        name.setText(mName);
        email.setText(mEmail);*/
//        Intent intent  = getIntent();
//        String extraName = intent.getStringExtra("name");
//        String extraEmail = intent.getStringExtra("email");


//           name.setText(extraName);
//           email.setText(extraEmail);


    }
    public void getjson() {
        //   Toast.makeText(getActivity(), "getjson", Toast.LENGTH_SHORT).show();

        final JsonObjectRequest requst = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/popular?api_key=777660159186d81259c9dcfa910ad0f1&page=", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray arr= response.getJSONArray("results");
                            for (int i=0;i<arr.length();i++) {
                                JSONObject move = arr.getJSONObject(i);
                                Movie m = new Movie();
                                m.setTitle(move.getString("title"));

                                m.setDescription(move.getString("overview"));
                                m.setImgurl(move.getString("poster_path"));
                                LM.add(m);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //          Toast.makeText(getActivity(), response + "", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //     Toast.makeText(getActivity(), error.getMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });
        //   Toast.makeText(getActivity(), "after request", Toast.LENGTH_SHORT).show();


        volly.getinsance(getApplicationContext()).add_request(requst);
    }
}
