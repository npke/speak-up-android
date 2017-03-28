package vn.coderschool.speakup.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Sound;
import vn.coderschool.speakup.presenter.PracticePronunciationPresenter;

public class PracticePronunciationActivity extends AppCompatActivity implements PracticePronunciationView {

    @BindView(R.id.recycler_vowels)
    RecyclerView rvVowels;

    @BindView(R.id.recycler_diphthongs)
    RecyclerView rvDiphthongs;

    @BindView(R.id.recycler_consonants)
    RecyclerView rvConsonants;

    private PracticePronunciationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pronunciation);

        ButterKnife.bind(this);

        presenter = new PracticePronunciationPresenter();
        presenter.attachView(this);
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public void showVowelSounds(List<Sound> sound) {
       showSounds(rvVowels, sound);
    }

    @Override
    public void showDiphthongSounds(List<Sound> sound) {
        showSounds(rvDiphthongs, sound);
    }

    @Override
    public void showConsonantsSounds(List<Sound> sound) {
        showSounds(rvConsonants, sound);
    }

    public void showSounds(RecyclerView recyclerView, List<Sound> sounds) {
        SoundAdapter adapter = new SoundAdapter(sounds);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public void showSoundPractice() {
        Toast.makeText(this, "Show practice for sound", Toast.LENGTH_SHORT).show();
    }
}
