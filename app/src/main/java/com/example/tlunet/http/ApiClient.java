package com.example.tlunet.http;

import com.example.tlunet.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;

public class ApiClient extends BaseClient {
    private static int REQUEST_TIMEOUT = 1000;
    @Override
    protected OkHttpClient httpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
//        builder.addInterceptor(new AuthenticateInterceptor());
        return builder.build();
    }

    @Override
    protected String baseUrl() {
        return "https://tlu.beenet.vn/wp-json/wp/v2/";
//        return BuildConfig.BASE_URL;
    }

    @Override
    protected boolean implementRx() {
        return true;
    }

    @Override
    protected Converter.Factory converter() {
//        return ResponseConverterFactory.create();
        return null;
    }
}
