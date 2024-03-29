package com.techment.assignmenttech.retrofit;

import com.techment.assignmenttech.utility.UrlConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance(){
        if(retrofit== null){
            retrofit = new Retrofit.Builder().baseUrl(UrlConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
