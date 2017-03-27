package vn.coderschool.speakup.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.presenter.CredentialsPresenter;

/**
 * Created by udcun on 3/27/2017.
 */

public class CredentialsActivity extends AppCompatActivity implements CredentialsView {

    private CredentialsPresenter presenter;

    @BindView(R.id.activity_credentials_iv_logo) ImageView ivLogo;
    @BindView(R.id.activity_credentials_tv_headline) TextView tvHeadline;
    @BindView(R.id.activity_credentials_et_email) EditText etEmail;
    @BindView(R.id.activity_credentials_et_password) EditText etPassword;
    @BindView(R.id.activity_credentials_btn_login) Button btnLogin;
    @BindView(R.id.activity_credentials_btn_register) Button btnRegister;
    @BindView(R.id.activity_credentials_tv_error_message) TextView tvErrorMessage;

    private ProgressDialog pdInProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
        ButterKnife.bind(this);

        pdInProgress = new ProgressDialog(this);
        pdInProgress.setMessage("Authenticating.....");
        pdInProgress.setCancelable(false);

        presenter = new CredentialsPresenter();
        presenter.attachView(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email == null || password == null) {
                    Toast.makeText(getContext(), "Need email / password", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.signIn(email, password);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email == null || password == null) {
                    Toast.makeText(getContext(), "Need email / password", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.createAccount(email, password);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showMessage(String message) {
        tvErrorMessage.setVisibility(View.VISIBLE);
        tvErrorMessage.setText(message);
    }

    @Override
    public void clearMessage() {
        tvErrorMessage.setVisibility(View.GONE);
        tvErrorMessage.setText("");
    }

    @Override
    public void showProgressIndicator() {
        pdInProgress.show();
    }

    @Override
    public void hideProgressIndicator() {
        pdInProgress.dismiss();
    }

    @Override
    public void goToLevelTest() {
        Intent intent = new Intent(this, LevelTestActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
