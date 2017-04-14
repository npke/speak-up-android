package vn.coderschool.speakup.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.coderschool.speakup.model.Conversation;
import vn.coderschool.speakup.view.SampleConversationView;

/**
 * Created by kenp on 06/04/2017.
 */

public class SampleConversationsPresenter implements Presenter<SampleConversationView> {

    SampleConversationView view;

    private DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("/samples");

    private List<Conversation> beginnerConversations = new ArrayList<>();
    private List<Conversation> intermediateConversations = new ArrayList<>();
    private List<Conversation> advancedConversations = new ArrayList<>();

    @Override
    public void attachView(SampleConversationView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void showConversations() {
        dataRef.child("beginner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Conversation> conversations = new ArrayList<Conversation>();
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();

                for(DataSnapshot con : data) {
                    Conversation conversation = con.getValue(Conversation.class);
                    conversations.add(conversation);
                }

                beginnerConversations = conversations;
                if (intermediateConversations.size() * advancedConversations.size() != 0) {
                    view.showConversation(beginnerConversations,
                            intermediateConversations, advancedConversations);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dataRef.child("intermediate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Conversation> conversations = new ArrayList<Conversation>();
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();

                for(DataSnapshot con : data) {
                    Conversation conversation = con.getValue(Conversation.class);
                    conversations.add(conversation);
                }

                intermediateConversations = conversations;
                if (beginnerConversations.size() * advancedConversations.size() != 0) {
                    view.showConversation(beginnerConversations,
                            intermediateConversations, advancedConversations);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dataRef.child("advanced").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Conversation> conversations = new ArrayList<Conversation>();
                Iterable<DataSnapshot> data = dataSnapshot.getChildren();

                for(DataSnapshot con : data) {
                    Conversation conversation = con.getValue(Conversation.class);
                    conversations.add(conversation);
                }

                advancedConversations = conversations;
                if (intermediateConversations.size() * beginnerConversations.size() != 0) {
                    view.showConversation(beginnerConversations,
                            intermediateConversations, advancedConversations);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

