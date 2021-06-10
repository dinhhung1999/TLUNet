//package com.example.tlunet.http;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Converter;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public abstract class BaseClient {
//    private static Retrofit retrofit;
//
//    public <T> T getService(Class<T> service) {
//        if (retrofit == null) {
//            Retrofit.Builder builder = new Retrofit.Builder();
//            builder.baseUrl(baseUrl());
//            Converter.Factory converter = converter();
//            if (converter == null) {
//                converter = GsonConverterFactory.create();
//            }
//            builder.addConverterFactory(converter);
//            if (implementRx()) {
//                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
//            }
//            OkHttpClient client = httpClient();
//            if (client != null) {
//                builder.client(client);
//            }
//            retrofit = builder.build();
//        }
//        return retrofit.create(service);
//    }
//
//    protected abstract OkHttpClient httpClient();
//
//    protected abstract String baseUrl();
//
//    protected abstract boolean implementRx();
//
//    protected abstract Converter.Factory converter();
//}
