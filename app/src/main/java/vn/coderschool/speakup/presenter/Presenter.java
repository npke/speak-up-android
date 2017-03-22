package vn.coderschool.speakup.presenter;

/**
 * Created by udcun on 3/22/2017.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
