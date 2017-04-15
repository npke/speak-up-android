package vn.coderschool.speakup.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.presenter.CredentialPresenter;

/**
 * Created by udcun on 3/27/2017.
 */

public class CredentialActivity extends AppCompatActivity implements CredentialView {

    private CredentialPresenter presenter;

    @BindView(R.id.activity_credential_et_email) EditText etEmail;
    @BindView(R.id.activity_credential_et_password) EditText etPassword;
    @BindView(R.id.activity_credential_btn_login) Button btnLogin;
    @BindView(R.id.activity_credential_btn_register) Button btnRegister;

    private ProgressDialog pdInProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);
        ButterKnife.bind(this);

        pdInProgress = new ProgressDialog(this);
        pdInProgress.setMessage("Authenticating.....");
        pdInProgress.setCancelable(false);

        presenter = new CredentialPresenter();
        presenter.attachView(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                hideSoftKeyboard(v);

                presenter.signIn(email, password);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                hideSoftKeyboard(v);

                presenter.createAccount(email, password);
            }
        });

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    clearMessage();
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    clearMessage();
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
        Toasty.error(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearMessage() {
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

    public void hideSoftKeyboard(View view) {
        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        methodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
