package vn.coderschool.speakup.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import vn.coderschool.speakup.view.CredentialsView;

/**
 * Created by udcun on 3/27/2017.
 */

public class CredentialsPresenter implements Presenter<CredentialsView> {

    public static String TAG = "CredentialsPresenter";

    private CredentialsView credentialsView;

    private FirebaseAuth mAuth;

    public CredentialsPresenter() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void attachView(CredentialsView view) {
        this.credentialsView = view;
    }

    @Override
    public void detachView() {
        this.credentialsView = null;
    }

    // If register fails, display a message to the user. If register succeeds
    // the auth state listener will be notified and logic to handle the
    // signed in user can be handled in the listener.
    public void createAccount(String email, String password){
        credentialsView.clearMessage();
        credentialsView.showProgressIndicator();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            credentialsView.hideProgressIndicator();
                            credentialsView.goToLevelTest();
                        } else {
                            Log.d(TAG, "createUserWithEmail:failed", task.getException());
                            credentialsView.hideProgressIndicator();
                            credentialsView.showMessage("Unsuccessful!\n" + task.getException().getMessage());
                        }
                    }
                });
    }

    // If sign in fails, display a message to the user. If sign in succeeds
    // the auth state listener will be notified and logic to handle the
    // signed in user can be handled in the listener.
    public void signIn(String email, String password) {
        credentialsView.clearMessage();
        credentialsView.showProgressIndicator();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            credentialsView.hideProgressIndicator();
                            credentialsView.goToLevelTest();
                        } else {
                            Log.d(TAG, "signInWithEmail:failed", task.getException());
                            credentialsView.hideProgressIndicator();
                            credentialsView.showMessage("Unsuccessful!\n" + task.getException().getMessage());
                        }
                    }
                });

    }
}
