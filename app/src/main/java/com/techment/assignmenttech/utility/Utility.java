package com.techment.assignmenttech.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techment.assignmenttech.modal.ResponceItems;

import java.util.List;

public class Utility {
    public static boolean isInternetConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public static void showToastMsg(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
    public  static String getCheckConnt(List<ResponceItems> responceItemsList){
        int count =0;
        for (int i=0;i<responceItemsList.size();i++){
            if(responceItemsList.get(i).isSwitchToggle()){
                count++;
            }
        }
        return String.valueOf(count);
    }
    public static void setActiveToogle(AppCompatActivity compatActivity,String acctiveToogle){
        if(compatActivity.getSupportActionBar()!=null){
            compatActivity.getSupportActionBar().setTitle(acctiveToogle);

        }

    }

}
