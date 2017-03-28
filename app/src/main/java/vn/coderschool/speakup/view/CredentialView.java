package vn.coderschool.speakup.view;

/**
 * Created by udcun on 3/27/2017.
 */

public interface CredentialView extends MvpView {
    void showMessage(String message);
    void clearMessage();
    void showProgressIndicator();
    void hideProgressIndicator();
    void goToLevelTest();
}
