package vn.coderschool.speakup.view;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.coderschool.speakup.R;
import vn.coderschool.speakup.presenter.SignInPresenter;

/**
 * Created by udcun on 3/22/2017.
 */

public class SignInActivity extends AppCompatActivity implements SignInView {

    private SignInPresenter presenter;
    private EditText editTextEmail, editTextPassword;
    private Button buttonSignIn, buttonSignUp;
    private ProgressBar progressBarWait;
    private TextView textViewMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_sign_in);

        presenter = new SignInPresenter();
        presenter.attachView(this);

        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextPassword = (EditText) findViewById(R.id.edit_text_password);
        buttonSignIn = (Button) findViewById(R.id.button_sign_in);
        buttonSignUp = (Button) findViewById(R.id.button_sign_up);
        progressBarWait = (ProgressBar) findViewById(R.id.progress_bar_wait);
        textViewMessage = (TextView) findViewById(R.id.text_view_message);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createAccount(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        progressBarWait.setVisibility(View.GONE);
        textViewMessage.setVisibility(View.VISIBLE);
        textViewMessage.setText(message);
    }

    @Override
    public void showProgressIndicator() {
        textViewMessage.setVisibility(View.GONE);
        progressBarWait.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndicator() {
        progressBarWait.setVisibility(View.GONE);
    }
}
