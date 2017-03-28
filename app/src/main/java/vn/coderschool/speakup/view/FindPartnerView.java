package vn.coderschool.speakup.view;

import vn.coderschool.speakup.model.MatchingResult;

/**
 * Created by kenp on 25/03/2017.
 */

public interface FindPartnerView extends MvpView {
    void showFindingPartner();

    void showPartnerAndConversationDetail(MatchingResult matchingResult);

    void showFindingError(Throwable t);
}
