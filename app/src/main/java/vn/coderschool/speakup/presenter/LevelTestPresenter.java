package vn.coderschool.speakup.presenter;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import vn.coderschool.speakup.model.Question;
import vn.coderschool.speakup.view.LevelTestView;

/**
 * Created by udcun on 3/23/2017.
 */

public class LevelTestPresenter implements Presenter<LevelTestView>  {

    public static final String TAG = "LevelTestPresenter";

    private LevelTestView levelTestView;

    private DatabaseReference mDatabase;

    private List<Question> questions;

    public LevelTestPresenter() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.questions = new ArrayList<>();
    }

    @Override
    public void attachView(LevelTestView view) {
        this.levelTestView = view;
    }

    @Override
    public void detachView() {
        this.levelTestView = null;
    }

    public void loadQuestions() {
        DatabaseReference reference = mDatabase.child("questions");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    Question question = questionSnapshot.getValue(Question.class);
                    Log.d(TAG, question.question.toString());
                    questions.add(question);
                }
                Log.d(TAG, "answer 1 of question 1: " + questions.get(0).answers.get(0).content.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
