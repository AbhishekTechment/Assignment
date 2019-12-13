package com.techment.assignmenttech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techment.assignmenttech.R;
import com.techment.assignmenttech.modal.ResponceItems;
import com.techment.assignmenttech.utility.Utility;

import java.util.List;

import okhttp3.internal.Util;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<ResponceItems> mdataList;
    private AdapterCallBack adapterCallBack;


    public RecyclerAdapter(Context context, List<ResponceItems> mdataList,AdapterCallBack adapterCallBack) {
        this.context = context;
        this.mdataList = mdataList;
        this.adapterCallBack = adapterCallBack;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
              final ResponceItems responceItems = mdataList.get(position);
              holder.tvTile.setText(responceItems.getTitle());
              holder.tvCreatedBy.setText(responceItems.getCreated_at());
              holder.aSwitch.setChecked(responceItems.isSwitchToggle());

              holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      responceItems.setSwitchToggle(isChecked);
                      if(adapterCallBack!=null){
                          adapterCallBack.changeTitle(Utility.getCheckConnt(mdataList));
                      }
                  }
              });


    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTile,tvCreatedBy;
        private Switch aSwitch;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTile = (itemView).findViewById(R.id.tvTitle);
            aSwitch = (itemView).findViewById(R.id.switchCompact);
            tvCreatedBy = (itemView).findViewById(R.id.tvCreatedById);
            linearLayout = (itemView).findViewById(R.id.linearLayout);
        }
    }
    public  interface AdapterCallBack{
        void changeTitle(String title);
    }
    public void setData(int pageSize,List<ResponceItems> responceItems){
        if(pageSize == 1){
            this.mdataList.clear();
        }
        this.mdataList.addAll(responceItems);
        notifyDataSetChanged();
    }

}
