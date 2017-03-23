package vn.coderschool.speakup.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.presenter.LevelTestPresenter;

/**
 * Created by udcun on 3/23/2017.
 */

public class LevelTestActivity extends AppCompatActivity implements LevelTestView {

    private LevelTestPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_test);

        ButterKnife.bind(this);

        presenter = new LevelTestPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void previousQuestion() {

    }

    @Override
    public void nextQuestion() {

    }

    @OnClick(R.id.button3)
    public void doneTest() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
