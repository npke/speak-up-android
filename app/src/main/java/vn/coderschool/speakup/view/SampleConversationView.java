package vn.coderschool.speakup.view;

import java.util.List;

import vn.coderschool.speakup.model.Conversation;


public interface SampleConversationView extends MvpView {
    void showConversation(List<Conversation> beginner,
                          List<Conversation> immediate,
                          List<Conversation> advanced);
}
