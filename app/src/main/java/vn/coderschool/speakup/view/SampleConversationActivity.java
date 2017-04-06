package vn.coderschool.speakup.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Conversation;
import vn.coderschool.speakup.presenter.SampleConversationsPresenter;

public class SampleConversationActivity extends AppCompatActivity implements SampleConversationView {

    @BindView(R.id.tab_sample_conversations)
    TabLayout tabLayout;

    @BindView(R.id.vie_pager_sample_conversations)
    ViewPager viewPager;

    SampleConversationsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_conversation);

        ButterKnife.bind(this);

        presenter = new SampleConversationsPresenter();
        presenter.attachView(this);

        presenter.showConversations();
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public void showConversation(List<Conversation> beginner, List<Conversation> immediate, List<Conversation> advanced) {
        ConversationFragmentPagerAdapter adapter = new ConversationFragmentPagerAdapter(getSupportFragmentManager(),
                beginner,
                immediate,
                advanced);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
