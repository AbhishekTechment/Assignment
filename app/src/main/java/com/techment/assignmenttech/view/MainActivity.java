package com.techment.assignmenttech.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.techment.assignmenttech.R;
import com.techment.assignmenttech.adapter.RecyclerAdapter;
import com.techment.assignmenttech.modal.ResponceItems;
import com.techment.assignmenttech.utility.RecyclerOnScrollListner;
import com.techment.assignmenttech.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private RecyclerView recyclerView;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter recyclerAdapter;
    private ProgressBar progressBarBottom,progressBarCenter;
    private List<ResponceItems> responceItemsList;
    private MainPresenter mainPresenter;
    private int pageSize = 1;
    private RecyclerAdapter.AdapterCallBack adapterCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setAdapter();
        setPullTorefresh();
        recyclerView.addOnScrollListener(new RecyclerOnScrollListner() {
            @Override
            protected void onLoadMore() {
                if(Utility.isInternetConnected(MainActivity.this)){
                    pageSize++;
                    mainPresenter.loadData(pageSize);
                }else{
                    Utility.showToastMsg(context,"No Internet Connection");
                }
            }
        });

    }

    private void init() {
        context = this;
        recyclerView = findViewById(R.id.recyclerview);
        progressBarBottom = findViewById(R.id.progressbarBottom);
        progressBarCenter = findViewById(R.id.progressBarCenter);
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        responceItemsList = new ArrayList<>();
        mainPresenter = new MainPresenter(this);
        if(Utility.isInternetConnected(context)){
            progressBarCenter.setVisibility(View.VISIBLE);
            mainPresenter.loadData(pageSize);
        }else{
            Utility.showToastMsg(context,"No Internet Connection");
        }
        Utility.setActiveToogle(this,"0");
        adapterCallBack = new RecyclerAdapter.AdapterCallBack() {
            @Override
            public void changeTitle(String title) {
                Utility.setActiveToogle(MainActivity.this,title);
            }
        };
    }


    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        recyclerAdapter = new RecyclerAdapter(context,responceItemsList,adapterCallBack);
        recyclerView.setAdapter(recyclerAdapter);
    }


    private void setPullTorefresh() {
     swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
         @Override
         public void onRefresh() {
             if(Utility.isInternetConnected(context)){
                 recyclerView.setVisibility(View.GONE);
                 refreshView();
             }else{
                 Utility.showToastMsg(context,"No internet connection");

             }
         }
     });

    }

    @Override
    public void showData(int pageSize, List<ResponceItems> responceItems) {
        recyclerAdapter.setData(pageSize,responceItems);
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showError() {
        Utility.showToastMsg(context,"Something Went Wrong");

    }

    @Override
    public void showProgressBar() {
        progressBarBottom.setVisibility(View.VISIBLE);


    }

    @Override
    public void hideProgressBar() {
        progressBarBottom.setVisibility(View.GONE);
        progressBarCenter.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);


    }
    public void refreshView() {
        Utility.setActiveToogle(this,"0");
        swipeRefreshLayout.setRefreshing(true);
        pageSize = 1;
        mainPresenter.loadData(pageSize);

    }
}
