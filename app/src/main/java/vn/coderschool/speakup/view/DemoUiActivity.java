package vn.coderschool.speakup.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import vn.coderschool.speakup.R;

/**
 * Created by udcun on 3/27/2017.
 */

// This activity is only used to test a xml view
public class DemoUiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
    }
}
