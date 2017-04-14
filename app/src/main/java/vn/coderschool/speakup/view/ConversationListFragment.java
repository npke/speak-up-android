package vn.coderschool.speakup.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Conversation;

/**
 * Created by kenp on 06/04/2017.
 */

public class ConversationListFragment extends Fragment {

    private List<Conversation> mConversations;

    @BindView(R.id.recycler_conversations)
    RecyclerView recyclerConversations;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversation_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        ConversationAdapter adapter = new ConversationAdapter(mConversations);
        adapter.setListener(new ConversationAdapter.ConversationListener() {
            @Override
            public void onConversationClick(Conversation conversation) {
                Intent intent = new Intent(getActivity(), SampleConversationDetailActivity.class);
                intent.putExtra("conversation", Parcels.wrap(conversation));

                startActivity(intent);
            }
        });
        recyclerConversations.setAdapter(adapter);
        recyclerConversations.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public static Fragment getInstance(List<Conversation> conversations) {
        ConversationListFragment fragment = new ConversationListFragment();
        fragment.mConversations = conversations;

        return fragment;
    }
}
