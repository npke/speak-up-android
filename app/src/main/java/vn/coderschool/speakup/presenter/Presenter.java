package vn.coderschool.speakup.presenter;

import vn.coderschool.speakup.view.MvpView;

/**
 * Created by udcun on 3/22/2017.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}
