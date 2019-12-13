package com.techment.assignmenttech.view;

import com.techment.assignmenttech.base.BasePresenter;
import com.techment.assignmenttech.modal.ResponceData;
import com.techment.assignmenttech.retrofit.RetrofitApiService;
import com.techment.assignmenttech.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitApiService retrofitApiService;

    public MainPresenter(MainContract.View mView) {
        super(mView);
    }

    @Override
    public void loadData(final int pageSize) {
        mView.showProgressBar();
        retrofitApiService = RetrofitInstance.getRetrofitInstance().create(RetrofitApiService.class);
        retrofitApiService.responceData(pageSize).enqueue(new Callback<ResponceData>() {
            @Override
            public void onResponse(Call<ResponceData> call, Response<ResponceData> response) {
                mView.hideProgressBar();
                mView.showData(pageSize,response.body().getResponceItems());
            }

            @Override
            public void onFailure(Call<ResponceData> call, Throwable t) {

            }
        });
    }
}
