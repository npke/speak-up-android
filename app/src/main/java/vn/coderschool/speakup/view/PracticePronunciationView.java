package vn.coderschool.speakup.view;

import java.util.List;

import vn.coderschool.speakup.model.Sound;

public interface PracticePronunciationView extends MvpView {
    void showVowelSounds(List<Sound> sound);

    void showDiphthongSounds(List<Sound> sound);

    void showConsonantsSounds(List<Sound> sound);

    void showSoundPractice();
}
