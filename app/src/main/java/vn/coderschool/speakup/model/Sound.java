package vn.coderschool.speakup.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Sound {
    public String symbol;

    public String hint;

    public String audioUrl;

    public List<Word> practiceWords;

    public String trainingVideoUrl;

    public String instruction;

    public Sound() {

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public List<Word> getPracticeWords() {
        return practiceWords;
    }

    public void setPracticeWords(List<Word> practiceWords) {
        this.practiceWords = practiceWords;
    }

    public String getTrainingVideoUrl() {
        return trainingVideoUrl;
    }

    public void setTrainingVideoUrl(String trainingVideoUrl) {
        this.trainingVideoUrl = trainingVideoUrl;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
