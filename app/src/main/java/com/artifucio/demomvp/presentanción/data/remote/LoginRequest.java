package com.artifucio.demomvp.presentanción.data.remote;

import com.artifucio.demomvp.presentanción.utils.Constanst;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginRequest {

    @POST(Constanst.LOGIN)
    Call<ResponseBody> login(@Header("Accept") String accept, @Header("Content-type") String content, @Body RequestBody body);
}
