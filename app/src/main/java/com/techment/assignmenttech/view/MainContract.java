package com.techment.assignmenttech.view;

import com.techment.assignmenttech.modal.ResponceItems;

import java.util.List;

public class MainContract  {
    MainContract.View view;

    public interface Presenter{
        void loadData(int pageSize);
    }

    public MainContract(View view) {
        this.view = view;
    }

    public interface View{
        void showData(int pageSize, List<ResponceItems> responceItems);
        void showError();
        void showProgressBar();
        void hideProgressBar();

    }
}
