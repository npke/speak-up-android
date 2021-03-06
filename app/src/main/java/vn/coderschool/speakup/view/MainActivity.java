package vn.coderschool.speakup.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import vn.coderschool.speakup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout_leaderboard)
    public void showLeaderboard() {
        Toasty.info(this, "This feature hasn't been implemented yet!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.layout_start_conversation)
    public void startConversation() {
        startActivity(new Intent(this, ConversationActivity.class));
    }

    @OnClick(R.id.layout_practice_pronunciation)
    public void practicePronunciation() {
        startActivity(new Intent(this, PracticePronunciationActivity.class));
    }

    @OnClick(R.id.layout_sample_conversations)
    public void showSampleConversation() {
        startActivity(new Intent(this, SampleConversationActivity.class));
    }
}
