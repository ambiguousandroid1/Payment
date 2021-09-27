package com.example.rerofit_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
Button btn ;
TextView textView;
List<Example> examples;
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        examples=new ArrayList<>();
        textView = findViewById(R.id.textView);
        btn=findViewById(R.id.button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         apiService = retrofit.create(ApiService.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Getdata();
//                sendata();
                //Updatedata();
//                Update_patch();
                deleleUser();
            }
        });
    }

    private void deleleUser() {
        Call<Void> call = apiService.DeleteUser(23);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(),"Response code :"+response.code(),Toast.LENGTH_LONG).show();
                textView.setText("\nResponse Code"+response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void Update_patch() {
        Map<String,String> map = new HashMap<>();
        map.put("userId","1234");

        map.put("body","Driver");

//        Call<Example> call= apiService.PatchUser(101,map);//Send Data in encode Url by Hashmap
        Call<Example> call= apiService.PatchUser(101,map);//Send Data in encode Url by Hashmap
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example1 = response.body();
                textView.setText("\nUser Id:"+example1.getUserId()+"\nTitle: "+example1.getTitle()+"\nBody :"+example1.getBody()+"\nResponse Code"+response.code());
//                textView.setText("\nResponse Code"+response.code());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Updatedata() {
        Map<String,String> map = new HashMap<>();
        map.put("userId","1234");
        map.put("title","Rai Singh Rawat");
        map.put("body","Businessmen");

//        Call<Example> call= apiService.PatchUser(101,map);//Send Data in encode Url by Hashmap
       Call<Example> call =apiService.PUtUser(22,map);
       call.enqueue(new Callback<Example>() {
           @Override
           public void onResponse(Call<Example> call, Response<Example> response) {

               Example example1 = response.body();
               textView.setText("\nUser Id:"+example1.getUserId()+"\nTitle: "+example1.getTitle()+"\nBody :"+example1.getBody()+"\nResponse Code"+response.code());
//
           }

           @Override
           public void onFailure(Call<Example> call, Throwable t) {

               Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

           }
       });


    }

    private void sendata() {
//        Example example = new Example(111,"MANISH","TIWARI");//Send data in Body by Model class
//        Call<Example> call= apiService.PostUser(123,"Tiwar11i","Manish");//Send Data in encode Url by Field
        Map<String,String> map = new HashMap<>();
        map.put("userId","1234");
        map.put("title","Rai Singh Rawat");
        map.put("body","Businessmen");

        Call<Example> call= apiService.PostUser(map);//Send Data in encode Url by Hashmap

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example1 = response.body();
                textView.setText("Id :"+example1.getId()+"\nUser Id:"+example1.getUserId()+"\nTitle: "+example1.getTitle()+"\nBody :"+example1.getBody());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

    private void Getdata() {

//

        Call<List<Example>> call = apiService.getUser();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                List<Example> examples = response.body();
                for (int i=0;i<examples.size();i++)
                {
                       textView.append("\nId :"+examples.get(i).getId()+"\n\nTitle :"+examples.get(i).getTitle());

                }


            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }
        });
    }
}