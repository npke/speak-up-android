package vn.coderschool.speakup.model;

/**
 * Created by kenp on 23/03/2017.
 */

public class MatchingResult {
    private String partnerId;
    private String selectedTopic;
    private boolean makeCall;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(String selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public boolean isMakeCall() {
        return makeCall;
    }

    public void setMakeCall(boolean makeCall) {
        this.makeCall = makeCall;
    }
}
