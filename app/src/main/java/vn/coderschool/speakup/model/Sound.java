package vn.coderschool.speakup.model;

import java.util.List;

public class Sound {
    private String symbol;

    private String hint;

    private String audioUrl;

    private List<Word> practiceWords;

    private String trainingVideoUrl;

    private String instruction;

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
