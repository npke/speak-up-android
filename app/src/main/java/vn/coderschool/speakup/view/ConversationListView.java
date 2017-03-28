package vn.coderschool.speakup.view;

import java.util.List;

import vn.coderschool.speakup.model.Conversation;


public interface ConversationListView extends MvpView {
    void showConversations(List<Conversation> conversations);

    void openConversationDetail(Conversation conversation);
}
