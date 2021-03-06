package com.example.callapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.callapi.api.ApiService;
import com.example.callapi.model.Currency;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTerms;
    private TextView tvSource;
    //private TextView tvQuotes;
    private Button btnCallAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTerms = (TextView) findViewById(R.id.tvTerms);
        tvSource = (TextView) findViewById(R.id.tvSource);
        //tvQuotes = (TextView) findViewById(R.id.tvQuotes);
        btnCallAPI = (Button) findViewById(R.id.btnCallAPI);

        // Set sự kiện

        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCallAPI();
            }
        });



//        Job job = new Job(1, "Doctor");
//        List<Favorite> favoriteList = new ArrayList<>();
//        favoriteList.add(new Favorite(1, "Football"));
//        favoriteList.add(new Favorite(2, "Sleep"));
//
//        User user = new User(1, "Quang", true, job, favoriteList);

        // convert dữ liệu qua Json

//        Gson gson = new Gson();
//        String strJson = gson.toJson(user);
//        Log.e("Json String", strJson);

    }

    public void onClickCallAPI() {
        //API link: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
        ApiService.apiService.convert("843d4d34ae72b3882e3db642c51e28e6","VND", "USD", 1).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call API Successed", Toast.LENGTH_SHORT).show();

                Currency currency = response.body();

                if (currency != null && currency.isSuccess()) {
                    tvTerms.setText(currency.getTerms());
                    tvSource.setText(currency.getSource());
                    //tvQuotes.setText(currency.getQuotes().getUSDVND());
                }

            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}