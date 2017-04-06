package vn.coderschool.speakup.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Sound;
import vn.coderschool.speakup.presenter.PracticePronunciationPresenter;

public class PracticePronunciationActivity extends AppCompatActivity implements PracticePronunciationView {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private PracticePronunciationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_pronunciation);

        ButterKnife.bind(this);

        presenter = new PracticePronunciationPresenter();
        presenter.attachView(this);
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public void showSounds(List<Sound> vowels, List<Sound> diphthongs, List<Sound> consonants) {
        SoundFragmentPagerAdapter adapter = new SoundFragmentPagerAdapter(getSupportFragmentManager(),
                vowels, diphthongs, consonants);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
