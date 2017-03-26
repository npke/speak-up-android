package vn.coderschool.speakup.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.presenter.SignInPresenter;

/**
 * Created by udcun on 3/22/2017.
 */

public class SignInActivity extends AppCompatActivity implements SignInView {

    private SignInPresenter presenter;

    @BindView(R.id.edit_text_email) EditText editTextEmail;
    @BindView(R.id.edit_text_password) EditText editTextPassword;
    @BindView(R.id.button_sign_in) Button  buttonSignIn;
    @BindView(R.id.button_sign_up) Button buttonSignUp;
    @BindView(R.id.progress_bar_wait) ProgressBar progressBarWait;
    @BindView(R.id.text_view_message) TextView textViewMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_sign_in);
        ButterKnife.bind(this);

        presenter = new SignInPresenter();
        presenter.attachView(this);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if (email == null || password == null) {
                    Toast.makeText(SignInActivity.this, "Need email / password", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.createAccount(email, password);
                }
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if (email == null || password == null) {
                    Toast.makeText(SignInActivity.this, "Need email / password", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.signIn(email, password);
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

    @Override
    public void goToLevelTest() {
        Intent intent = new Intent(this, LevelTestActivity.class);
        startActivity(intent);
    }
}
