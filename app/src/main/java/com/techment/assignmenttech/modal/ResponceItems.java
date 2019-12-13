package com.techment.assignmenttech.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponceItems {
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("title")
    @Expose
    private String title;
    private boolean switchToggle = false;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSwitchToggle() {
        return switchToggle;
    }

    public void setSwitchToggle(boolean switchToggle) {
        this.switchToggle = switchToggle;
    }
}
