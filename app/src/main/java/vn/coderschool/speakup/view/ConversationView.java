package vn.coderschool.speakup.view;

import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.model.Topic;
import vn.coderschool.speakup.model.User;

/**
 * Created by kenp on 23/03/2017.
 */

public interface ConversationView extends MvpView {

    void showFindPartner();

    void showVideoCall(MatchingResult matchingResult);

    void showRatePartner();
}
