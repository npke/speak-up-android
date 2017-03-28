package vn.coderschool.speakup.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import vn.coderschool.speakup.view.CredentialView;

/**
 * Created by udcun on 3/27/2017.
 */

public class CredentialPresenter implements Presenter<CredentialView> {

    public static String TAG = "CredentialPresenter";

    private CredentialView credentialView;

    private FirebaseAuth mAuth;

    public CredentialPresenter() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void attachView(CredentialView view) {
        this.credentialView = view;
    }

    @Override
    public void detachView() {
        this.credentialView = null;
    }

    // If register fails, display a message to the user. If register succeeds
    // the auth state listener will be notified and logic to handle the
    // signed in user can be handled in the listener.
    public void createAccount(String email, String password){
        if (email.isEmpty() || password.isEmpty()) {
            credentialView.showMessage("Email / Password is  empty.");
        } else {
            credentialView.clearMessage();
            credentialView.showProgressIndicator();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                credentialView.hideProgressIndicator();
                                credentialView.goToLevelTest();
                            } else {
                                Log.d(TAG, "createUserWithEmail:failed", task.getException());
                                credentialView.hideProgressIndicator();
                                credentialView.showMessage("Unsuccessful!\n" + task.getException().getMessage());
                            }
                        }
                    });
        }
    }

    // If sign in fails, display a message to the user. If sign in succeeds
    // the auth state listener will be notified and logic to handle the
    // signed in user can be handled in the listener.
    public void signIn(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            credentialView.showMessage("Email / Password is  empty.");
        } else {
            credentialView.clearMessage();
            credentialView.showProgressIndicator();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                credentialView.hideProgressIndicator();
                                credentialView.goToLevelTest();
                            } else {
                                Log.d(TAG, "signInWithEmail:failed", task.getException());
                                credentialView.hideProgressIndicator();
                                credentialView.showMessage("Unsuccessful!\n" + task.getException().getMessage());
                            }
                        }
                    });

        }
    }
}
