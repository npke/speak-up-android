package vn.coderschool.speakup.view;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.model.User;
import vn.coderschool.speakup.presenter.VideoCallPresenter;
import vn.coderschool.speakup.speech_recognize.SpeechService;
import vn.coderschool.speakup.speech_recognize.VoiceRecorder;

import static android.content.Context.BIND_AUTO_CREATE;

public class VideoCallFragment extends Fragment implements VideoCallView {

    private VideoCallPresenter presenter;

    private MatchingResult matchingResult;
    private boolean microphoneEnable = true;
    private boolean recording = false;

    private SpeechService mSpeechService;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 1;
    private VoiceRecorder mVoiceRecorder;
    @BindView(R.id.text)
    TextView mText;
    private final VoiceRecorder.Callback mVoiceCallback = new VoiceRecorder.Callback() {

        @Override
        public void onVoiceStart() {
//            showStatus(true);
            if (mSpeechService != null) {
                mSpeechService.startRecognizing(mVoiceRecorder.getSampleRate());
            }
        }

        @Override
        public void onVoice(byte[] data, int size) {
            if (mSpeechService != null) {
                mSpeechService.recognize(data, size);
            }
        }

        @Override
        public void onVoiceEnd() {
//            showStatus(false);
            if (mSpeechService != null) {
                mSpeechService.finishRecognizing();
            }
        }

    };
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            mSpeechService = SpeechService.from(binder);
            mSpeechService.addListener(mSpeechServiceListener);
//            mStatus.setVisibility(View.VISIBLE);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mSpeechService = null;
        }

    };
    private final SpeechService.Listener mSpeechServiceListener =
            new SpeechService.Listener() {
                @Override
                public void onSpeechRecognized(final String text, final boolean isFinal) {
                    if (isFinal) {
                        mVoiceRecorder.dismiss();
                    }
                    if (mText != null && !TextUtils.isEmpty(text)) {
                        mText.setText(text);
                        Toast.makeText(getContext(), "Text recognize: " + text, Toast.LENGTH_SHORT).show();
                    }
                }
            };

    public interface VideoCallListener {
        void onCallFinish(User partner);
    }

    private VideoCallListener listener;

    @BindView(R.id.layout_partner_video)
    RelativeLayout partnerVideo;

    @BindView(R.id.layout_user_video)
    RelativeLayout userVideo;

    @BindView(R.id.layout_contacting)
    RelativeLayout contacting;

    @BindView(R.id.button_microphone)
    ImageButton btMicrophone;

    public static VideoCallFragment getInstance(VideoCallListener listener, MatchingResult matchingResult) {
        VideoCallFragment fragment = new VideoCallFragment();
        fragment.listener = listener;
        fragment.matchingResult = matchingResult;

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

        presenter = new VideoCallPresenter();
        presenter.attachView(this);
        presenter.matchingResult = matchingResult;

        presenter.configSinchService();
    }

    @Override
    public void showConnecting() {
        // Toast.makeText(getActivity(), "Someone calling", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showVideoCall(View remoteView, View localView) {
        //Toast.makeText(getActivity(), "Video shown", Toast.LENGTH_SHORT).show();
        AudioManager audioManager = (AudioManager) getActivity().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        audioManager.setSpeakerphoneOn(true);
        contacting.setVisibility(View.GONE);
        userVideo.addView(localView);
        partnerVideo.addView(remoteView);
    }

    @Override
    public void showCallFinished() {

        listener.onCallFinish(matchingResult.getPartner());
    }

    @OnClick(R.id.button_end)
    public void endConversation() {
        AudioManager audioManager = (AudioManager) getActivity().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(false);
        presenter.terminateCall();
    }

    @OnClick(R.id.button_microphone)
    public void toggleMicrophone() {
//        System.out.println("da click button microphone");
////        microphoneEnable = !microphoneEnable;
////        presenter.setMicroEnabled(microphoneEnable);
//        // Prepare Cloud Speech API
//        getActivity().bindService(new Intent(getContext(), SpeechService.class), mServiceConnection, BIND_AUTO_CREATE);
//
//        // Start listening to voices
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)
//                == PackageManager.PERMISSION_GRANTED) {
//            startVoiceRecorder();
//        } else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//                Manifest.permission.RECORD_AUDIO)) {
//            showPermissionMessageDialog();
//        } else {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO},
//                    REQUEST_RECORD_AUDIO_PERMISSION);
//        }
    }

    private void startVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
        }
        mVoiceRecorder = new VoiceRecorder(mVoiceCallback);
        mVoiceRecorder.start();
    }

    private void stopVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
            mVoiceRecorder = null;
        }
    }

    private void showPermissionMessageDialog() {
        Toast.makeText(getContext(), "This app needs to record audio and recognize your speech.", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button_record)
    public void recordAudio() {
        String fileName = getActivity().getExternalCacheDir().getAbsolutePath();
        fileName += "/speakup.3gp";

        if (recording) {
            presenter.stopRecord();
            presenter.startPlaying(fileName);
        } else presenter.startRecordAudio(fileName);

        recording = !recording;
    }

    @Override
    public void callAccepted() {
        Toast.makeText(getActivity(), "call accepted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }
}
