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

    public static String TAG = "SignInPresenter";

    private SignInView signInView;

    public SignInPresenter() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void attachView(SignInView view) {
        this.signInView = view;
    }

    @Override
    public void detachView() {
        this.signInView = null;
    }

    public void createAccount(String email, String password){
        signInView.showProgressIndicator();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            signInView.hideProgressIndicator();
                        } else {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            Log.d(TAG, "createUserWithEmail:failed", task.getException());
                            signInView.showMessage("Unsuccessful! " + task.getException().getMessage());
                        }
                    }
                });
    }

    public void signIn(String email, String password) {
        signInView.showProgressIndicator();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            signInView.hideProgressIndicator();
                            signInView.goToLevelTest();
                        } else {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            Log.d(TAG, "signInWithEmail:failed", task.getException());
                            signInView.showMessage("Unsuccessful! " + task.getException().getMessage());
                        }
                    }
                });

    }
}
