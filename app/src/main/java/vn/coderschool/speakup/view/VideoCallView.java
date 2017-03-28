package vn.coderschool.speakup.view;

import android.view.View;

/**
 * Created by kenp on 25/03/2017.
 */

public interface VideoCallView extends MvpView {
    void showConnecting();

    void showVideoCall(View remoteView, View localView);

    void showCallFinished();

    void callAccepted();

    void showToast(String xxxxx);
}
