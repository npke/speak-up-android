package vn.coderschool.speakup.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.model.Topic;
import vn.coderschool.speakup.model.User;

public class ConversationActivity extends AppCompatActivity implements ConversationView {

    private FindPartnerFragment findPartner;
    private VideoCallFragment videoCall;
    private RatePartnerFragment ratePartner;

    private FindPartnerFragment.FindPartnerListener findPartnerListener = new FindPartnerFragment.FindPartnerListener() {
        @Override
        public void onStartConversation(MatchingResult matchingResult) {
            showVideoCall(matchingResult);
        }
    };

    private VideoCallFragment.VideoCallListener videoCallListener = new VideoCallFragment.VideoCallListener() {
        @Override
        public void onCallFinish() {
            showRatePartner();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        findPartner = FindPartnerFragment.getInstance(findPartnerListener);
        showFindPartner();
    }

    @Override
    public void showFindPartner() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_conversation, findPartner)
                .commit();
    }

    @Override
    public void showVideoCall(MatchingResult matchingResult) {
        videoCall = VideoCallFragment.getInstance(videoCallListener, matchingResult);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_conversation, videoCall)
                .commit();
    }

    @Override
    public void showRatePartner() {

        ratePartner = new RatePartnerFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_conversation, ratePartner)
                .commit();
    }

    @Override
    public Context getContext() {
        return getContext();
    }
}
