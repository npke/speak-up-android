package vn.coderschool.speakup.view;

import android.content.Context;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Sound;

public class PracticeSoundActivity extends AppCompatActivity implements PracticeSoundView {

    private static final String YOUTUBE_API_KEY = "AIzaSyAtCYrh5QLcgdn8nMd9z10qHXzhVtwQkgo";

    @BindView(R.id.recycler_practice_words)
    RecyclerView rvPracticeWords;

    @BindView(R.id.text_instruction)
    TextView tvInstruction;

    private Sound sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_sound);

        ButterKnife.bind(this);

        sound = Parcels.unwrap(getIntent().getParcelableExtra("sound"));

        showTrainingVideo();

        showPronounInstruction();

        showPracticeWords();
    }

    @Override
    public void showTrainingVideo() {
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.training_video);

        playerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(sound.getTrainingVideoUrl());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    @Override
    public void showPronounInstruction() {
        tvInstruction.setText(sound.getInstruction());
    }

    @Override
    public void showPracticeWords() {
        WordAdapter adapter = new WordAdapter(sound.getPracticeWords());
        rvPracticeWords.setAdapter(adapter);
        rvPracticeWords.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public Context getContext() {
        return getContext();
    }
}
