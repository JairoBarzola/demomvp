package com.artifucio.demomvp.presentanción.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.artifucio.demomvp.R;
import com.artifucio.demomvp.presentanción.contracts.LoginContract;
import com.artifucio.demomvp.presentanción.presenter.LoginPresenter;
import com.artifucio.demomvp.presentanción.utils.ProgressDialogCustom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity  implements LoginContract.View{


    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.ln_body)
    LinearLayout lnBody;
    @BindView(R.id.progress)
    ProgressBar progressBar;


    LoginContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(getApplicationContext(),this);

        //progressDialogCustom = new ProgressDialogCustom(getApplicationContext(),"Iniciando...");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkCredentials(etEmail.getText().toString(),etPass.getText().toString());
            }
        });
    }

    /*@OnClick({R.id.btn_login})
    public void onClickListener (View view){

        switch (view.getId()){

            case R.id.btn_login:
                // presenter
        }
    }*/

    @Override
    public void setProgressBar(Boolean state) {
        if(state) {
            lnBody.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }else {
            lnBody.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccessfully() {
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
    }

    @Override
    public void loginError() {
        Toast.makeText(getApplicationContext(),"Email y/o contraseña son incorrectos",Toast.LENGTH_SHORT).show();
    }
}
