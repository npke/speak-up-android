package vn.coderschool.speakup.presenter;

import vn.coderschool.speakup.view.LevelTestView;

/**
 * Created by udcun on 3/23/2017.
 */

public class LevelTestPresenter implements Presenter<LevelTestView>  {

    private LevelTestView levelTestView;

    @Override
    public void attachView(LevelTestView view) {
        this.levelTestView = view;
    }

    @Override
    public void detachView() {
        this.levelTestView = null;
    }
}
