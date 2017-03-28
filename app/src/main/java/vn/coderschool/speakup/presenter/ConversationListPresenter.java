package vn.coderschool.speakup.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.coderschool.speakup.model.Conversation;
import vn.coderschool.speakup.view.ConversationListView;

public class ConversationListPresenter implements Presenter<ConversationListView> {

    private ConversationListView view;
    private DatabaseReference  dataRef = FirebaseDatabase.getInstance().getReference("/samples");

    @Override
    public void attachView(ConversationListView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void getConversationList(String level) {
        dataRef.child(level).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Conversation> conversations = new ArrayList<Conversation>();
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();

                for(DataSnapshot con : data) {
                    Conversation conversation = con.getValue(Conversation.class);
                    conversations.add(conversation);
                }

                view.showConversations(conversations);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
