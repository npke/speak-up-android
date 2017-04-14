package vn.coderschool.speakup.view;

import vn.coderschool.speakup.model.Question;

/**
 * Created by udcun on 3/23/2017.
 */

public interface LevelTestView extends MvpView {
    void showProgressIndicator();
    void hideProgressIndicator();
    void showQuestion(Question question, String numberOfQuestions, int selectedAnswerId);
    void showUserLevel(String level);
    void showUserAvatar(String url);
}
