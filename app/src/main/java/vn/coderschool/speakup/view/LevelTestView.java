package vn.coderschool.speakup.view;

import vn.coderschool.speakup.model.Question;

/**
 * Created by udcun on 3/23/2017.
 */

public interface LevelTestView extends MvpView {

    void showQuestion(Question question);
    void showLevel(String level);
    void showUserAvatar(String url);
}
