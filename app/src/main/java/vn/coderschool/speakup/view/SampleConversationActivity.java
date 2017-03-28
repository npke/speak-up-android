package vn.coderschool.speakup.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.coderschool.speakup.R;

public class SampleConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_conversation);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout_beginner)
    public void showBeginnerSampleConversations() {
        showConversationList("beginner");
    }

    @OnClick(R.id.layout_intermediate)
    public void showIntermediateSampleConversations() {
        showConversationList("intermediate");
    }

    @OnClick(R.id.layout_beginner)
    public void showAdvancedSampleConversations() {
        showConversationList("advanced");
    }

    public void showConversationList(String level) {
        Intent intent = new Intent(this, ConversationListActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}
