package com.artifucio.demomvp.presentanción.contracts;

public interface LoginContract {
    public  interface  View{
        void setProgressBar(Boolean state);
        void showToast(String message);
        void loginSuccessfully();
        void loginError();
    }
    public  interface  Presenter{
        void checkCredentials(String email, String pass);
    }

}
