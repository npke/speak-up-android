package vn.coderschool.speakup.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.coderschool.speakup.model.Sound;
import vn.coderschool.speakup.view.PracticePronunciationView;

public class PracticePronunciationPresenter implements Presenter<PracticePronunciationView> {

    private PracticePronunciationView view;
    private DatabaseReference soundDatabase = FirebaseDatabase.getInstance().getReference("/sounds");

    private List<Sound> vowelSounds = new ArrayList<Sound>();
    private List<Sound> diphthongsSounds = new ArrayList<Sound>();
    private List<Sound> consonantSounds = new ArrayList<Sound>();

    public PracticePronunciationPresenter() {
        soundDatabase.child("vowels").addValueEventListener(getVowelSoundsListener(vowelSounds));
        soundDatabase.child("diphthongs").addValueEventListener(getDiphthongSoundsListener(diphthongsSounds));
        soundDatabase.child("consonants").addValueEventListener(getConsonantSoundsListener(consonantSounds));
    }

    @Override
    public void attachView(PracticePronunciationView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public ValueEventListener getVowelSoundsListener(final List<Sound> sounds) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sounds.clear();
                Iterable<DataSnapshot> listSound = dataSnapshot.getChildren();
                for (DataSnapshot sound : listSound) {
                    sounds.add(sound.getValue(Sound.class));
                }

                view.showVowelSounds(sounds);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public ValueEventListener getDiphthongSoundsListener(final List<Sound> sounds) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sounds.clear();
                Iterable<DataSnapshot> listSound = dataSnapshot.getChildren();
                for (DataSnapshot sound : listSound) {
                    sounds.add(sound.getValue(Sound.class));
                }

                view.showDiphthongSounds(sounds);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public ValueEventListener getConsonantSoundsListener(final List<Sound> sounds) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sounds.clear();
                Iterable<DataSnapshot> listSound = dataSnapshot.getChildren();
                for (DataSnapshot sound : listSound) {
                    sounds.add(sound.getValue(Sound.class));
                }

                view.showConsonantsSounds(sounds);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

}
