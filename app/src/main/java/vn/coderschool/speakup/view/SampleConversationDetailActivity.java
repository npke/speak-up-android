package vn.coderschool.speakup.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Conversation;

public class SampleConversationDetailActivity extends AppCompatActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyAtCYrh5QLcgdn8nMd9z10qHXzhVtwQkgo";
    private Conversation conversation;

    @BindView(R.id.recycler_scripts)
    RecyclerView rvScripts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_conversation_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        conversation = Parcels.unwrap(intent.getParcelableExtra("conversation"));

        getSupportActionBar().setTitle(conversation.getName());

        showVideoSample();
        showScripts();
    }

    public void showVideoSample() {
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.sample_video);
        playerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(conversation.videoUrl);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    public void showScripts() {
        ScriptAdapter adapter = new ScriptAdapter(conversation.getScripts());
        rvScripts.setAdapter(adapter);
        rvScripts.setLayoutManager(new LinearLayoutManager(this));
    }

}
