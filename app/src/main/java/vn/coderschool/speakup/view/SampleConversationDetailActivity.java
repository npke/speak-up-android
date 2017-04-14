package vn.coderschool.speakup.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Conversation;
import vn.coderschool.speakup.model.Script;

public class SampleConversationDetailActivity extends AppCompatActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyAtCYrh5QLcgdn8nMd9z10qHXzhVtwQkgo";
    private Conversation conversation;
    private YouTubePlayer mPlayer;
    private List<Script> mScripts;
    private int mCountForScript;
    private Handler mHandler = null;
    private LinearLayoutManager mLayoutManager;

    @BindView(R.id.recycler_scripts)
    RecyclerView rvScripts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_conversation_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        conversation = Parcels.unwrap(intent.getParcelableExtra("conversation"));
        mScripts = conversation.getScripts();
        mHandler = new Handler();
        showVideoSample();
        showScripts();
    }

    public void showVideoSample() {
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.sample_video);
        playerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                mPlayer = youTubePlayer;
                if (!b)mPlayer.cueVideo(conversation.videoUrl);
                mPlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {
                        mHandler.postDelayed(runnable, 100);
                        handleCurrentTime();

//                        new HandlerTiming().execute();
                    }

                    @Override
                    public void onPaused() {
//                        Toast.makeText(getBaseContext(), "onPaused", Toast.LENGTH_SHORT).show();
                        mHandler.removeCallbacks(runnable);
                    }

                    @Override
                    public void onStopped() {
                        mHandler.removeCallbacks(runnable);
                    }

                    @Override
                    public void onBuffering(boolean b) {

                    }

                    @Override
                    public void onSeekTo(int i) {
                        mHandler.postDelayed(runnable, 100);
                    }
                });

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }

    public void showScripts() {
        ScriptAdapter adapter = new ScriptAdapter(conversation.getScripts());
        rvScripts.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(this);
        rvScripts.setLayoutManager(mLayoutManager);
    }

    private class HandlerTiming extends AsyncTask<Void, Integer, Integer>{

        @Override
        protected Integer doInBackground(Void... params) {
            while (mPlayer.isPlaying()){

                int currentSecond = mPlayer.getCurrentTimeMillis()/1000;
                Log.d("currentSecond: ", currentSecond + "");
//            if (mPlayer.isPlaying()) Toast.makeText(getBaseContext(), "is playing", Toast.LENGTH_SHORT).show();
                if (currentSecond == mScripts.get(mCountForScript).getSecond()){
                    publishProgress(mCountForScript);
                    mCountForScript++;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getBaseContext(), "current time: " + values, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }

    private void handleCurrentTime(){
        if (mCountForScript < mScripts.size()){
            if (mPlayer.getCurrentTimeMillis()/1000 == mScripts.get(mCountForScript).getSecond()){
                Toast.makeText(getBaseContext(), "current time: " + mPlayer.getCurrentTimeMillis()/1000, Toast.LENGTH_SHORT).show();
                rvScripts.smoothScrollToPosition(mCountForScript);
                mLayoutManager.scrollToPositionWithOffset(mCountForScript, 0);
                mCountForScript++;
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handleCurrentTime();
            mHandler.postDelayed(this, 100);
        }
    };

    private void scrollSub(int position){
        rvScripts.smoothScrollToPosition(position);
    }

}
