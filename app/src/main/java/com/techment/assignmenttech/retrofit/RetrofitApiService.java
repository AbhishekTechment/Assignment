package com.techment.assignmenttech.retrofit;

import com.techment.assignmenttech.modal.ResponceData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiService {

    @GET("search_by_date?tags=story")
    Call<ResponceData>responceData(@Query("page") int page_number);
}
