package vn.coderschool.speakup.view;

/**
 * Created by udcun on 3/22/2017.
 */

public interface SignInView extends MvpView {

    void showMessage(String message);
    void showProgressIndicator();
    void hideProgressIndicator();
}
