package vn.coderschool.speakup.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import vn.coderschool.speakup.R;
import vn.coderschool.speakup.view.SignInView;

/**
 * Created by udcun on 3/22/2017.
 */

public class SignInPresenter implements Presenter<SignInView> {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public static String TAG = "SignInPresenter";

    private SignInView signInView;

    @Override
    public void attachView(SignInView view) {
        this.signInView = view;
    }

    @Override
    public void detachView() {
        this.signInView = null;
    }

    public void createAccount(String email, String password){

    }

    public void signIn(String email, String password) {

    }
}
