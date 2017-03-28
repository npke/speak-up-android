package vn.coderschool.speakup.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.coderschool.speakup.R;
import vn.coderschool.speakup.model.Conversation;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    public interface ConversationListener {
        void onConversationClick(Conversation conversation);
    }

    private List<Conversation> mConversations;
    private ConversationListener mListener;

    public ConversationAdapter(List<Conversation> conversations) {
        this.mConversations = conversations;
    }

    public void setListener(ConversationListener listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Conversation conversation = mConversations.get(position);

        holder.tvTopicName.setText(conversation.getName());
        holder.tvTopicDescription.setText(conversation.getDescription());
    }

    @Override
    public int getItemCount() {
        return mConversations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_topic_name)
        public TextView tvTopicName;

        @BindView(R.id.text_topic_description)
        public TextView tvTopicDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mListener.onConversationClick(mConversations.get(position));
                }
            });
        }
    }
}
