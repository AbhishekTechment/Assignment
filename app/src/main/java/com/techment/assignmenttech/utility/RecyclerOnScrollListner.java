package com.techment.assignmenttech.utility;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerOnScrollListner  extends RecyclerView.OnScrollListener {

    public static int previous = 0;
    public static boolean loading = true;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibileItemCount =recyclerView.getChildCount();
        if(recyclerView.getLayoutManager()!=null){
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibelItems=((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
            if(loading){
                if(totalItemCount>previous){
                    loading=false;
                    previous=totalItemCount;
                }
            }
            int visibelThreshole =5;
            if(!loading && (totalItemCount - visibileItemCount)<= (firstVisibelItems + visibelThreshole)){
                loading = true;
                onLoadMore();
            }

        }

    }
    protected abstract void onLoadMore();
}
