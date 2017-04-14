package vn.coderschool.speakup.view;

import java.util.List;

import vn.coderschool.speakup.model.Sound;

public interface PracticePronunciationView extends MvpView {
   void showSounds(List<Sound> vowels, List<Sound> diphthongs, List<Sound> consonants);
}
