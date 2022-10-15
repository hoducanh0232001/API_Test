package com.example.api_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_test.API.ApiService;
import com.example.api_test.model.Currency;
import com.example.api_test.model.Post;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTerms, tvSource, tvUSDVND,tvTerms2, tvSource2, tvUSDVND2, tvPost;
    private Button btnCallApi, btnCallApi2, btnPostApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTerms = findViewById(R.id.tv_terms);
        tvSource = findViewById(R.id.tv_source);
        tvUSDVND = findViewById(R.id.tv_usdVnd);
        btnCallApi = findViewById(R.id.btn_call_api);

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallApi();
            }
        });

        tvTerms2 = findViewById(R.id.tv_terms_2);
        tvSource2 = findViewById(R.id.tv_source_2);
        tvUSDVND2 = findViewById(R.id.tv_usdVnd_2);
        btnCallApi2 = findViewById(R.id.btn_call_api_2);

        btnCallApi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallApi2();
            }
        });

        tvPost = findViewById(R.id.post_api);
        btnPostApi = findViewById(R.id.btn_post_api);
        btnPostApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPosts();
            }
        });
    }

    private void clickCallApi2() {
        Map<String, String> options = new HashMap<>();
        options.put("access_key","843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies","VND");
        options.put("source","USD");
        options.put("format","1");
        ApiService.apiService.convertUsdToVnd2(options).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call API Success 2", Toast.LENGTH_SHORT).show();
                Currency currency = response.body();
                if (currency!= null && currency.isSuccess()){
                    tvTerms2.setText(currency.getTerms());
                    tvSource2.setText(currency.getSource());
                    tvUSDVND2.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Failed 2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickCallApi() {
        //link api : http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
        ApiService.apiService.convertUsdToVnd("843d4d34ae72b3882e3db642c51e28e6","VND","USD",1)
                .enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call API Success ", Toast.LENGTH_SHORT).show();

                Currency currency = response.body();
                if (currency != null && currency.isSuccess()){
                    tvTerms.setText(currency.getTerms());
                    tvSource.setText(currency.getSource());
                    tvUSDVND.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendPosts(){
        Post post = new Post(10,100,"hello", " Ducanhday");
        ApiService.apiService.sendPosts(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "Post API Success", Toast.LENGTH_SHORT).show();

                Post postResult = response.body();
                if(postResult != null ){
                    tvPost.setText(postResult.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Post API Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}