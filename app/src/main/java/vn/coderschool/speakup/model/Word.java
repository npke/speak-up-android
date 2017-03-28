package vn.coderschool.speakup.model;

import org.parceler.Parcel;

@Parcel
public class Word {
    public String text;
    public String audioUrl;
    public String ipa;

    public Word() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }
}
