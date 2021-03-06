package vn.coderschool.speakup.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
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
        public void onPartnerFound(MatchingResult matchingResult) {
            showVideoCall(matchingResult);
        }

        @Override
        public void onPartnerNotFound() {
            Toasty.error(ConversationActivity.this, "Partner not found!", Toast.LENGTH_SHORT, true).show();
            finish();
        }
    };

    private VideoCallFragment.VideoCallListener videoCallListener = new VideoCallFragment.VideoCallListener() {
        @Override
        public void onCallFinish(User partner) {
            showRatePartner(partner);
        }
    };

    private RatePartnerFragment.RatePartnerListener ratePartnerListener = new RatePartnerFragment.RatePartnerListener() {
        @Override
        public void onRateSubmit(float rating) {
            Toasty.success(ConversationActivity.this, "Rate successfully!", Toast.LENGTH_SHORT, true).show();
            finish();
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
    public void showRatePartner(User partner) {

        ratePartner = RatePartnerFragment.getInstance(ratePartnerListener, partner);

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
