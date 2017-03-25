package vn.coderschool.speakup.model;

/**
 * Created by kenp on 23/03/2017.
 */

public class MatchingResult {
    private String partner;
    private String selectedTopic;
    private boolean makeCall;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
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
