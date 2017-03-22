package vn.coderschool.speakup;

import android.app.Application;
import android.content.Context;

/**
 * Created by udcun on 3/22/2017.
 */

public class SpeakUpApplication extends Application {

    public static SpeakUpApplication get(Context context) {
        return (SpeakUpApplication) context.getApplicationContext();
    }
}
