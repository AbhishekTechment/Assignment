package com.techment.assignmenttech.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponceData {
    @SerializedName("hits")
    @Expose
    private ArrayList<ResponceItems> responceItems;

    public ArrayList<ResponceItems> getResponceItems() {
        return responceItems;
    }

    public void setResponceItems(ArrayList<ResponceItems> responceItems) {
        this.responceItems = responceItems;
    }
}
