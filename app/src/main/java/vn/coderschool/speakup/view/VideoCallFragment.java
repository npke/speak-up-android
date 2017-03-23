package vn.coderschool.speakup.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sinch.android.rtc.ClientRegistration;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchClientListener;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.video.VideoController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.model.User;

public class VideoCallFragment extends Fragment {

    public interface VideoCallListener {
        void onCallFinish();
    }

    private final String SINCH_APP_KEY = "420799df-c501-4c8c-bc3b-4e2b8f8e4946";
    private final String SINCH_APP_SECRET = "dKXg2GvUx0SDLqpdzH6r9Q==";
    private final String SINCH_ENV_HOST = "sandbox.sinch.com";

    private SinchClient sinchClient;
    private CallClient callClient;
    private Call call;

    private MatchingResult matchingResult;

    private User user;
    private User partner;

    private boolean inConversation = false;

    private VideoCallListener listener;

    @BindView(R.id.layout_partner_video)
    RelativeLayout partnerVideo;

    @BindView(R.id.layout_user_video)
    RelativeLayout userVideo;

    public static VideoCallFragment getInstance(VideoCallListener listener, MatchingResult matchingResult) {
        VideoCallFragment fragment = new VideoCallFragment();
        fragment.listener = listener;
        fragment.matchingResult = matchingResult;
        fragment.partner = new User();
        fragment.partner.setId(matchingResult.getPartnerId());

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_call, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        configSinchService(user.getId());

        if (matchingResult.isMakeCall())
            makeCall(partner.getId());
    }

    private void configSinchService(String userId) {
        sinchClient = Sinch.getSinchClientBuilder()
                .context(getContext())
                .applicationKey(SINCH_APP_KEY)
                .applicationSecret(SINCH_APP_SECRET)
                .environmentHost(SINCH_ENV_HOST)
                .userId(userId)
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.setSupportMessaging(true);

        sinchClient.addSinchClientListener(getSinchClientListener());

        sinchClient.start();

        callClient = sinchClient.getCallClient();
        callClient.addCallClientListener(new CallClientListener() {
            @Override
            public void onIncomingCall(CallClient callClient, Call call) {
                Toast.makeText(getActivity(), "Ringing...", Toast.LENGTH_SHORT).show();
                configCall(call);
                call.answer();
            }
        });
    }

    private void configCall(Call incommingCall) {
        call = incommingCall;
        call.addCallListener(new com.sinch.android.rtc.video.VideoCallListener() {
            @Override
            public void onVideoTrackAdded(Call call) {
                VideoController vc = sinchClient.getVideoController();

                partnerVideo.addView(vc.getRemoteView());
                userVideo.addView(vc.getLocalView());
            }

            @Override
            public void onCallProgressing(Call call) {

            }

            @Override
            public void onCallEstablished(Call call) {

            }

            @Override
            public void onCallEnded(Call call) {
                endConversation();
            }

            @Override
            public void onShouldSendPushNotification(Call call, List<PushPair> list) {

            }
        });
    }


    @NonNull
    private SinchClientListener getSinchClientListener() {
        return new SinchClientListener() {
            @Override
            public void onClientStarted(SinchClient sinchClient) {

            }

            @Override
            public void onClientStopped(SinchClient sinchClient) {

            }

            @Override
            public void onClientFailed(SinchClient sinchClient, SinchError sinchError) {

            }

            @Override
            public void onRegistrationCredentialsRequired(SinchClient sinchClient, ClientRegistration clientRegistration) {

            }

            @Override
            public void onLogMessage(int i, String s, String s1) {

            }
        };
    }

    public void makeCall(String partnerId) {
        call = callClient.callUserVideo(partnerId);
        configCall(call);
    }

    @OnClick(R.id.button_end)
    public void endConversation() {

        call.hangup();
        sinchClient.terminate();
        listener.onCallFinish();
    }
}
