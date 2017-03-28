package vn.coderschool.speakup.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Conversation;
import vn.coderschool.speakup.presenter.ConversationListPresenter;

public class ConversationListActivity extends AppCompatActivity implements ConversationListView {

    private String conversationLevel = "beginner";
    private ConversationListPresenter presenter;

    @BindView(R.id.recycler_conversation)
    RecyclerView rvConversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        ButterKnife.bind(this);

        String level = getIntent().getStringExtra("level");
        if (level != null)
            conversationLevel = level;

        presenter = new ConversationListPresenter();
        presenter.attachView(this);

        presenter.getConversationList(conversationLevel);
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public void showConversations(List<Conversation> conversations) {
        ConversationAdapter adapter = new ConversationAdapter(conversations);
        adapter.setListener(new ConversationAdapter.ConversationListener() {
            @Override
            public void onConversationClick(Conversation conversation) {
                openConversationDetail(conversation);
            }
        });

        rvConversation.setAdapter(adapter);
        rvConversation.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void openConversationDetail(Conversation conversation) {
        Intent intent = new Intent(this, SampleConversationDetailActivity.class);
        intent.putExtra("conversation", Parcels.wrap(conversation));

        startActivity(intent);
    }
}
