package com.artifucio.demomvp.presentanción.presenter;

import android.content.Context;

import com.artifucio.demomvp.presentanción.contracts.LoginContract;
import com.artifucio.demomvp.presentanción.data.remote.LoginRequest;
import com.artifucio.demomvp.presentanción.data.remote.ServiceFactory;
import com.artifucio.demomvp.presentanción.utils.Constanst;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    Context context;
    LoginContract.View view;
    //SessionManager manager ;

    public LoginPresenter (Context context, LoginContract.View view){
        this.context= context;
        this.view= view;
    }

    @Override
    public void checkCredentials(String email, String pass) {

        if(email.isEmpty() || pass.isEmpty()){
            view.showToast("Por favor ingrese los campos");
        }else{
            //llamada a la api
            view.setProgressBar(true);
            JSONObject paramObject = new JSONObject();
            try {
                paramObject.put("email",email);
                paramObject.put("password",pass);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(paramObject.toString()));

            LoginRequest request = ServiceFactory.createService(LoginRequest.class);
            Call<ResponseBody> call = request.login(Constanst.CONTENT_TYPE_JSON,Constanst.CONTENT_TYPE_JSON,body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    view.setProgressBar(false);
                    if(response.code()==200){
                        view.loginSuccessfully();
                    }else{
                        view.loginError();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    view.setProgressBar(false);
                    view.showToast(t.getMessage());
                }
            });
        }

    }
}
