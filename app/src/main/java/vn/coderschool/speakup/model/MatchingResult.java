package vn.coderschool.speakup.model;

/**
 * Created by kenp on 23/03/2017.
 */

public class MatchingResult {
    private User partner;
    private Topic selectedTopic;
    private boolean makeCall;

    public User getPartner() {
        return partner;
    }

    public void setPartner(User partner) {
        this.partner = partner;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public boolean isMakeCall() {
        return makeCall;
    }

    public void setMakeCall(boolean makeCall) {
        this.makeCall = makeCall;
    }
}
